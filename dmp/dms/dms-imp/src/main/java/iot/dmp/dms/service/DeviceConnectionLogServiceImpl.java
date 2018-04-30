package iot.dmp.dms.service;

import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DeviceConnectionMsg;
import iot.dmp.dms.IDeviceConnectionLogService;
import iot.dmp.dms.dao.DeviceConnectionLogDao;
import iot.dmp.dms.dto.DeviceConnectionLogDto;
import iot.dmp.dms.po.DeviceConnectionLog;
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
        log.setNodeId(msg.getDcsNodeId());
        log.setTerminalIp(msg.getTerminalIp());
        log.setConnected(msg.isConnected());

        dao.saveAndFlush(log);
        deviceStatusServiceImpl.processMsg(msg);
    }

    @Override
    public QueryResult<DeviceConnectionLogDto> getDeviceConnectionLogsByDeviceId(String deviceId, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        Page<DeviceConnectionLog> page = dao.findAll(new Specification<DeviceConnectionLog>() {
            @Override
            public Predicate toPredicate(Root<DeviceConnectionLog> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceIdAndCreateTime(root, criteriaBuilder, deviceId, beginTime,
                        endTime);
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        List<DeviceConnectionLogDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceConnectionLogDto dto = new DeviceConnectionLogDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }

}
