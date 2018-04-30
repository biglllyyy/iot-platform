package iot.common.msg;

/**
 * DAS 服务器节点连接(上/下线)消息
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
public class DcsConnectionMsg extends AbstractDeviceMsg {

    /**
     * DAS集群节点实例编码
     */
    private String dcsNodeId;

    /**
     * 服务器节点连接状态(上/下线)
     */
    private boolean connected;

    private String ip;

    private int port;

    public void setDcsInfo(String dcsNodeId, String ip, int port, boolean connected) {
        this.dcsNodeId = dcsNodeId;
        this.ip = ip;
        this.port = port;
        this.connected = connected;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.DcsConnection;
    }

    public String getDcsNodeId() {
        return dcsNodeId;
    }

    public void setDcsNodeId(String dcsNodeId) {
        this.dcsNodeId = dcsNodeId;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
