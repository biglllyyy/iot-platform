package me.iot.util.mq;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import me.iot.util.misc.NetUtils;

import java.util.Properties;

/**
 * File Name             :  AbstractClient
 * Author                :  sylar
 * Create Date           :  2017/11/7
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractClient implements IClient {

    protected String brokers;
    protected String groupId = "DEFAULT";
    protected String clientId = NetUtils.getHostMac();
    protected Properties properties = new Properties();

    @Override
    public void setBasicParameter(String brokers, String groupId, String clientId) {
        this.brokers = brokers;

        if (!Strings.isNullOrEmpty(groupId)) {
            this.groupId = groupId;
        }

        if (!Strings.isNullOrEmpty(clientId)) {
            this.clientId = clientId;
        }

        Preconditions.checkNotNull(this.brokers, "brokers is null");
        Preconditions.checkNotNull(this.groupId, "groupId is null");
        Preconditions.checkNotNull(this.clientId, "clientId is null");
    }

    @Override
    public void setProperties(Properties properties) {
        if (null != properties) {
            this.properties.putAll(properties);
        }
    }
}