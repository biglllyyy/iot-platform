package me.iot.das.simple.udp;

import io.netty.channel.ChannelPipeline;
import me.iot.das.common.NettyConst;
import me.iot.das.common.core.AbstractUdpServer;
import org.springframework.beans.factory.annotation.Autowired;

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
