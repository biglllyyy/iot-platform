package iot.common.msg;

/**
 * 设备连接消息
 * 协议解析时,在 decoder hanlder 中必须实现该消息的实例化
 */

/**
 * @author :  sylar
 * @FileName :
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
public class DeviceConnectionMsg extends AbstractDeviceMsg {

    /**
     * DAS集群节点实例编码
     */
    private String dcsNodeId;

    /**
     * 设备终端IP
     */
    private String terminalIp;

    /**
     * 连接状态
     */
    private boolean connected;

    public void setConnectInfo(String sourceDeviceType, String sourceDeviceId, String dcsNodeId, String terminalIp,
                               boolean connected) {
        this.sourceDeviceType = sourceDeviceType;
        this.sourceDeviceId = sourceDeviceId;

        this.dcsNodeId = dcsNodeId;
        this.dcsNodeId = dcsNodeId;
        this.terminalIp = terminalIp;
        this.connected = connected;
    }


    @Override
    public MsgType getMsgType() {
        return MsgType.DeviceConnection;
    }

    public String getDcsNodeId() {
        return dcsNodeId;
    }

    public void setDcsNodeId(String dcsNodeId) {
        this.dcsNodeId = dcsNodeId;
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
