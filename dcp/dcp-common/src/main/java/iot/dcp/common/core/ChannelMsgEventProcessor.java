package iot.dcp.common.core;

import com.lmax.disruptor.EventHandler;
import io.netty.channel.Channel;
import iot.common.msg.DeviceConnectionMsg;
import iot.common.msg.IMsg;
import iot.common.msg.MsgType;
import iot.dcp.common.DcsAutoConfiguration;
import iot.dcp.common.NettyUtil;
import iot.dcp.common.bean.ChannelCache;
import iot.dcp.common.bean.DcsToDmsProducer;
import iot.dcp.common.event.ChannelMsgEvent;
import iot.util.disruptor.DisruptorHub;
import iot.util.disruptor.ValueEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author :  sylar
 * @FileName :  InternalInboundMsgProcessor
 * @CreateDate :  2017/11/08
 * @Description :  ChannelMsgEvent 事件处理器
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
public class ChannelMsgEventProcessor implements ApplicationListener<ChannelMsgEvent>, EventHandler<ValueEvent> {
    private final static Logger LOG = LoggerFactory.getLogger(ChannelMsgEventProcessor.class);

    @Autowired
    DcsAutoConfiguration dcsConfig;

    @Autowired
    private ChannelCache channelCache;

    @Autowired
    private DcsToDmsProducer dcsToDmsProducer;

    @Autowired
    private IResponder IResponder;

    private DisruptorHub disruptorHub;

    @PostConstruct
    private void onInit() {
        disruptorHub = new DisruptorHub(this);
    }

    @PreDestroy
    private void onDestory() {
        disruptorHub.stop();
        disruptorHub = null;
    }

    @Override
    public void onApplicationEvent(ChannelMsgEvent event) {
        disruptorHub.publish(event);
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
                    if (IResponder != null) {
                        needThrowUp = IResponder.onRespond(msg);
                    }
                    break;
            }

            if (needThrowUp) {
                dcsToDmsProducer.publish(msg);
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
