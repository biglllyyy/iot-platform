package me.iot.dms;

import me.iot.common.msg.IMsg;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceMessageService {

    void sendMsg(IMsg msg);


//    /**
//     * 订阅设备消息(根据设备类型)
//     *
//     * @param callback    收到设备消息时的回调通知
//     * @param deviceTypes 设备类型编码列表,为空时订阅所有类型设备的消息
//     */
//    void subscribeMsgByDeviceTypes(Callback<IMsg> callback, List<String> deviceTypes);
//
//    /**
//     * 订阅设备消息
//     *
//     * @param callback  收到设备消息时的回调通知
//     * @param deviceIds 设备编码列表,为空时订阅所有设备的消息
//     */
//    void subscribeMsgByDeviceIds(Callback<IMsg> callback, List<String> deviceIds);
//
//    /**
//     * 取消订阅设备消息
//     *
//     * @param callback 收到设备消息时的回调通知
//     */
//    void unsubscribeMsg(Callback<IMsg> callback);

}
