package iot.dcp.mqtt.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.MessageToByteEncoder;
import iot.dcp.mqtt.protocol.message.AbstractMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  sylar
 * @FileName :
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
public class MqttEncoder extends MessageToByteEncoder<AbstractMessage> {

    @SuppressWarnings("rawtypes")
    private Map<Byte, AbstractDemuxEncoder> mEncoderMap = new HashMap<Byte, AbstractDemuxEncoder>();

    public MqttEncoder() {
        mEncoderMap.put(AbstractMessage.CONNECT, new ConnectEncoderAbstract());
        mEncoderMap.put(AbstractMessage.CONNACK, new ConnAckEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PUBLISH, new PublishEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PUBACK, new PubAckEncoderAbstract());
        mEncoderMap.put(AbstractMessage.SUBSCRIBE, new SubscribeEncoderAbstract());
        mEncoderMap.put(AbstractMessage.SUBACK, new SubAckEncoderAbstract());
        mEncoderMap.put(AbstractMessage.UNSUBSCRIBE, new UnsubscribeEncoderAbstract());
        mEncoderMap.put(AbstractMessage.DISCONNECT, new DisconnectEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PINGREQ, new PingReqEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PINGRESP, new PingRespEncoderAbstract());
        mEncoderMap.put(AbstractMessage.UNSUBACK, new UnsubAckEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PUBCOMP, new PubCompEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PUBREC, new PubRecEncoderAbstract());
        mEncoderMap.put(AbstractMessage.PUBREL, new PubRelEncoderAbstract());
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void encode(ChannelHandlerContext chc, AbstractMessage msg, ByteBuf bb) throws Exception {
        AbstractDemuxEncoder encoder = mEncoderMap.get(msg.getMessageType());
        if (encoder == null) {
            throw new CorruptedFrameException("Can't find any suitable decoder for message type: " + msg
                    .getMessageType());
        }
        encoder.encode(chc, msg, bb);
    }
}
