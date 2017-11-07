package me.iot.util.rocketmq.msg;

import java.util.List;

/**
 * Created by sylar on 2017/1/5.
 */
public interface IRocketMsgListener {
    void onSuccess(List<RocketMsg> messages);

    void onFaild(Throwable throwable);
}
