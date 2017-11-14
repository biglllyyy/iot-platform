package me.iot.dms.service;

import me.iot.common.dto.QueryResult;
import me.iot.common.msg.DeviceConnectionMsg;
import me.iot.dms.IDeviceConnectionLogService;
import me.iot.dms.dao.DeviceConnectionLogDao;
import me.iot.dms.entity.DeviceConnectionLog;
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
 * @author :  sylar
 * @FileName :  DeviceConnectionLogServiceImpl
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
public class DeviceConnectionLogServiceImpl implements IDmsMsgProcessor<DeviceConnectionMsg>,
        IDeviceConnectionLogService {
    @Autowired
    DeviceConnectionLogDao dao;

    @Autowired
    DeviceStatusServiceImpl deviceStatusServiceImpl;

    @Override
    public void processMsg(DeviceConnectionMsg msg) {
        DeviceConnectionLog log = new DeviceConnectionLog();
        log.setDeviceType(msg.getSourceDeviceType());
        log.setDeviceId(msg.getSourceDeviceId());
        log.setNodeId(msg.getDasNodeId());
        log.setTerminalIp(msg.getTerminalIp());
        log.setConnected(msg.isConnected());

        dao.saveAndFlush(log);
        deviceStatusServiceImpl.processMsg(msg);
    }

    @Override
    public QueryResult<DeviceConnectionLog> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        Page<DeviceConnectionLog> page = dao.findAll(new Specification<DeviceConnectionLog>() {
            @Override
            public Predicate toPredicate(Root<DeviceConnectionLog> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime,
                        endTime);
            }
        }, new PageRequest(pageIndex - 1, pageSize));
        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }

}
