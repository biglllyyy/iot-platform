package me.iot.dms;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceManageService extends
        IMsgLogService,
        IDasStatusService,
        IDasConnectionLogService,
        IDeviceStatusService,
        IDeviceConnectionLogService,
        IDeviceTokenService,
        IDeviceMessageService,
        IDeviceInfoService,
        IDeviceAlarmService,
        IDeviceEventService,
        IDeviceLogService,
        IDeviceOtaService,
        IDeviceOwnerService,
        IDeviceLocationService {
}
