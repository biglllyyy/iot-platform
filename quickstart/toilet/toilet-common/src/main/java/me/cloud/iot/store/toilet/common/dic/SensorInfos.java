package me.cloud.iot.store.toilet.common.dic;

import java.util.List;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class SensorInfos {

    private List<SensorInfo> sensorInfos;

    public List<SensorInfo> getSensorInfos() {
        return sensorInfos;
    }

    public void setSensorInfos(List<SensorInfo> sensorInfos) {
        this.sensorInfos = sensorInfos;
    }
}
