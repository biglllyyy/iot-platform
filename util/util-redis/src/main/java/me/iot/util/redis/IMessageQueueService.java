package me.iot.util.redis;

public interface IMessageQueueService {

    boolean containsQueue(String queueName);

    long getRemainSize(String queueName);

    <Msg> void sendMessage(String queueName, Msg msg);

    <Msg> Msg receiveMessage(String queueName, Class<Msg> clazz);

}
