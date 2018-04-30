package iot.dmp.dms.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import iot.common.msg.DeviceConnectionMsg;
import iot.dmp.dms.DmsCacheKeys;
import iot.dmp.dms.DmsConfig;
import iot.dmp.dms.IDeviceStatusService;
import iot.dmp.dms.dto.DeviceStatusDto;
import iot.dmp.dms.po.DeviceStatus;
import iot.util.redis.RedisOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :  DeviceStatusServiceImpl
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
public class DeviceStatusServiceImpl implements IDmsMsgProcessor<DeviceConnectionMsg>, IDeviceStatusService {

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    DcsStatusServiceImpl dasStatusServiceImpl;

    @Autowired
    RedisOperations redisOperations;

    @PostConstruct
    private void init() {

    }

    @Override
    public void processMsg(DeviceConnectionMsg msg) {
        String deviceId = msg.getSourceDeviceType() + msg.getSourceDeviceId();
        String ccsKey = DmsCacheKeys.getKey_deviceStatus(deviceId);

        DeviceStatus pojo = new DeviceStatus(deviceId, msg.getDcsNodeId(), msg.getTerminalIp(), msg.isConnected());
        redisOperations.opsValue(ccsKey).set(JSON.toJSONString(pojo));

        dasStatusServiceImpl.updateDeviceConnection(msg.getDcsNodeId(), deviceId, msg.isConnected());
    }

    @Override
    public DeviceStatus getDeviceStatus(String deviceId) {
        String key = DmsCacheKeys.getKey_deviceStatus(deviceId);
        if (!redisOperations.hasKey(key)) {
            return null;
        }

        return JSON.parseObject(redisOperations.opsValue(key).get(), DeviceStatus.class);
    }

    @Override
    public List<DeviceStatusDto> getDeviceStatusBatch(String[] deviceIds) {
        if (deviceIds == null || deviceIds.length <= 0) {
            return null;
        }

        List<DeviceStatusDto> deviceStatusList = Lists.newArrayList();
        Arrays.stream(deviceIds).forEach(t -> {
            DeviceStatus deviceStatus = getDeviceStatus(t);
            if (deviceStatus != null) {
                deviceStatusList.add(deviceStatus);
            }
        });

        return deviceStatusList;
    }
}
