package iot.dcp.mqtt.core;

import io.netty.channel.ChannelPipeline;
import iot.dcp.common.NettyConst;
import iot.dcp.common.core.AbstractTcpServer;
import iot.dcp.mqtt.protocol.codec.MqttDecoder;
import iot.dcp.mqtt.protocol.codec.MqttEncoder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author :  sylar
 * @fileName :  MqttServer
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @@CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class MqttServer extends AbstractTcpServer {

    final static protected String MQTT_ENCODER_HANDLER = "mqttEncoderHandler";
    final static protected String MQTT_DECODER_HANDLER = "mqttDecoderHandler";
    final static protected String DEVICE_MSG_TO_MQTT_MSG_ENCODER_HANDLER = "deviceMsgToMqttMsgEncoderHandler";
    final static protected String MQTT_MSG_TO_DEVICE_MSG_DECODER_HANDLER = "mqttMsgToDeviceMsgDecoderHandler";

    @Autowired
    protected DeviceMsgToMqttMsgEncoder deviceMsgToMqttMsgEncoder;

    @Autowired
    protected MqttMsgToDeviceMsgDecoder mqttMsgToDeviceMsgDecoder;

    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {
        //in
        pipeline.addLast(MQTT_DECODER_HANDLER, new MqttDecoder());
        pipeline.addLast(MQTT_MSG_TO_DEVICE_MSG_DECODER_HANDLER, mqttMsgToDeviceMsgDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);

        //out
        pipeline.addLast(MQTT_ENCODER_HANDLER, new MqttEncoder());
        pipeline.addLast(DEVICE_MSG_TO_MQTT_MSG_ENCODER_HANDLER, deviceMsgToMqttMsgEncoder);
    }
}
