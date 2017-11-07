package me.iot.util.mq.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.google.common.base.Charsets;
import me.iot.util.mq.AbstractConsumer;
import me.iot.util.mq.Message;
import me.iot.util.mq.MessageListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class RocketmqConsumer extends AbstractConsumer {

    protected DefaultMQPushConsumer consumer;

    public RocketmqConsumer(String brokers) {
        super(brokers);
    }

    public RocketmqConsumer(String brokers, Properties properties) {
        super(brokers, properties);
    }

    @Override
    public void subscribe(List<String> topics, MessageListener messageListener) {
        super.subscribe(topics, messageListener);

        try {
            initConsumer();
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    list.forEach(messageExt -> {
                        Message message = new Message(messageExt.getTopic(), new String(messageExt.getBody(), Charsets.UTF_8));
                        message.setTag(messageExt);
                        messageListener.onSuccess(message);
                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            String subExpression = "*";
//        if (topicFilters != null && topicFilters.length > 0) {
//            subExpression = Joiner.on("||").skipNulls().join(topicFilters);
//        }
            topics.forEach(topic -> {
                try {
                    consumer.subscribe(topic, subExpression);
                } catch (MQClientException e) {
                    e.printStackTrace();
                    messageListener.onFailure(e.getCause());
                }
            });

            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
            messageListener.onFailure(e.getCause());
        }
    }

    @Override
    public void unsubscribe() {

        if (consumer != null) {
            Enumeration<String> topics = consumer.getDefaultMQPushConsumerImpl().getRebalanceImpl().getTopicSubscribeInfoTable().keys();
            while (topics.hasMoreElements()) {
                consumer.unsubscribe(topics.nextElement());
            }
            consumer.shutdown();
        }
    }

    private void initConsumer() {
        consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(brokers);
        consumer.setConsumerGroup(properties.getProperty(ConsumerConfig.GROUP_ID_CONFIG, "DEFAULT"));
        consumer.setVipChannelEnabled(false);

        /*
         RocketMQ 广播消费模式是通过 setMessageModel 显示设置
         Kafka 广播消费是通过 consumer group id 来区分，不同组即可广播， 组内则是集群消费
        */
        String model = properties.getProperty("message.model", MessageModel.CLUSTERING.name()).toUpperCase();
        consumer.setMessageModel(MessageModel.valueOf(model));
    }
}
