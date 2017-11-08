package me.cloud.iot.store.dustbin.data.service;

import me.cloud.iot.store.dustbin.data.dto.DustbinParamDto;

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
public interface IDustbinParamService {

    /**
     * 增加
     *
     * @param dustbinParamDto
     */
    void add(DustbinParamDto dustbinParamDto);

    /**
     * get the record by the deviceId of the dustbin
     *
     * @param deviceId data format: deviceType + deviceNumber. For example, TRCAN867587029315130
     * @return
     */
    DustbinParamDto getByDeviceId(String deviceId);

    /**
     * 更新
     *
     * @param dustbinParamDto
     */
    void update(DustbinParamDto dustbinParamDto);

    /**
     * 增加or更新
     *
     * @param dustbinParamDto
     */
    void addOrUpdate(DustbinParamDto dustbinParamDto);
}
