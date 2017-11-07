package me.iot.util.mq;

public interface MessageListener {

    /**
     * 成功时通知
     *
     * @param message 消息
     */
    void onSuccess(Message message);

    /**
     * 失败时通知
     *
     * @param t 异常信息
     */
    void onFailure(Throwable t);

}
