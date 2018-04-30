package iot.quickstart.toilet.common.protolcol;

/**
 * @author :  sylar
 * @FileName :  MsgParams
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
public interface MsgParams {

    /**
     * 回应码 1B 0-成功，1-失败
     */
    String RC = "RC";

    /**
     * 总包号
     */
    String OTA_PACKET_COUNT = "OtaPacketCount";

    /**
     * 当前包号
     */
    String OTA_CURRENT_PACKET_INDEX = "OtaCurrentPacketIndex";

    /**
     * 包内容
     */
    String OTA_PACKET_DATA = "OtaPacketData";

    /**
     * 云端推送内容
     */
    String PUSH_CONTENT = "PushContent";

    /**
     * 上报间隔
     */
    String REPORT_INTERVAL = "ReportInterval";

    /**
     * SSID String 根据WifiSsid_Len确定长度
     */
    String WIFI_SSID = "WifiSsid";

    /**
     * Wifi pasword String 根据WifiPwd_Len确定长度
     */
    String WIFI_PWD = "WifiPwd";

    /**
     * 设备个数 1B
     */
    String DEVICE_NUM = "DeviceNum";

    /**
     * 实时时间
     */
    String TIME = "Time";

    /**
     * 考勤卡ID
     */
    String RFID = "Rfid";

    /**
     * 传感器列表
     */
    String SENSONS = "Sensons";

    /**
     * 设备Guid
     */
    String GUID = "Guid";

    /**
     * 开关风机阈值
     */
    String TOILET_PARAM_FAN = "ToiletParamFan";

    /**
     * 喷除臭剂阈值
     */
    String TOILET_PARAM_DEODORANT = "ToiletParamDeodorant";

}
