package me.iot.util.disruptor;

public interface IMessaging {
    void stop();

    void publish(Object value);

    long getRemainBufferSize();
}
