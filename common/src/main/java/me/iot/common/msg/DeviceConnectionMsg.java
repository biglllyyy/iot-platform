package me.iot.common.msg;

/**
 * 设备连接消息
 * 协议解析时,在 decoder hanlder 中必须实现该消息的实例化
 */
public class DeviceConnectionMsg extends AbsDeviceMsg {

    /**
     * DAS集群节点实例编码
     */
    private String dasNodeId;

    /**
     * 设备终端IP
     */
    private String terminalIp;

    /**
     * 连接状态
     */
    private boolean connected;

    public void setConnectInfo(String sourceDeviceType, String sourceDeviceId, String dasNodeId, String terminalIp, boolean connected) {
        this.sourceDeviceType = sourceDeviceType;
        this.sourceDeviceId = sourceDeviceId;

        this.dasNodeId = dasNodeId;
        this.dasNodeId = dasNodeId;
        this.terminalIp = terminalIp;
        this.connected = connected;
    }


    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceConnection;
    }

    public String getDasNodeId() {
        return dasNodeId;
    }

    public void setDasNodeId(String dasNodeId) {
        this.dasNodeId = dasNodeId;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}
