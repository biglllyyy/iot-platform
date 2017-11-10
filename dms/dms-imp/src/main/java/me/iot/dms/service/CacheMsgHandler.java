package me.iot.dms.service;

import me.iot.common.AbstractDeviceMessagePipe;
import me.iot.common.Callback;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.GroupConsts;
import me.iot.common.usual.TopicConsts;
import me.iot.util.rocketmq.IConsumer;
import me.iot.util.rocketmq.IConsumerConfig;
import me.iot.util.rocketmq.IFactory;
import me.iot.util.rocketmq.RocketMQUtil;
import me.iot.util.rocketmq.msg.IRocketMsgListener;
import me.iot.util.rocketmq.msg.RocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private IFactory factory;

    @Value("${iot.rocketmq.brokers}")
    private String brokers;

    @PostConstruct
    public void init() {
        factory = RocketMQUtil.createOwnFactory(brokers);
    }

    @Override
    public void input(Callback<IMsg> callback) {
        IConsumer consumer = factory.createConsumer(new IConsumerConfig() {
            @Override
            public String getConsumerId() {
                return GroupConsts.IOT_DMS_GROUP;
            }
        });

        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void output(IMsg msg) {
        deviceManageService.processMsg(msg);
    }
}
