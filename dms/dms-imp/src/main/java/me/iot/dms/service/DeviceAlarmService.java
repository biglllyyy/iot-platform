package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.common.msg.DeviceAlarmMsg;
import me.iot.dms.IDeviceAlarmService;
import me.iot.dms.dao.DeviceAlarmDao;
import me.iot.dms.entity.DeviceAlarm;
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
import java.util.List;

/**
 * Created by sylar on 16/6/4.
 */
@Service
public class DeviceAlarmService implements IDmsMsgProcessor<DeviceAlarmMsg>, IDeviceAlarmService {

    @Autowired
    DeviceAlarmDao dao;

    @Override
    public void processMsg(DeviceAlarmMsg msg) {

        DeviceAlarm pojo = new DeviceAlarm();
        pojo.setDeviceType(msg.getSourceDeviceType());
        pojo.setDeviceId(msg.getSourceDeviceId());
        pojo.setAlarmCode(msg.getAlarmCode());
        pojo.setAlarmDesc(msg.getAlarmDescription());

        dao.saveAndFlush(pojo);
    }

    @Override
    public long countOfDeviceAlarm(long beginTime, long endTime) {
        return dao.count(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return PredicateUtil.newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceTypeAndCreateTime(root, criteriaBuilder, deviceType, beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime, endTime);
            }
        });
    }

    @Override
    public QueryResult<DeviceAlarm> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long beginTime, long endTime, int pageIndex, int pageSize) {
        Page<DeviceAlarm> page = dao.findAll(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime, endTime));

                if (alarmCodes != null && !alarmCodes.isEmpty()) {
                    predicateList.add(criteriaBuilder.in(root.get("alarmCode").as(String.class).in(alarmCodes)));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }
}
