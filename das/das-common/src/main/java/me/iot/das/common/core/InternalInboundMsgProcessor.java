package me.iot.das.common.core;

import com.lmax.disruptor.EventHandler;
import io.netty.channel.Channel;
import me.iot.das.common.DasConfig;
import me.iot.das.common.NettyUtil;
import me.iot.das.common.bean.ChannelCache;
import me.iot.das.common.bean.MsgThrower;
import me.iot.das.common.event.ChannelMsgEvent;
import me.iot.common.msg.DeviceConnectionMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.msg.MsgType;
import me.iot.util.disruptor.IMessaging;
import me.iot.util.disruptor.LmaxDiscuptorMessaging;
import me.iot.util.disruptor.ValueEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class InternalInboundMsgProcessor implements ApplicationListener<ChannelMsgEvent>, EventHandler<ValueEvent> {
    private final static Logger LOG = LoggerFactory.getLogger(InternalInboundMsgProcessor.class);

    @Autowired
    DasConfig dasConfig;

    @Autowired
    private ChannelCache channelCache;

    @Autowired
    private MsgThrower msgThrower;

    @Autowired
    private InboundMsgProcessor inboundMsgProcessor;

    private IMessaging messaging;

    @PostConstruct
    private void init() {
        messaging = new LmaxDiscuptorMessaging(this);
    }

    @Override
    public void onApplicationEvent(ChannelMsgEvent event) {
        messaging.publish(event);
    }

    @Override
    public void onEvent(ValueEvent valueEvent, long sequence, boolean endOfBatch) throws Exception {

        Object obj = valueEvent.getValue();
        if (obj instanceof ChannelMsgEvent) {
            ChannelMsgEvent cmEvent = (ChannelMsgEvent) obj;

            Channel channel = cmEvent.getChannel();
            IMsg msg = cmEvent.getMsg();

            boolean needThrowUp = true;
            MsgType msgType = msg.getMsgType();

            if (NettyUtil.isUdpChannel(channel)) {
                String clientId = msg.getSourceDeviceType() + msg.getSourceDeviceId();
                channelCache.put(clientId, channel);
            }

            switch (msgType) {
                case DeviceConnection:
                    needThrowUp = onDeviceConnection(channel, (DeviceConnectionMsg) msg);
                    break;
                default:
                    if (inboundMsgProcessor != null) {
                        needThrowUp = inboundMsgProcessor.processInboundMsg(msg);
                    }
                    break;
            }

            if (needThrowUp) {
                msgThrower.sendToQueue(msg);
            }
        }
    }

    private boolean onDeviceConnection(Channel channel, DeviceConnectionMsg msg) {
        String clientId = msg.getSourceDeviceId();

        boolean isTcp = NettyUtil.isTcpChannel(channel);
        if (isTcp) {
            if (msg.isConnected()) {
                //当建立新连接时,为channel加上ClientId属性标记,并将channel加入本地缓存
                NettyUtil.setDeviceType(channel, msg.getSourceDeviceType());
                NettyUtil.setClientId(channel, clientId);
                channelCache.put(clientId, channel);
                LOG.debug("onDeviceConnection connected:{}", msg.getSourceDeviceId());
            } else {
                if (channelCache.get(clientId) == channel) {
                    //当连接断开时,从本地缓存里清除
                    channelCache.remove(clientId);
                    LOG.debug("onDeviceConnection disconnected:{}", msg.getSourceDeviceId());
                }
            }
            return true;
        }

        boolean isUdp = NettyUtil.isUdpChannel(channel);
        if (isUdp) {
            if (msg.isConnected()) {
                //TODO
            } else {
                //TODO
            }
            return true;
        }

        return false;
    }

}
