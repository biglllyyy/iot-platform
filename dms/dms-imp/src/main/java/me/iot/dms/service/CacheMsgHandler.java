package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.common.AbstractCacheMsgHandler;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.TopicConsts;
import me.iot.dms.DmsConfig;
import me.iot.util.mq.Message;
import me.iot.util.mq.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sylar on 16/5/31.
 */
@Component
public class CacheMsgHandler extends AbstractCacheMsgHandler {

    @Autowired
    DeviceManageService deviceManageService;
    @Autowired
    private DmsConfig dmsConfig;

    @Override
    public IMsg getMsgFromCache() {

        String topic = TopicConsts.getTopicFromDasToDms();

        try {
            dmsConfig.getConsumer().subscribe(Lists.newArrayList(topic), new MessageListener() {
                @Override
                public void onSuccess(Message message) {

                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void handleMsg(IMsg msg) {
        if (msg == null) {
            return;
        }

        deviceManageService.processMsg(msg);
    }

}
