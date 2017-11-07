package me.iot.dms.service;

import me.iot.common.pojo.DeviceGuid;
import me.iot.dms.IDeviceTokenService;
import me.iot.dms.dao.DeviceTokenDao;
import me.iot.dms.entity.DeviceToken;
import me.iot.common.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by sylar on 16/5/26.
 */
@Service
public class DeviceTokenService implements IDeviceTokenService {

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
    public DeviceToken getDeviceTokenByToken(String token) {
        return dao.getByToken(token);
    }

    @Override
    public DeviceToken getDeviceTokenByDeviceId(String deviceId) {
        return dao.getByDeviceId(deviceId);
    }

    @Override
    public QueryResult<DeviceToken> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize) {
        Page<DeviceToken> page = dao.findAll(new Specification<DeviceToken>() {
            @Override
            public Predicate toPredicate(Root<DeviceToken> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deviceType").as(String.class), deviceType);
            }
        }, new PageRequest(pageIndex - 1, pageSize));
        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }

}
