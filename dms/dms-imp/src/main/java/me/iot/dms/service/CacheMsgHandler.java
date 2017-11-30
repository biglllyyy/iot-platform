package me.iot.dms.service;

import me.iot.common.AbstractDeviceMessagePipe;
import me.iot.common.Callback;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.GroupConsts;
import me.iot.common.usual.TopicConsts;
import me.iot.dms.DmsConfig;
import me.iot.util.rocketmq.IConsumer;
import me.iot.util.rocketmq.IConsumerConfig;
import me.iot.util.rocketmq.msg.IRocketMsgListener;
import me.iot.util.rocketmq.msg.RocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author :  sylar
 * @FileName :  CacheMsgHandler
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
@Component
public class CacheMsgHandler extends AbstractDeviceMessagePipe {

    @Autowired
    DeviceManageServiceImpl deviceManageService;

    @Autowired
    private DmsConfig dmsConfig;

    private IConsumer consumer;

    @PostConstruct
    public void initialize() {
        consumer = dmsConfig.getFactory().createConsumer(new IConsumerConfig() {
            @Override
            public String getConsumerId() {
                return GroupConsts.IOT_DAS_TO_DMS_GROUP;
            }
        });

        init();
    }

    @Override
    public void input(Callback<IMsg> callback) {
        String topic = TopicConsts.DAS_TO_DMS;
        consumer.subscribe(topic, null, new IRocketMsgListener() {
            @Override
            public void onSuccess(List<RocketMsg> messages) {
                for (RocketMsg rocketMsg : messages) {
                    callback.onSuccess(convert(rocketMsg.getContent()));
                }
            }

            @Override
            public void onFaild(Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (consumer != null) {
            consumer.unsubscribe();
            consumer = null;
        }
    }


    @Override
    public void output(IMsg msg) throws Exception {
        deviceManageService.processMsg(msg);
    }
}
