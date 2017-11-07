package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.util.redis.ICentralCacheService;
import me.iot.common.msg.DeviceConnectionMsg;
import me.iot.dms.DmsCacheKeys;
import me.iot.dms.DmsConfig;
import me.iot.dms.IDeviceStatusService;
import me.iot.dms.entity.DeviceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sylar on 16/6/2.
 */
@Service
public class DeviceStatusService implements IDmsMsgProcessor<DeviceConnectionMsg>, IDeviceStatusService {

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    DasStatusService dasStatusService;

    ICentralCacheService ccs;

    @PostConstruct
    private void init() {
        ccs = dmsConfig.getCcs();
    }

    @Override
    public void processMsg(DeviceConnectionMsg msg) {
        String deviceId = msg.getSourceDeviceType() + msg.getSourceDeviceId();
        String ccsKey = DmsCacheKeys.getCcsKeyForDeviceStatus(deviceId);

        DeviceStatus pojo = new DeviceStatus(deviceId, msg.getDasNodeId(), msg.getTerminalIp(), msg.isConnected());
        ccs.putObject(ccsKey, pojo);

        dasStatusService.updateDeviceConnection(msg.getDasNodeId(), deviceId, msg.isConnected());
    }

    @Override
    public DeviceStatus getDeviceStatus(String deviceId) {
        String ccsKey = DmsCacheKeys.getCcsKeyForDeviceStatus(deviceId);
        if (!ccs.containsKey(ccsKey))
            return null;

        return ccs.getObject(ccsKey, DeviceStatus.class);
    }

    @Override
    public List<DeviceStatus> getDeviceStatusBatch(String[] deviceIds) {
        if (deviceIds == null || deviceIds.length <= 0) {
            return null;
        }

        List<DeviceStatus> deviceStatusList = Lists.newArrayList();
        Arrays.stream(deviceIds).forEach(t -> {
            DeviceStatus deviceStatus = getDeviceStatus(t);
            if (deviceStatus != null) {
                deviceStatusList.add(deviceStatus);
            }
        });

        return deviceStatusList;
    }
}
