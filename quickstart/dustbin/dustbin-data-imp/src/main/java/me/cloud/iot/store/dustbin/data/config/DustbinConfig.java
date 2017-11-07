package me.cloud.iot.store.dustbin.data.config;

import me.iot.dms.DMS;
import me.iot.dms.IDeviceManageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by LiShijun on 16/9/22.
 */
@Configuration
public class DustbinConfig {

    @Value("${zookeeper.connectString}")
    String zkConnectString;

    IDeviceManageService dms;

    @PostConstruct
    public void init() {
        dms = DMS.getService(zkConnectString);
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public IDeviceManageService getDms() {
        return dms;
    }

}
