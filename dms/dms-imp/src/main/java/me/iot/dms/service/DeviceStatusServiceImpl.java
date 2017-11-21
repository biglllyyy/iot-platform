package me.iot.dms.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import me.iot.common.msg.DeviceConnectionMsg;
import me.iot.dms.DmsCacheKeys;
import me.iot.dms.DmsConfig;
import me.iot.dms.IDeviceStatusService;
import me.iot.dms.entity.DeviceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tf56.common.redis.client.CommonRedisUtil;

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
    DasStatusServiceImpl dasStatusServiceImpl;

    private CommonRedisUtil redisUtil = CommonRedisUtil.getInstance();

    @PostConstruct
    private void init() {

    }

    @Override
    public void processMsg(DeviceConnectionMsg msg) {
        String deviceId = msg.getSourceDeviceType() + msg.getSourceDeviceId();
        String ccsKey = DmsCacheKeys.getCcsKeyForDeviceStatus(deviceId);

        DeviceStatus pojo = new DeviceStatus(deviceId, msg.getDasNodeId(), msg.getTerminalIp(), msg.isConnected());
        redisUtil.set(ccsKey, JSON.toJSONString(pojo));

        dasStatusServiceImpl.updateDeviceConnection(msg.getDasNodeId(), deviceId, msg.isConnected());
    }

    @Override
    public DeviceStatus getDeviceStatus(String deviceId) {
        String ccsKey = DmsCacheKeys.getCcsKeyForDeviceStatus(deviceId);
        if (!redisUtil.exists(ccsKey)) {
            return null;
        }

        return JSON.parseObject(redisUtil.get(ccsKey), DeviceStatus.class);
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
