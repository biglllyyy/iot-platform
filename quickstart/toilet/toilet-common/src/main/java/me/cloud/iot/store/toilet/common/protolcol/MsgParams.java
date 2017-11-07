package me.cloud.iot.store.toilet.common.protolcol;

public interface MsgParams {

    /**
     * 回应码 1B 0-成功，1-失败
     */
    String RC = "RC";

    /**
     * 总包号
     */
    String OtaPacketCount = "OtaPacketCount";

    /**
     * 当前包号
     */
    String OtaCurrentPacketIndex = "OtaCurrentPacketIndex";

    /**
     * 包内容
     */
    String OtaPacketData = "OtaPacketData";

    /**
     * 云端推送内容
     */
    String PushContent = "PushContent";

    /**
     * 上报间隔
     */
    String ReportInterval = "ReportInterval";

    /**
     * SSID String 根据WifiSsid_Len确定长度
     */
    String WifiSsid = "WifiSsid";

    /**
     * Wifi pasword String 根据WifiPwd_Len确定长度
     */
    String WifiPwd = "WifiPwd";

    /**
     * 设备个数 1B
     */
    String DeviceNum = "DeviceNum";

    /**
     * 实时时间
     */
    String Time = "Time";

    /**
     * 考勤卡ID
     */
    String Rfid = "Rfid";

    /**
     * 传感器列表
     */
    String Sensons = "Sensons";

    /**
     * 设备Guid
     */
    String Guid = "Guid";

    /**
     * 开关风机阈值
     */
    String ToiletParamFan = "ToiletParamFan";

    /**
     * 喷除臭剂阈值
     */
    String ToiletParamDeodorant = "ToiletParamDeodorant";

}
