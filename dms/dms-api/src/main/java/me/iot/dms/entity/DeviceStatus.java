package me.iot.dms.entity;

import java.io.Serializable;

/**
 * Created by sylar on 16/6/2.
 */
public class DeviceStatus implements Serializable {

    private String deviceId;

    private String nodeId;

    private String terminalIp;

    private boolean connected;


    public DeviceStatus() {
    }

    public DeviceStatus(String deviceId, String nodeId, String terminalIp, boolean connected) {
        this.deviceId = deviceId;
        this.nodeId = nodeId;
        this.terminalIp = terminalIp;
        this.connected = connected;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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
