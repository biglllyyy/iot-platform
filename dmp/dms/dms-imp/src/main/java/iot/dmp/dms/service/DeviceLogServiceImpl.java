package iot.dmp.dms.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DeviceLogMsg;
import iot.dmp.dms.IDeviceLogService;
import iot.dmp.dms.dao.DeviceLogDao;
import iot.dmp.dms.dto.DeviceLogDto;
import iot.dmp.dms.po.DeviceLog;
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
 * @FileName :  DeviceLogServiceImpl
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
public class DeviceLogServiceImpl implements IDmsMsgProcessor<DeviceLogMsg>, IDeviceLogService {

    @Autowired
    DeviceLogDao dao;

    @Override
    public void processMsg(DeviceLogMsg msg) {

        DeviceLog pojo = new DeviceLog();
        pojo.setDeviceType(msg.getSourceDeviceType());
        pojo.setDeviceId(msg.getSourceDeviceId());
        pojo.setLogType(msg.getLogType());
        pojo.setLogContent(msg.getLogContent());
        dao.saveAndFlush(pojo);
    }

    @Override
    public QueryResult<DeviceLogDto> getDeviceLogsByTime(String deviceId, String logType, long beginTime, long endTime,
                                                         int pageIndex, int pageSize) {
        Page<DeviceLog> page = dao.findAll(
                new Specification<DeviceLog>() {
                    @Override
                    public Predicate toPredicate(Root<DeviceLog> root, CriteriaQuery<?> criteriaQuery,
                                                 CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.and(
                                PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId,
                                        beginTime, endTime),
                                criteriaBuilder.equal(root.get("logType").as(String.class), logType));
                    }
                },
                new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(convert(page), page.getTotalElements());
    }

    @Override
    public QueryResult<DeviceLogDto> getDeviceLogsByTime(String deviceId, String deviceType, String logType, long
            beginTime, long endTime, int pageIndex, int pageSize) {
        Page<DeviceLog> page = dao.findAll(new Specification<DeviceLog>() {
            @Override
            public Predicate toPredicate(Root<DeviceLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                if (!Strings.isNullOrEmpty(deviceId)) {
                    predicateList.add(PredicateUtil.newPredicateByDeviceId(root, criteriaBuilder, deviceId));
                }
                if (!Strings.isNullOrEmpty(deviceType)) {
                    predicateList.add(PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType));
                }

                Predicate prTime = PredicateUtil.newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
                if (prTime != null) {
                    predicateList.add(prTime);
                }
                if (!Strings.isNullOrEmpty(logType)) {
                    predicateList.add(criteriaBuilder.equal(root.get("logType").as(String.class), logType));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(convert(page), page.getTotalElements());
    }

    private List<DeviceLogDto> convert(Page<DeviceLog> page) {
        List<DeviceLogDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceLogDto dto = new DeviceLogDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });
        return list;
    }

}
