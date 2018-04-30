package iot.util.mq;

import com.google.common.base.Preconditions;
import iot.util.misc.NetUtils;

import java.util.Properties;

/**
 * @author :  sylar
 * @FileName :  AbstractClient
 * @CreateDate :  2017/11/7
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractClient extends Properties implements IClient {

    protected String brokers;
    protected String groupId = "DEFAULT";
    protected String clientId = NetUtils.getHostMac();

    @Override
    public String getBrokers() {
        return brokers;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Properties getProperties() {
        return this;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void checkParameters() {
        Preconditions.checkNotNull(this.brokers, "brokers is null");
        Preconditions.checkNotNull(this.groupId, "groupId is null");
        Preconditions.checkNotNull(this.clientId, "clientId is null");
    }

}
