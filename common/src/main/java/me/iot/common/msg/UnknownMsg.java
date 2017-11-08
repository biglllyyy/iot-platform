package me.iot.common.msg;

/**
 * 未知设备消息
 */
public class UnknownMsg extends AbstractDeviceMsg {
    private byte[] bytes;

    @Override
    public MsgType getMsgType() {
        return MsgType.Unknown;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
