package me.cloud.iot.store.dustbin.data.dto;

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
public class DustbinParamDto {
    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备编码
     */
    private String deviceId;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 全满阈值
     */
    private Integer fullThreshold ;

    /**
     * 半满阈值
     */
    private Integer halfFullThreshold;

    private String userId;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFullThreshold() {
        return fullThreshold;
    }

    public void setFullThreshold(Integer fullThreshold) {
        this.fullThreshold = fullThreshold;
    }

    public Integer getHalfFullThreshold() {
        return halfFullThreshold;
    }

    public void setHalfFullThreshold(Integer halfFullThreshold) {
        this.halfFullThreshold = halfFullThreshold;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
