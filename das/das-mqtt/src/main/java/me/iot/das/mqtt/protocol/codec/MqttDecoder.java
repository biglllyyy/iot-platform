package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.util.AttributeKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName             :  MqttDecoder
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
public class MqttDecoder extends ByteToMessageDecoder {

    /**
     * 3 = 3.1, 4 = 3.1.1
     */
    static final AttributeKey<Integer> PROTOCOL_VERSION = AttributeKey.valueOf("version");

    private final Map<Byte, AbstractDemuxDecoder> m_decoderMap = new HashMap<Byte, AbstractDemuxDecoder>();

    public MqttDecoder() {
        m_decoderMap.put(AbstractMessage.CONNECT, new ConnectDecoderAbstract());
        m_decoderMap.put(AbstractMessage.CONNACK, new ConnAckDecoderAbstract());
        m_decoderMap.put(AbstractMessage.PUBLISH, new PublishDecoderAbstract());
        m_decoderMap.put(AbstractMessage.PUBACK, new PubAckDecoderAbstractAbstractMessageIDDecoder());
        m_decoderMap.put(AbstractMessage.SUBSCRIBE, new SubscribeDecoderAbstract());
        m_decoderMap.put(AbstractMessage.SUBACK, new SubAckDecoderAbstract());
        m_decoderMap.put(AbstractMessage.UNSUBSCRIBE, new UnsubscribeDecoderAbstract());
        m_decoderMap.put(AbstractMessage.DISCONNECT, new DisconnectDecoderAbstract());
        m_decoderMap.put(AbstractMessage.PINGREQ, new PingReqDecoderAbstract());
        m_decoderMap.put(AbstractMessage.PINGRESP, new PingRespDecoderAbstract());
        m_decoderMap.put(AbstractMessage.UNSUBACK, new UnsubAckDecoderAbstractAbstractMessageIDDecoder());
        m_decoderMap.put(AbstractMessage.PUBCOMP, new PubCompDecoderAbstractAbstractMessageIDDecoder());
        m_decoderMap.put(AbstractMessage.PUBREC, new PubRecDecoderAbstractAbstractMessageIDDecoder());
        m_decoderMap.put(AbstractMessage.PUBREL, new PubRelDecoderAbstract());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        if (!CodecUtil.checkHeaderAvailability(in)) {
            in.resetReaderIndex();
            return;
        }
        in.resetReaderIndex();

        byte messageType = CodecUtil.readMessageType(in);

        AbstractDemuxDecoder decoder = m_decoderMap.get(messageType);
        if (decoder == null) {
            throw new CorruptedFrameException("Can't find any suitable decoder for message type: " + messageType);
        }
        decoder.decode(ctx, in, out);
    }
}
