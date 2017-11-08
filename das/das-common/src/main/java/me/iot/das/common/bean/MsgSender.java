package me.iot.das.common.bean;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import me.iot.common.AbstractDeviceMessagePipe;
import me.iot.common.Callback;
import me.iot.common.msg.DeviceOtaMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.TopicConsts;
import me.iot.das.common.DasConfig;
import me.iot.das.common.NettyUtil;
import me.iot.das.common.event.OtaNewTaskEvent;
import me.iot.util.mq.Message;
import me.iot.util.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * @FileName :  MsgSender
 * @Author :  sylar
 * @CreateDate :  2017/11/08
 * @Description :  消息发送器:  将消息发给 channel
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
public class MsgSender extends AbstractDeviceMessagePipe {
    private static final Logger LOG = LoggerFactory.getLogger(MsgSender.class);

    @Autowired
    ApplicationContext ctx;

    @Autowired
    DasConfig dasConfig;

    @Autowired
    ChannelCache channelCache;

    @Override
    public void input(Callback<IMsg> callback) {
        //"iot.DmsToDas.xxxxxxxxx"
        String topic = TopicConsts.getTopicFromDmsToDas(dasConfig.getDasNodeId());

        try {
            dasConfig.getConsumer().subscribe(Lists.newArrayList(topic), new MessageListener() {
                @Override
                public void onSuccess(Message message) {
                    IMsg msg = convert(message.getContent());

                    switch (msg.getMsgType()) {
                        case DeviceOta:
                            ctx.publishEvent(new OtaNewTaskEvent(this, (DeviceOtaMsg) msg));
                            break;
                        default:
                            callback.onSuccess(msg);
                            break;
                    }

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
        sendMsg(msg);
    }

    public void sendMsg(IMsg msg) {
        String targetDeviceType = msg.getTargetDeviceType();
        String targetDeviceId = msg.getTargetDeviceId();

        if (Strings.isNullOrEmpty(targetDeviceType)) {
            LOG.warn("sendMsg error: targetDeviceType is null or empty");
            return;
        }

        if (Strings.isNullOrEmpty(targetDeviceId)) {
            LOG.warn("sendMsg error: targetDeviceId is null or empty");
            return;
        }

        //channel查找规则: deviceId 或者 deviceType + deviceId
        Channel channel = channelCache.get(targetDeviceId);
        if (channel == null) {
            channel = channelCache.get(targetDeviceType + targetDeviceId);
        }

        if (channel == null) {
            LOG.warn("sendMsg error. can't find channel for deviceId: {}", targetDeviceId);
            return;
        }

        NettyUtil.writeData(channel, msg);
        LOG.info("send message : {} -> {} \n message: {}", msg.getSourceDeviceId(), targetDeviceId, msg);
    }

}
