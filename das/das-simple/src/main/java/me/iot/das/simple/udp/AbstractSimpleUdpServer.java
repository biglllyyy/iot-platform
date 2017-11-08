package me.iot.das.simple.udp;

import io.netty.channel.ChannelPipeline;
import me.iot.das.common.NettyConst;
import me.iot.das.common.core.AbstractUdpServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sylar on 16/5/29.
 */
public abstract class AbstractSimpleUdpServer extends AbstractUdpServer {

    public static final String MSG_ENCODER_HANDLER_NAME = "msgEncoderHandler";
    public static final String MSG_DECODER_HANDLER_NAME = "msgDecoderHandler";

    @Autowired
    protected UdpDecoder udpDecoder;

    @Autowired
    protected UdpEncoder udpEncoder;

    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {
        //in
        pipeline.addLast(MSG_DECODER_HANDLER_NAME, udpDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);


        //out
        pipeline.addLast(MSG_ENCODER_HANDLER_NAME, udpEncoder);
    }

}
