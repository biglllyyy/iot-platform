package me.iot.dms;

import me.iot.util.rocketmq.IFactory;
import me.iot.util.rocketmq.RocketMQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author :  sylar
 * @FileName :  DmsConfig
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
public class DmsConfig {

    @Value("${zookeeper.connectString}")
    String zkConnectString;

    @Value("${iot.rocketmq.brokers}")
    private String brokers;

    private IFactory factory;

    @PostConstruct
    public void init() {
        factory = RocketMQUtil.createOwnFactory(brokers);
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public IFactory getFactory() {
        return factory;
    }
}
