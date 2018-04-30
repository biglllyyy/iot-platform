package iot.dmp.dms.service;

import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.pojo.DeviceGuid;
import iot.dmp.dms.IDeviceTokenService;
import iot.dmp.dms.dao.DeviceTokenDao;
import iot.dmp.dms.dto.DeviceTokenDto;
import iot.dmp.dms.dto.MsgLogDto;
import iot.dmp.dms.po.DeviceToken;
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
 * @FileName :  DeviceTokenServiceImpl
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
public class DeviceTokenServiceImpl implements IDeviceTokenService {

    @Autowired
    DeviceTokenDao dao;

    @Override
    public long countOfDeviceToken() {
        return dao.count();
    }

    @Override
    public String generateDeviceId(String deviceType, String token) {
        DeviceToken pojo = dao.getByToken(token);
        if (pojo == null) {
            String guid = null;
            while (guid == null || dao.getByDeviceId(guid) != null) {
                guid = DeviceGuid.generateGuid(deviceType);
            }

            pojo = new DeviceToken();
            pojo.setDeviceType(deviceType);
            pojo.setToken(token);
            pojo.setDeviceId(guid);
            dao.saveAndFlush(pojo);

            return guid;
        } else {
            return pojo.getDeviceId();
        }
    }

    @Override
    public DeviceTokenDto getDeviceTokenByToken(String token) {
        DeviceTokenDto dto = new DeviceTokenDto();
        BeanUtils.copyProperties(dao.getByToken(token), dto);
        return dto;
    }

    @Override
    public DeviceTokenDto getDeviceTokenByDeviceId(String deviceId) {
        DeviceTokenDto dto = new DeviceTokenDto();
        BeanUtils.copyProperties(dao.getByDeviceId(deviceId), dto);
        return dto;
    }

    @Override
    public QueryResult<DeviceTokenDto> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        Page<DeviceToken> page = dao.findAll(new Specification<DeviceToken>() {
            @Override
            public Predicate toPredicate(Root<DeviceToken> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deviceType").as(String.class), deviceType);
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        List<DeviceTokenDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceTokenDto dto = new DeviceTokenDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }

}
