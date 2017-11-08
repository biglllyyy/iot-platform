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
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Service
public class DeviceStatusServiceImpl implements IDmsMsgProcessor<DeviceConnectionMsg>, IDeviceStatusService {

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    DasStatusServiceImpl dasStatusServiceImpl;

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

        dasStatusServiceImpl.updateDeviceConnection(msg.getDasNodeId(), deviceId, msg.isConnected());
    }

    @Override
    public DeviceStatus getDeviceStatus(String deviceId) {
        String ccsKey = DmsCacheKeys.getCcsKeyForDeviceStatus(deviceId);
        if (!ccs.containsKey(ccsKey)) {
            return null;
        }

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
