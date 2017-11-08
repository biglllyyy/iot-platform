package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DeviceToken;

/**
 * @author :  sylar
 * @FileName :  IDeviceTokenService
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
public interface IDeviceTokenService {

    /**
     * @return
     */
    long countOfDeviceToken();

    /**
     * @param deviceType
     * @param token
     * @return
     */
    String generateDeviceId(String deviceType, String token);

    /**
     * @param deviceId
     * @return
     */
    DeviceToken getDeviceTokenByDeviceId(String deviceId);

    /**
     * @param token
     * @return
     */
    DeviceToken getDeviceTokenByToken(String token);

    /**
     * @param deviceType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DeviceToken> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize);

}
