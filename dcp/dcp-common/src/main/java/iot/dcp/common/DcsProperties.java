package iot.dcp.common;

import com.google.common.collect.Maps;
import iot.util.misc.NetUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author :  sylar
 * @FileName :  DcsProperties
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
@ConfigurationProperties(prefix = "dcs")
public class DcsProperties {

    /**
     * dcs进程的唯一标识
     */
    private String nodeId = NetUtils.getHostMac();

    /**
     * socket监听地址。默认在主机的所有地址上监听
     */
    private String host;

    /**
     * socket监听端口
     */
    private int port = 10001;

    /**
     * 链路空闲检查阀值。以秒为单位，须大于0。
     */
    private int idleTime = 0;

    /**
     * 扩展的dcs配置参数
     */
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
