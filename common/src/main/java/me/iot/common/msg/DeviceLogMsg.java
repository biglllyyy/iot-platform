package me.iot.common.msg;

/**
 * 设备上报的日志消息
 */
public class DeviceLogMsg extends AbsDeviceMsg {
    private String logType;
    private String logContent;

    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceLog;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
