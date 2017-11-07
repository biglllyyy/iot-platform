package me.iot.util.rocketmq;

import me.iot.util.rocketmq.msg.IRocketMsgListener;

/**
 * Created by sylar on 2017/1/5.
 */
public interface IConsumer {

    /**
     * 订阅消息
     *
     * @param topic        消息主题
     * @param topicFilters 主题过滤条件
     * @param listener     收到消息时的回调
     */
    void subscribe(String topic, String[] topicFilters, IRocketMsgListener listener);

    /**
     * 取消订阅
     */
    void unsubscribe();
}
