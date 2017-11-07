package me.iot.das.mqtt.core;

import me.iot.das.common.NettyConst;
import me.iot.das.common.core.AbsTcpServer;
import me.iot.das.mqtt.protocol.codec.MQTTDecoder;
import me.iot.das.mqtt.protocol.codec.MQTTEncoder;
import io.netty.channel.ChannelPipeline;
import org.springframework.beans.factory.annotation.Autowired;

public class MqttServer extends AbsTcpServer {

    public static final String MQTT_ENCODER_HANDLER_NAME = "mqttEncoderHandler";
    public static final String MQTT_DECODER_HANDLER_NAME = "mqttDecoderHandler";

    public static final String M2M_ENCODER_HANDLER_NAME = "m2mEncoderHandler";
    public static final String M2M_DECODER_HANDLER_NAME = "m2mDecoderHandler";

    @Autowired
    protected DeviceMsgToMqttMsgEncoder deviceMsgToMqttMsgEncoder;

    @Autowired
    protected MqttMsgToDeviceMsgDecoder mqttMsgToDeviceMsgDecoder;

    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {
        //in
        pipeline.addLast(MQTT_DECODER_HANDLER_NAME, new MQTTDecoder());
        pipeline.addLast(M2M_DECODER_HANDLER_NAME, mqttMsgToDeviceMsgDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);

        //out
        pipeline.addLast(MQTT_ENCODER_HANDLER_NAME, new MQTTEncoder());
        pipeline.addLast(M2M_ENCODER_HANDLER_NAME, deviceMsgToMqttMsgEncoder);

    }

}
