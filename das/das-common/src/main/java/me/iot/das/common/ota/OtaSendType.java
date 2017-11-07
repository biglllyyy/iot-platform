package me.iot.das.common.ota;

/**
 * Created by sylar on 16/6/1.
 */
public enum OtaSendType {

    /**
     * 每包回应后才发送下一包
     */
    EachPacketResponse,

    /**
     * 发包时不回应
     */
    LastPacketResponse,

}
