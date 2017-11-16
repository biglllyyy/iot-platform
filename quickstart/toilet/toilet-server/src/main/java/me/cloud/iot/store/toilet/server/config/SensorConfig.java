package me.cloud.iot.store.toilet.server.config;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import me.cloud.iot.store.toilet.common.dic.SensorInfoManager;
import me.cloud.iot.store.toilet.common.dic.SensorInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author :  sylar
 * @FileName :  SensorConfig
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
@Component
public class SensorConfig {

    @Autowired
    ApplicationContext ctx;

    @PostConstruct
    private void init() {
        Resource resource = ctx.getResource("classpath:toilet_sensor_config.json");
        if (!resource.exists()) {
            return;
        }

        FileInputStream is = null;
        try {
            File file = resource.getFile();
            byte[] buffer = new byte[(int) file.length()];
            is = new FileInputStream(file);
            is.read(buffer, 0, buffer.length);
            String json = new String(buffer, Charsets.UTF_8);
            SensorInfos sensorInfos = JSON.parseObject(json, SensorInfos.class);
            if (sensorInfos != null) {
                SensorInfoManager.getInstance().loadSensonInfos(sensorInfos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}