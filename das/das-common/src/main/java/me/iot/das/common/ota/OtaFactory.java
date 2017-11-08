package me.iot.das.common.ota;

import com.google.common.collect.Maps;
import me.iot.common.msg.DeviceOtaMsg;
import me.iot.das.common.event.OtaNewTaskEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public class OtaFactory implements ApplicationListener<OtaNewTaskEvent> {

    private Map<String, IOtaWorker> mapWorker = Maps.newHashMap();

    public void registWorker(String deviceType, IOtaWorker worker) {
        mapWorker.put(deviceType, worker);
    }

    @Override
    public void onApplicationEvent(OtaNewTaskEvent event) {
        DeviceOtaMsg msg = event.getMsg();
        String deviceType = msg.getTargetDeviceType();
        if (!mapWorker.containsKey(deviceType)) {
            return;
        }

        IOtaWorker worker = mapWorker.get(deviceType);
        String deivceId = msg.getTargetDeviceId();
        byte[] otaData = msg.getOtaData();
        worker.startOtaTask(deivceId, otaData);
    }

}
