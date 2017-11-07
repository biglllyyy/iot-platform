package me.iot.das.common.bean;

import com.google.common.base.Strings;
import io.netty.channel.Channel;
import me.iot.das.common.DasConfig;
import me.iot.das.common.NettyUtil;
import me.iot.das.common.event.OtaNewTaskEvent;
import me.iot.common.AbstractCacheMsgHandler;
import me.iot.common.msg.DeviceOtaMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.common.usual.DasCacheKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * 消息发送器:  将消息发给 channel
 */
@Component
public class MsgSender extends AbstractCacheMsgHandler<IMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(MsgSender.class);

    @Autowired
    ApplicationContext ctx;

    @Autowired
    DasConfig dasConfig;

    @Autowired
    ChannelCache channelCache;

    @Override
    public IMsg getMsgFromCache() {
        try {
            String queueName = DasCacheKeys.getMqsKeyFromDmsToDas(dasConfig.getDasNodeId());
            CacheMsgWrap wrap = dasConfig.getMqs().receiveMessage(queueName, CacheMsgWrap.class);


            if (wrap == null) {
                return null;
            }

            IMsg msg = wrap.getMsg();
            if (msg == null) {
                return null;
            }

            LOG.info("getMsgFromCache:{}", msg);

            switch (msg.getMsgType()) {
                case DeviceOta:
                    ctx.publishEvent(new OtaNewTaskEvent(this, (DeviceOtaMsg) msg));
                    return null;
                default:
                    return msg;
            }
        } catch (Exception e) {
            LOG.warn("getMsgFromCache error:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void handleMsg(IMsg msg) {
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
