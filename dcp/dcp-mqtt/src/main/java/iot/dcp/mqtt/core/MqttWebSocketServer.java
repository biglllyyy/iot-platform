package iot.dcp.mqtt.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import iot.dcp.common.NettyConst;
import iot.dcp.mqtt.protocol.codec.MqttDecoder;
import iot.dcp.mqtt.protocol.codec.MqttEncoder;

import java.util.List;

public class MqttWebSocketServer extends MqttServer {

    final static protected String HTTP_ENCODER_HANDLER = "httpEncoder";
    final static protected String HTTP_DECODER_HANDLER = "httpDecoder";

    final static protected String WEBSOCKET_TO_BYTEBUF_DECODER_HANDLER = "websocketToBytebufDecoder";
    final static protected String BYTEBUF_TO_WEBSOCKET_TO_ENCODER_HANDLER = "bytebufToWebsocketEncoder";

    final static protected String AGGREGATOR_ENCODER_HANDLER = "aggregator";
    final static protected String WEBSOCKET_DECODER_HANDLER = "webSocketDecoderHandler";

    final static protected String WEBSOCKET_PATH = "/mqtt";
    final static protected String SUB_PROTOCOLS = "mqttv3.1.1";


    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {

//        pipeline.addLast(HTTP_ENCODER_HANDLER, new HttpResponseEncoder())
//                .addLast(HTTP_DECODER_HANDLER, new HttpRequestDecoder())
//                .addLast(AGGREGATOR_ENCODER_HANDLER, new HttpObjectAggregator(65536))
//                .addLast(WEBSOCKET_DECODER_HANDLER, new WebSocketServerProtocolHandler(WEBSOCKET_PATH, SUB_PROTOCOLS))
//                .addLast(WEBSOCKET_TO_BYTEBUF_DECODER_HANDLER, new WebSocketFrameToByteBufDecoder())
//                .addLast(BYTEBUF_TO_WEBSOCKET_TO_ENCODER_HANDLER, new ByteBufToWebSocketFrameEncoder())
//                .addLast(MQTT_DECODER_HANDLER, new MqttDecoder())
//                .addLast(MQTT_ENCODER_HANDLER, new MqttEncoder())
//                .addLast(DEVICE_MSG_TO_MQTT_MSG_ENCODER_HANDLER, deviceMsgToMqttMsgEncoder);

        //in
        pipeline.addLast(HTTP_DECODER_HANDLER, new HttpRequestDecoder());
        pipeline.addLast(AGGREGATOR_ENCODER_HANDLER, new HttpObjectAggregator(65536));
        pipeline.addLast(WEBSOCKET_DECODER_HANDLER, new WebSocketServerProtocolHandler(WEBSOCKET_PATH, SUB_PROTOCOLS));
        pipeline.addLast(WEBSOCKET_TO_BYTEBUF_DECODER_HANDLER, new WebSocketFrameToByteBufDecoder());
        pipeline.addLast(MQTT_DECODER_HANDLER, new MqttDecoder());
        pipeline.addLast(MQTT_MSG_TO_DEVICE_MSG_DECODER_HANDLER, mqttMsgToDeviceMsgDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);

        //out
        pipeline.addLast(HTTP_ENCODER_HANDLER, new HttpResponseEncoder());
        pipeline.addLast(BYTEBUF_TO_WEBSOCKET_TO_ENCODER_HANDLER, new ByteBufToWebSocketFrameEncoder());
        pipeline.addLast(MQTT_ENCODER_HANDLER, new MqttEncoder());
        pipeline.addLast(DEVICE_MSG_TO_MQTT_MSG_ENCODER_HANDLER, deviceMsgToMqttMsgEncoder);
    }


    static class WebSocketFrameToByteBufDecoder extends MessageToMessageDecoder<BinaryWebSocketFrame> {
        @Override
        protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame frame, List<Object> out) throws Exception {
            //convert the frame to a ByteBuf
            ByteBuf bb = frame.content();
            bb.retain();
            out.add(bb);
        }
    }

    static class ByteBufToWebSocketFrameEncoder extends MessageToMessageEncoder<ByteBuf> {
        @Override
        protected void encode(ChannelHandlerContext chc, ByteBuf bb, List<Object> out) throws Exception {
            //convert the ByteBuf to a WebSocketFrame
            BinaryWebSocketFrame result = new BinaryWebSocketFrame();
            result.content().writeBytes(bb);
            out.add(result);
        }
    }

}
