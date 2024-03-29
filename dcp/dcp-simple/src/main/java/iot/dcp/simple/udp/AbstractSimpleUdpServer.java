package iot.dcp.simple.udp;

import io.netty.channel.ChannelPipeline;
import iot.dcp.common.NettyConst;
import iot.dcp.common.core.AbstractUdpServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author :  sylar
 * @FileName :  AbstractSimpleUdpServer
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
