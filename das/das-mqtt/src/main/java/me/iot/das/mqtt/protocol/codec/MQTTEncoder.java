package me.iot.das.mqtt.protocol.codec;

import me.iot.das.mqtt.protocol.message.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.HashMap;
import java.util.Map;

public class MQTTEncoder extends MessageToByteEncoder<AbstractMessage> {

    @SuppressWarnings("rawtypes")
    private Map<Byte, AbstractDemuxEncoder> m_encoderMap = new HashMap<Byte, AbstractDemuxEncoder>();

    public MQTTEncoder() {
        m_encoderMap.put(AbstractMessage.CONNECT, new ConnectEncoderAbstract());
        m_encoderMap.put(AbstractMessage.CONNACK, new ConnAckEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PUBLISH, new PublishEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PUBACK, new PubAckEncoderAbstract());
        m_encoderMap.put(AbstractMessage.SUBSCRIBE, new SubscribeEncoderAbstract());
        m_encoderMap.put(AbstractMessage.SUBACK, new SubAckEncoderAbstract());
        m_encoderMap.put(AbstractMessage.UNSUBSCRIBE, new UnsubscribeEncoderAbstract());
        m_encoderMap.put(AbstractMessage.DISCONNECT, new DisconnectEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PINGREQ, new PingReqEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PINGRESP, new PingRespEncoderAbstract());
        m_encoderMap.put(AbstractMessage.UNSUBACK, new UnsubAckEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PUBCOMP, new PubCompEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PUBREC, new PubRecEncoderAbstract());
        m_encoderMap.put(AbstractMessage.PUBREL, new PubRelEncoderAbstract());
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void encode(ChannelHandlerContext chc, AbstractMessage msg, ByteBuf bb) throws Exception {
        AbstractDemuxEncoder encoder = m_encoderMap.get(msg.getMessageType());
        if (encoder == null) {
            throw new CorruptedFrameException("Can't find any suitable decoder for message type: " + msg.getMessageType());
        }
        encoder.encode(chc, msg, bb);
    }
}
