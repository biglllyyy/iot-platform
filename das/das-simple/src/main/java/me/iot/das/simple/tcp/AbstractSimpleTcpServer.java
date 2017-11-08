package me.iot.das.simple.tcp;

import me.iot.das.common.NettyConst;
import me.iot.das.common.core.AbstractTcpServer;
import me.iot.das.simple.SimpleDecoder;
import me.iot.das.simple.SimpleEncoder;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
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
public abstract class AbstractSimpleTcpServer extends AbstractTcpServer {

    public static final String MSG_ENCODER_HANDLER_NAME = "msgEncoderHandler";
    public static final String MSG_DECODER_HANDLER_NAME = "msgDecoderHandler";
    public static final String FRAME_DECODER_HANDLER_NAME = "frameDecoderHandler";
    @Autowired
    protected SimpleDecoder simpleDecoder;
    @Autowired
    protected SimpleEncoder simpleEncoder;

    /**
     * 协议帧解码器
     * 参见 netty 默认提供的 FrameDecoder
     * DelimiterBasedFrameDecoder       基于分隔符
     * FixedLengthFrameDecoder          基于固定消息长度
     * LengthFieldBasedFrameDecoder     基于消息长度
     * LineBasedFrameDecoder            基于文本换行
     *
     * @return 协议帧解码器
     */
    protected abstract ByteToMessageDecoder getFrameDecoder();

    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {
        //in
        pipeline.addLast(FRAME_DECODER_HANDLER_NAME, getFrameDecoder());
        pipeline.addLast(MSG_DECODER_HANDLER_NAME, simpleDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);

        //out
        pipeline.addLast(MSG_ENCODER_HANDLER_NAME, simpleEncoder);
    }

}
