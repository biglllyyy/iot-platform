package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.common.dto.QueryResult;
import me.iot.common.msg.DeviceEventMsg;
import me.iot.dms.IDeviceEventService;
import me.iot.dms.dao.DeviceEventDao;
import me.iot.dms.entity.DeviceEvent;
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
 * @FileName :  MqttConst
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
public class DeviceEventServiceImpl implements IDmsMsgProcessor<DeviceEventMsg>, IDeviceEventService {

    @Autowired
    DeviceEventDao dao;

    @Override
    public void processMsg(DeviceEventMsg msg) {

        DeviceEvent pojo = new DeviceEvent();
        pojo.setDeviceType(msg.getSourceDeviceType());
        pojo.setDeviceId(msg.getSourceDeviceId());
        pojo.setEventCode(msg.getEventCode());
        pojo.setEventDesc(msg.getEventDescription());

        dao.saveAndFlush(pojo);
    }

    @Override
    public long countOfDeviceEvent(long beginTime, long endTime) {
        return dao.count(new Specification<DeviceEvent>() {
            @Override
            public Predicate toPredicate(Root<DeviceEvent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceEventByDeviceType(String deviceType, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceEvent>() {
            @Override
            public Predicate toPredicate(Root<DeviceEvent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceTypeAndCreateTime(root, criteriaBuilder, deviceType,
                        beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceEventByDeviceId(String deviceId, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceEvent>() {
            @Override
            public Predicate toPredicate(Root<DeviceEvent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime,
                        endTime);
            }
        });
    }

    @Override
    public QueryResult<DeviceEvent> getDeviceEventsByDeviceId(String deviceId, List<String> eventCodes, long
            beginTime, long endTime, int pageIndex, int pageSize) {
        Page<DeviceEvent> page = dao.findAll(new Specification<DeviceEvent>() {
            @Override
            public Predicate toPredicate(Root<DeviceEvent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId,
                        beginTime, endTime));

                if (eventCodes != null && !eventCodes.isEmpty()) {
                    predicateList.add(criteriaBuilder.and(root.get("eventCode").as(String.class).in(eventCodes)));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }
}
