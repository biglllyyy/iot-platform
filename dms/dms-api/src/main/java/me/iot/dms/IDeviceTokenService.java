package me.iot.dms;

import me.iot.dms.entity.DeviceToken;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceTokenService {

    long countOfDeviceToken();

    String generateDeviceId(String deviceType, String token);

    DeviceToken getDeviceTokenByDeviceId(String deviceId);

    DeviceToken getDeviceTokenByToken(String token);

    QueryResult<DeviceToken> getDeviceTokensByDeviceType(String deviceType, int pageIndex, int pageSize);

}
