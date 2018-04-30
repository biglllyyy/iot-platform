package iot.dmp.dms.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DeviceInfoMsg;
import iot.dmp.dms.IDeviceInfoService;
import iot.dmp.dms.dao.DeviceInfoDao;
import iot.dmp.dms.dto.DeviceInfoDto;
import iot.dmp.dms.po.DeviceInfo;
import iot.dmp.dms.po.DeviceOwner;
import iot.dmp.dms.po.DeviceStatus;
import iot.util.misc.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :  DeviceInfoServiceImpl
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Service
public class DeviceInfoServiceImpl implements IDmsMsgProcessor<DeviceInfoMsg>, IDeviceInfoService {

    @Autowired
    DeviceInfoDao dao;


    @Override
    public void processMsg(DeviceInfoMsg msg) {
        DeviceInfo pojo = dao.getByDeviceId(msg.getSourceDeviceId());
        if (pojo == null) {
            pojo = new DeviceInfo();
        }

        pojo.setDeviceType(msg.getSourceDeviceType());
        pojo.setDeviceId(msg.getSourceDeviceId());
        pojo.setBid(msg.getBid());
        pojo.setMac(msg.getMac());
        pojo.setVersionCode(msg.getVersion());

        if (msg.getParams() != null && msg.getParams().size() > 0) {
            String params = JSON.toJSONString(msg.getParams());
            pojo.setParams(params);
        }

        pojo.setParams(JSON.toJSONString(msg.getParams()));
        dao.saveAndFlush(pojo);
    }

    @Override
    public long countOfDeviceInfo() {
        return dao.count();
    }

    @Override
    public long countOfDeviceInfoByDeviceType(String deviceType) {
        return dao.count(new Specification<DeviceInfo>() {
            @Override
            public Predicate toPredicate(Root<DeviceInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType);
            }
        });
    }

    @Override
    public long countOfDeviceInfoByDeviceTypeAndVersionCode(String deviceType, int versionCode) {
        return dao.count(new Specification<DeviceInfo>() {
            @Override
            public Predicate toPredicate(Root<DeviceInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return criteriaBuilder.and(
                        PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType),
                        criteriaBuilder.equal(root.get("versionCode").as(Integer.class), versionCode));
            }
        });
    }

    @Override
    public DeviceInfoDto getDeviceInfoByDeviceId(String deviceId) {
        DeviceInfoDto dto = new DeviceInfoDto();
        BeanUtils.copyProperties(dao.getByDeviceId(deviceId), dto);
        return dto;
    }

    @Override
    public DeviceInfoDto getDeviceInfoById(long id) {
        DeviceInfoDto dto = new DeviceInfoDto();
        BeanUtils.copyProperties(dao.findOne(id), dto);
        return dto;
    }

    @Override
    public DeviceInfoDto getDeviceInfoByMac(String mac) {
        DeviceInfoDto dto = new DeviceInfoDto();
        BeanUtils.copyProperties(dao.getByMac(mac), dto);
        return dto;
    }

    @Override
    public QueryResult<DeviceInfoDto> getDeviceInfosByDeviceType(String deviceType, int pageIndex, int pageSize) {
        Page<DeviceInfo> page = dao.findAll(new Specification<DeviceInfo>() {
            @Override
            public Predicate toPredicate(Root<DeviceInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType);
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(convert(page), page.getTotalElements());
    }

    @Override
    public QueryResult<DeviceInfoDto> getDeviceInfosByDeviceTypeAndVersion(String deviceType, int versionCode, int
            pageIndex, int pageSize) {
        Page<DeviceInfo> page = dao.findAll(
                new Specification<DeviceInfo>() {
                    @Override
                    public Predicate toPredicate(Root<DeviceInfo> root, CriteriaQuery<?> criteriaQuery,
                                                 CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.and(
                                PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType),
                                criteriaBuilder.equal(root.get("versionCode").as(Integer.class), versionCode));
                    }
                },
                new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(convert(page), page.getTotalElements());
    }

    @Override
    public QueryResult<DeviceInfoDto> getDeviceInfoByOwnerId(String ownerId, int pageIndex, int pageSize) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                Root<DeviceOwner> ownerRoot = query.from(DeviceOwner.class);
                predicateList.add(cb.equal(root.get("deviceId").as(String.class), ownerRoot.get("deviceId")));
                predicateList.add(cb.equal(ownerRoot.get("ownerId").as(String.class), ownerId));
                predicateList.add(cb.equal(ownerRoot.get("isBound").as(Boolean.class), true));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        if (pageIndex == 0 || pageSize == 0) {
            List<DeviceInfo> all = dao.findAll(spec);
            List<DeviceInfoDto> list = Lists.newArrayList();
            all.forEach(po -> {
                DeviceInfoDto dto = new DeviceInfoDto();
                BeanUtils.copyProperties(po, dto);
                list.add(dto);
            });

            return new QueryResult<>(list, list.size());
        } else {
            Page<DeviceInfo> page = dao.findAll(spec, new PageRequest(pageIndex - 1, pageSize));
            return new QueryResult<>(convert(page), page.getTotalElements());
        }

    }

    /**
     * 根据参数查询设备信息
     *
     * @param deviceType 设备类型
     * @param connected  是否在线
     * @param pageIndex  当前页数
     * @param pageSize   每页显示条数
     * @return 设备信息列表
     */
    @Override
    public QueryResult<DeviceInfoDto> findDeviceByParams(String[] ownerIds, String deviceType, boolean connected, int
            pageIndex, int pageSize) {
        Page<DeviceInfo> page = dao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            Root<DeviceStatus> ownerRoot = query.from(DeviceStatus.class);
            predicateList.add(cb.equal(root.get("deviceId").as(String.class), ownerRoot.get("deviceId")));
            if (StringUtils.isNotBlank(deviceType)) {
                predicateList.add(cb.equal(root.get("deviceType").as(String.class), deviceType));
            }
            if (connected) {
                predicateList.add(cb.equal(root.get("connected").as(Boolean.class), connected));
            }
            if (null != ownerIds && ownerIds.length > 0) {

                predicateList.add(cb.and(root.get("ownerId").as(String.class).in((Object[]) ownerIds)));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(convert(page), page.getTotalElements());
    }

    private List<DeviceInfoDto> convert(Page<DeviceInfo> page) {
        List<DeviceInfoDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceInfoDto dto = new DeviceInfoDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });
        return list;
    }
}
