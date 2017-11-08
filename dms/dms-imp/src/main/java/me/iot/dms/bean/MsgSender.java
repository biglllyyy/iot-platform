package me.iot.dms.bean;

import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.common.usual.DasCacheKeys;
import me.iot.dms.DmsConfig;
import me.iot.dms.entity.DeviceStatus;
import me.iot.dms.service.DeviceStatusServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author :  sylar
 * @FileName :  MqttConst
 * @CreateDate :  2017/11/08
 * @Description : 消息发送器:  将消息发给 DAS
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class MsgSender {
    private static final Logger LOG = LoggerFactory.getLogger(MsgSender.class);

    @Autowired
    ApplicationContext ctx;

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    DeviceStatusServiceImpl deviceStatusServiceImpl;

    public void sendToQueue(IMsg msg) {
        if (msg == null) {
            return;
        }

        DeviceStatus deviceStatus = deviceStatusServiceImpl.getDeviceStatus(msg.getTargetDeviceType() + msg
                .getTargetDeviceId());
        if (deviceStatus == null) {
            LOG.warn("can not send msg: deviceStatus not found, nodeId is unknown");
            return;
        }

        if (!deviceStatus.isConnected()) {
            LOG.warn("can not send msg: device is not connected.");
            return;
        }

        String nodeId = deviceStatus.getNodeId();
        String mqsKey = DasCacheKeys.getMqsKeyFromDmsToDas(nodeId);
        CacheMsgWrap wrap = new CacheMsgWrap(msg);
        dmsConfig.getMqs().sendMessage(mqsKey, wrap);
    }
}
