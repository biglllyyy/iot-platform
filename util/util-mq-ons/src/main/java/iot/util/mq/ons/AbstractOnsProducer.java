package iot.util.mq.ons;

import com.google.common.base.Preconditions;
import iot.util.mq.AbstractProducer;

/**
 * File Name             :  AbstractOnsProducer
 * Author                :  sylar
 * Create Date           :  2018/4/11
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
public abstract class AbstractOnsProducer extends AbstractProducer implements IOnsClient {

    protected String accessKey;
    protected String secretKey;


    @Override
    public void checkParameters() {
        super.checkParameters();
        Preconditions.checkNotNull(getAccessKey(), "accessKey is null");
        Preconditions.checkNotNull(getSecretKey(), "secretKey is null");
    }

    @Override
    public String getAccessKey() {
        return accessKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
