package me.iot.dms.bean;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.common.usual.GroupConsts;
import me.iot.common.usual.TopicConsts;
import me.iot.dms.DmsConfig;
import me.iot.dms.entity.DeviceStatus;
import me.iot.dms.service.DeviceStatusServiceImpl;
import me.iot.util.rocketmq.IProducer;
import me.iot.util.rocketmq.IProducerConfig;
import me.iot.util.rocketmq.msg.RocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :  MsgSender
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
    private DmsConfig dmsConfig;

    @Autowired
    private DeviceStatusServiceImpl deviceStatusServiceImpl;

    private IProducer producer;

    @PostConstruct
    public void init() {
        producer = dmsConfig.getFactory().createProducer(new IProducerConfig() {
            @Override
            public String getProducerId() {
                return String.join("-", GroupConsts.IOT_DMS_TO_APS_GROUP, this.getClass().getSimpleName());
            }
        });
    }

    public void sendToQueue(IMsg msg) throws Exception {
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
        CacheMsgWrap wrap = new CacheMsgWrap(msg);

        RocketMsg rocketMsg = new RocketMsg(TopicConsts.DAS_TO_DMS);

        List<String> tagsList = Lists.newArrayList();
        tagsList.add(nodeId);
        tagsList.add(msg.getMsgCode());
        tagsList.add(msg.getSourceDeviceType());
        tagsList.add(msg.getSourceDeviceId());
        tagsList.add(String.valueOf(msg.getMsgType()));
        tagsList.add(String.valueOf(msg.getOccurTime()));

        rocketMsg.setContent(JSON.toJSONString(wrap));

        producer.syncSend(rocketMsg);
    }
}
