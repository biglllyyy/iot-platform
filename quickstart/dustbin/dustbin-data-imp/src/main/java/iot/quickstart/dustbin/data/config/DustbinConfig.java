package iot.quickstart.dustbin.data.config;

import iot.common.consts.AppConsts;
import iot.dmp.dms.Dms;
import iot.dmp.dms.IDeviceManageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
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

    @Bean
    @ConditionalOnProperty(AppConsts.PROPERTY_ZOOKEEPER)
    public IDeviceManageService getDeviceManageService() {
        return Dms.getService(zkConnectString);
    }

}
