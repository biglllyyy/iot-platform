package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.common.AbstractDeviceMessagePipe;
import me.iot.common.Callback;
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
public class CacheMsgHandler extends AbstractDeviceMessagePipe {

    @Autowired
    DeviceManageService deviceManageService;
    @Autowired
    private DmsConfig dmsConfig;

    @Override
    public void input(Callback<IMsg> callback) {
        //"iot.DasToDms"
        String topic = TopicConsts.getTopicFromDasToDms();

        try {
            dmsConfig.getConsumer().subscribe(Lists.newArrayList(topic), new MessageListener() {
                @Override
                public void onSuccess(Message message) {
                    IMsg msg = convert(message.getContent());
                    callback.onSuccess(msg);
                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onFailure(t);
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
