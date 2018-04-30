package iot.dcp.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author :  sylar
 * @FileName :  ServerConnectEvent
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
public class ServerConnectEvent extends ApplicationEvent {

    private String dcsNodeId;
    private String ip;
    private int port;
    private boolean connected;

    public ServerConnectEvent(Object source, String dcsNodeId, String ip, int port, boolean connected) {
        super(source);
        this.dcsNodeId = dcsNodeId;
        this.ip = ip;
        this.port = port;
        this.connected = connected;
    }

    public String getDcsNodeId() {
        return dcsNodeId;
    }

    public void setDcsNodeId(String dcsNodeId) {
        this.dcsNodeId = dcsNodeId;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
