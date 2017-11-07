package me.iot.util.redis;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.nio.charset.Charset;

/**
 * Created by sylar on 2017/2/8.
 */
public abstract class AbstractMessageListener implements MessageListener {
    private final static Charset CHARSET = Charsets.UTF_8;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected abstract void handleMessage(String topic, String jsonMsg);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(message.getChannel(), CHARSET);
        String msg = new String(message.getBody(), CHARSET);

        try {
            handleMessage(topic, msg);
        } catch (Exception e) {
            LOG.warn("handleMessage error:{}", e.getMessage());
        }
    }
}
