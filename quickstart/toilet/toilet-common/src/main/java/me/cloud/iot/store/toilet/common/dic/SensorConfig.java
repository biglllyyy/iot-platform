package me.cloud.iot.store.toilet.common.dic;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by sylar on 16/6/5.
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
        try {
            File file = resource.getFile();
            byte[] buffer = new byte[(int) file.length()];
            FileInputStream is = new FileInputStream(file);
            is.read(buffer, 0, buffer.length);
            is.close();
            String json = new String(buffer, Charsets.UTF_8);
            SensorInfos sensorInfos = JSON.parseObject(json, SensorInfos.class);
            if (sensorInfos != null) {
                SensorInfoManager.getInstance().loadSensonInfos(sensorInfos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
