package me.iot.das.common;

import com.google.common.collect.Maps;
import me.iot.util.misc.NetUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by sylar on 16/5/18.
 */
@ConfigurationProperties(prefix = "das")
public class DasProperties {
    private String nodeId;
    private String host = "127.0.0.1";
    private int port = 10001;
    private int idleTime = 0;
    private Map<String, String> params = Maps.newLinkedHashMap();

    public String getParam(String paramKey) {
        if (paramKey == null || params == null) {
            return null;
        }
        return params.get(paramKey);
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        nodeId = NetUtils.getHostMac() + "_" + port;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
