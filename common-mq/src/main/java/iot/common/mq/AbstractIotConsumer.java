package iot.common.mq;

import com.lmax.disruptor.EventHandler;
import iot.util.disruptor.DisruptorHub;
import iot.util.disruptor.ValueEvent;
import iot.util.mq.IConsumer;
import iot.util.mq.Message;
import iot.util.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * File Name             :  AbstractIotConsumer
 * Author                :  sylar
 * Create Date           :  2018/4/18
 * Description           :  抽象的队列消息分发处理者：从MQ中订阅消息，分发处理。
 * 【当前设计由于使用到RocketMQ的tag特性，故MQ约定采用ROcketMQ。若要切换到kafka，须重新设计Topic】
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractIotConsumer {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 消费者待订阅的消息主题
     *
     * @return 消息主题
     */
    protected abstract String getTopic();

    /**
     * 消费者所在组
     *
     * @return 消费者组
     */
    protected abstract String getConsumerGroupId();

    /**
     * 处理MQ消息
     *
     * @param message 设备消息
     */
    protected abstract void onConsume(Message message);

    @Autowired
    protected MqAssistant mqAssistant;
    protected IConsumer consumer;
    private final DisruptorHub disruptorHub = new DisruptorHub(new Handler());

    @PostConstruct
    protected void onInit() {
        consumer = mqAssistant.createConsumer(getConsumerGroupId());
        consumer.subscribe(getTopic(), getTags(), new MessageListener() {
            @Override
            public void onSuccess(Message message) {
                LOG.info("received published msg.  topic:{}\n{}", message.getTopic(), message.getContent());
                disruptorHub.publish(message);
            }

            @Override
            public void onFailure(Throwable t) {
                LOG.warn(t.getMessage());
            }
        });
    }

    @PreDestroy
    protected void onDestroy() {
        disruptorHub.stop();
        consumer.unsubscribe();
        consumer = null;
    }

    /**
     * 订阅时关心的tag列表
     *
     * @return 消息tag列表
     */
    protected String[] getTags() {
        return null;
    }

    class Handler implements EventHandler<ValueEvent> {

        @Override
        public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {

            try {
                Object value = event.getValue();
                if (value != null && value instanceof Message) {
                    Message message = (Message) value;
                    onConsume(message);
                }
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
    }
}
