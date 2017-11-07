package me.iot.das.mqtt.core;

import me.iot.das.mqtt.pojo.MqttPacketWrap;
import me.iot.common.msg.IMsg;

import java.util.List;

/**
 * Created by sylar on 16/5/29.
 */
public interface IMqttMsgResolver {

    List<IMsg> wrapToMsg(MqttPacketWrap wrap);

    MqttPacketWrap msgToWrap(IMsg msg);
}
