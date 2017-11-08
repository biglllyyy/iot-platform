package me.iot.dms.service;

import com.google.common.base.Strings;
import me.iot.common.msg.DasConnectionMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.dms.DmsConfig;
import me.iot.dms.DmsTopics;
import me.iot.dms.IDeviceMessageService;
import me.iot.dms.bean.MsgSender;
import me.iot.util.redis.ISubscribePublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public class DeviceMessageServiceImpl implements IDmsMsgProcessor<IMsg>, IDeviceMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceMessageServiceImpl.class);

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    MsgSender msgSender;

    ISubscribePublishService sps;

    @PostConstruct
    public void init() {
        sps = dmsConfig.getSps();
    }

    @Override
    public void processMsg(IMsg msg) {
        if (msg instanceof DasConnectionMsg) {
            //das node connection msg
            return;
        }
        if (Strings.isNullOrEmpty(msg.getSourceDeviceId())
                || Strings.isNullOrEmpty(msg.getMsgCode())) {
            LOG.warn("sourceDeviceId is null or msgCode is null:{}", msg);
            return;
        }


        String topic = DmsTopics.getTopicWhenPublish(msg.getSourceDeviceType(), msg.getSourceDeviceId());
        CacheMsgWrap wrap = new CacheMsgWrap(msg);
        LOG.info("DMS publish DeviceMessage\n{}", msg);
        sps.publishMessage(topic, wrap);

        //only test
//        DeviceMsg deviceMsg = DeviceMsg.newMsgToCloud("130","TRCAN","1234567890");
//        topic = DmsTopics.getTopicWhenPublish(deviceMsg.getSourceDeviceType(), deviceMsg.getSourceDeviceId());
//        wrap = new CacheMsgWrap(deviceMsg);
//        LOG.info("DMS publish DeviceMessage\n{}", deviceMsg);
//        sps.publishMessage(topic, wrap);

    }

    @Override
    public void sendMsg(IMsg msg) {
        msgSender.sendToQueue(msg);
    }
}
