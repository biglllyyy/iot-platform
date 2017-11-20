package me.cloud.iot.store.dustbin.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * File Name             :  DustbinDeviceData
 * Author                :  luhao
 * Create Date           :  2017/11/17
 * Description           :  设备采集的数据，原始数据
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
@Entity
@Table(name = "dustbin_device_data")
public class DustbinDeviceData extends BaseEntity {
    /**
     * 设备代码，对应每台设备的唯一编码
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;


    /**
     * 因子代码，对应DeviceFactorModel的factorCode
     */
    @Column(name = "factor_code")
    private String factorCode;

    /**
     * 采集的原始值
     */
    @Column(name = "original_value")
    private Double originalValue;

    /**
     * 修正值
     */
    @Column(name = "correct_value")
    private Double correctValue;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public Double getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(Double correctValue) {
        this.correctValue = correctValue;
    }
}
