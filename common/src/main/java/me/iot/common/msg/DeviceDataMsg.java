package me.iot.common.msg;

/**
 * 设备实时数据消息
 */

/**
 * @author :  sylar
 * @FileName :  DeviceDataMsg
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
public class DeviceDataMsg extends AbstractDeviceMsg {

    private long timestamp = System.currentTimeMillis();

    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
