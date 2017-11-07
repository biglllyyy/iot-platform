package me.iot.util.mq;

public interface IFactory {

    IProducer createProducer(Provider provider);

    IConsumer createConsumer(Provider provider);
}
