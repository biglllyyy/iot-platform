package iot.dmp.dms;

import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotConsumer;
import iot.common.mq.MqMsgUtils;
import iot.common.msg.IMsg;
import iot.dmp.dms.service.DeviceManageServiceImpl;
import iot.util.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  DcsToDmsConsumer
 * @CreateDate :  2017/11/08
 * @Description : 订阅DCS发出的消息，转交给 deviceManageService 处理
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
public class DcsToDmsConsumer extends AbstractIotConsumer {

    @Autowired
    DeviceManageServiceImpl deviceManageService;

    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DCS_TO_DMS;
    }

    @Override
    protected String[] getTags() {
        return null;
    }

    @Override
    protected String getConsumerGroupId() {
        return MqConsts.CID_DCS_TO_DMS_GROUP;
    }

    @Override
    protected void onConsume(Message message) {
        try {
            IMsg msg = MqMsgUtils.convert(message);
            deviceManageService.processMsg(msg);
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            e.printStackTrace();
        }
    }
}
