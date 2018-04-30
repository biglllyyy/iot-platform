package iot.dcp.common.bean;

import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotConsumer;
import iot.common.mq.MqMsgUtils;
import iot.common.msg.DeviceOtaMsg;
import iot.common.msg.IMsg;
import iot.dcp.common.DcsAutoConfiguration;
import iot.dcp.common.DcsProperties;
import iot.dcp.common.event.OtaNewTaskEvent;
import iot.util.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * File Name             :  Dms2DcsConsumer
 * Author                :  sylar
 * Create Date           :  2018/4/18
 * Description           :  订阅DMS发出的消息，并将消息发给 netty channel
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class DmsToDcsConsumer extends AbstractIotConsumer {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private MsgSender sender;

    @Autowired
    private DcsProperties dcsProperties;

    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DMS_TO_DCS;
    }

    @Override
    protected String[] getTags() {
        return new String[]{dcsProperties.getNodeId()};
    }

    @Override
    protected String getConsumerGroupId() {
        return MqConsts.CID_DMS_TO_DCS_GROUP;
    }

    @Override
    protected void onConsume(Message message) {
        IMsg msg = MqMsgUtils.convert(message);
        if (msg == null) {
            return;
        }

        switch (msg.getMsgType()) {
            case DeviceOta:
                ctx.publishEvent(new OtaNewTaskEvent(this, (DeviceOtaMsg) msg));
                break;
            default:
                sender.sendMsg(msg);
                break;
        }
    }
}
