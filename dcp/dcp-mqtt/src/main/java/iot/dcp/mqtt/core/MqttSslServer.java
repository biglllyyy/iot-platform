package iot.dcp.mqtt.core;

import com.google.common.base.Preconditions;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.SslHandler;
import iot.dcp.common.NettyConst;
import iot.dcp.mqtt.protocol.codec.MqttDecoder;
import iot.dcp.mqtt.protocol.codec.MqttEncoder;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.InputStream;
import java.security.KeyStore;

public class MqttSslServer extends MqttServer {
    private static final String MQTT_SSL_HANDLER = "sslHandler";
    private static final String PARAM_SSL_JKS_PATH = "SslJksPath";
    private static final String PARAM_SSL_STORE_PASSWORD = "SslKeyStorePassword";
    private static final String PARAM_SSL_MANAGER_PASSWORD = "SslKeyManagerPassword";

    private String jksPath, storePwd, managerPwd;

    @Override
    protected void initAndCheckProperties() {
        super.initAndCheckProperties();
        jksPath = dcsProperties.getParam(PARAM_SSL_JKS_PATH);
        storePwd = dcsProperties.getParam(PARAM_SSL_STORE_PASSWORD);
        managerPwd = dcsProperties.getParam(PARAM_SSL_MANAGER_PASSWORD);

        Preconditions.checkNotNull(jksPath, "dcs.SslJksPath can not be null");
        Preconditions.checkNotNull(jksPath, "dcs.SslKeyStorePassword can not be null");
        Preconditions.checkNotNull(jksPath, "dcs.SslKeyManagerPassword can not be null");
    }

    @Override
    protected void buildChannelHandler(ChannelPipeline pipeline) {

        SslHandler sslHandler = null;
        try {
            sslHandler = createSslHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Preconditions.checkNotNull(sslHandler, "sslHandler is null");

        //in
        pipeline.addLast(MQTT_DECODER_HANDLER, new MqttDecoder());
        pipeline.addLast(MQTT_MSG_TO_DEVICE_MSG_DECODER_HANDLER, mqttMsgToDeviceMsgDecoder);
        pipeline.addLast(NettyConst.INBOUND_MSG_HANDLER_NAME, inboundMsgHandler);

        //out
        pipeline.addLast(MQTT_SSL_HANDLER, sslHandler);
        pipeline.addLast(MQTT_ENCODER_HANDLER, new MqttEncoder());
        pipeline.addLast(DEVICE_MSG_TO_MQTT_MSG_ENCODER_HANDLER, deviceMsgToMqttMsgEncoder);

    }

    private SslHandler createSslHandler() throws Exception {
        InputStream jksInputStream = getClass().getClassLoader().getResourceAsStream(jksPath);
        SSLContext serverContext = SSLContext.getInstance("TLS");

        final KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(jksInputStream, storePwd.toCharArray());

        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, managerPwd.toCharArray());

        serverContext.init(kmf.getKeyManagers(), null, null);

        SSLEngine engine = serverContext.createSSLEngine();
        engine.setUseClientMode(false);
        return new SslHandler(engine);
    }
}
