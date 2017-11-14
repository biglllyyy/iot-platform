package me.cloud.iot.store.dustbin.data.config;

import me.iot.dms.Dms;
import me.iot.dms.IDeviceManageService;
import me.iot.util.rocketmq.IFactory;
import me.iot.util.rocketmq.RocketMQUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author :  sylar
 * @FileName :  DustbinConfig
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
@Configuration
public class DustbinConfig {

    @Value("${zookeeper.connectString}")
    String zkConnectString;

    IDeviceManageService dms;

    @Value("${iot.rocketmq.brokers}")
    private String brokers;

    private IFactory factory;

    @PostConstruct
    public void init() {
        dms = Dms.getService(zkConnectString);
        factory = RocketMQUtil.createOwnFactory(brokers);
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public IDeviceManageService getDms() {
        return dms;
    }

    public IFactory getFactory() {
        return factory;
    }
}
