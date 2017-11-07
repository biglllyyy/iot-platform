package me.cloud.iot.store.dustbin.data.service;

import me.iot.store.dustbin.data.dto.DustbinParamDto;

/**
 * Created by vortex on 2016/9/22.
 */
public interface IDustbinParamService {

    void add(DustbinParamDto dustbinParamDto);

    /**
     * get the record by the deviceId of the dustbin
     * @param deviceId    data format: deviceType + deviceNumber. For example, TRCAN867587029315130
     * @return
     */
    DustbinParamDto getByDeviceId(String deviceId);

    void update(DustbinParamDto dustbinParamDto);

    void addOrUpdate(DustbinParamDto dustbinParamDto);
}
