package me.iot.dms;

import me.iot.dms.entity.DeviceToken;
import me.iot.common.dto.QueryResult;

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
public interface IDeviceTokenService {

    long countOfDeviceToken();

    String generateDeviceId(String deviceType, String token);

    DeviceToken getDeviceTokenByDeviceId(String deviceId);

    DeviceToken getDeviceTokenByToken(String token);

    QueryResult<DeviceToken> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize);

}
