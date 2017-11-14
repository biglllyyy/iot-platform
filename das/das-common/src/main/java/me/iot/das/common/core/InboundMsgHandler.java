package me.iot.das.common.core;

import com.google.common.base.Strings;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import me.iot.common.msg.DeviceConnectionMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.DeviceGuid;
import me.iot.das.common.DasConfig;
import me.iot.das.common.NettyUtil;
import me.iot.das.common.bean.ChannelCache;
import me.iot.das.common.event.ChannelMsgEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author :  sylar
 * @FileName :  InboundMsgHandler
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
@ChannelHandler.Sharable
public class InboundMsgHandler extends SimpleChannelInboundHandler<IMsg> {
    private final static Logger LOG = LoggerFactory.getLogger(InboundMsgHandler.class);

    @Autowired
    DasConfig dasConfig;
    @Autowired
    ApplicationContext ctx;
    @Autowired
    private ChannelCache channelCache;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMsg msg) throws Exception {
        LOG.info("received message : {} <- {} \n message: {}", msg.getTargetDeviceId(), msg.getSourceDeviceId(), msg);

        if (isValidMsg(msg)) {
            throwToInboundProcessor(ctx.channel(), msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        onChannelInactive(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (NettyUtil.isTcpChannel(ctx.channel())
                && evt instanceof IdleStateEvent
                && IdleState.READER_IDLE == ((IdleStateEvent) evt).state()) {
            Channel channel = ctx.channel();
            NettyUtil.closeChannel(channel, false);
            LOG.warn("close idle channel for channel: {} and clientId: {}", channel, NettyUtil.getClientId(channel));
            return;
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        LOG.error("exceptionCaught on channel :{}", cause.getMessage());
        LOG.error("clientId:{}", NettyUtil.getClientId(ctx.channel()));

        NettyUtil.closeChannel(ctx.channel(), false);
    }

    /**
     * ==================================================================================================================================
     */

    private boolean isValidMsg(IMsg msg) {
        if (msg == null || Strings.isNullOrEmpty(msg.getSourceDeviceId()) || Strings.isNullOrEmpty(msg
                .getSourceDeviceType())) {
            LOG.error("invalid message: {}", msg);
            return false;
        }
        return true;
    }


    private void onChannelInactive(Channel channel) {

        if (!NettyUtil.isTcpChannel(channel)) {
            return;
        }

        //tcp 链路，才发送设备断开消息

        String clientId = NettyUtil.getClientId(channel);
        if (Strings.isNullOrEmpty(clientId)) {
            return;
        }

        if (channelCache.get(clientId) != channel) {
            return;
        }

        String deviceType = NettyUtil.getDeviceType(channel);
        String deviceId = clientId;
        if (DeviceGuid.checkValid(clientId)) {
            //若是mqtt deviceId
            deviceId = DeviceGuid.fromString(clientId).getDeviceNumber();
        }

        DeviceConnectionMsg msg = new DeviceConnectionMsg();
        msg.setSourceDeviceType(deviceType);
        msg.setSourceDeviceId(deviceId);
        msg.setDasNodeId(dasConfig.getDasNodeId());
        msg.setTerminalIp(NettyUtil.getChannelRemoteIP(channel));
        msg.setConnected(false);

        throwToInboundProcessor(channel, msg);
    }


    private void throwToInboundProcessor(Channel channel, IMsg msg) {
        ctx.publishEvent(new ChannelMsgEvent(this, channel, msg));
    }

}
