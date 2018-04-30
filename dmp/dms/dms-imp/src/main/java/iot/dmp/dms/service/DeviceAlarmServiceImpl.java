package iot.dmp.dms.service;

import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DeviceAlarmMsg;
import iot.dmp.dms.IDeviceAlarmService;
import iot.dmp.dms.dao.DeviceAlarmDao;
import iot.dmp.dms.dto.DeviceAlarmDto;
import iot.dmp.dms.dto.MsgLogDto;
import iot.dmp.dms.po.DeviceAlarm;
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
 * @FileName :  DeviceAlarmServiceImpl
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
public class DeviceAlarmServiceImpl implements IDmsMsgProcessor<DeviceAlarmMsg>, IDeviceAlarmService {

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
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceAlarmByDeviceType(String deviceType, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceTypeAndCreateTime(root, criteriaBuilder, deviceType,
                        beginTime, endTime);
            }
        });
    }

    @Override
    public long countOfDeviceAlarmByDeviceId(String deviceId, long beginTime, long endTime) {
        return dao.count(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime,
                        endTime);
            }
        });
    }

    @Override
    public QueryResult<DeviceAlarmDto> getDeviceAlarmsByDeviceId(String deviceId, List<String> alarmCodes, long
            beginTime, long endTime, int pageIndex, int pageSize) {
        Page<DeviceAlarm> page = dao.findAll(new Specification<DeviceAlarm>() {
            @Override
            public Predicate toPredicate(Root<DeviceAlarm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId,
                        beginTime, endTime));

                if (alarmCodes != null && !alarmCodes.isEmpty()) {
                    predicateList.add(criteriaBuilder.in(root.get("alarmCode").as(String.class).in(alarmCodes)));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        List<DeviceAlarmDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceAlarmDto dto = new DeviceAlarmDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }
}
