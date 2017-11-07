package me.iot.util.mq;

public interface IFactory {

    IProducer createProducer(Provider provider, String brokers);

    IConsumer createConsumer(Provider provider, String brokers);
}
