package me.iot.dms;

import me.iot.common.msg.IMsg;

/**
 * @author :  sylar
 * @FileName :  IDeviceMessageService
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface IDeviceMessageService {

    /**
     * @param msg
     */
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
