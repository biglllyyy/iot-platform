package me.iot.dms;

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
