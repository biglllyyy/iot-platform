package me.cloud.iot.store.dustbin.common;

public interface MsgParams {

    /**
     * 回应码 1B 0-成功，1-失败
     */
    String RC = "RC";

    /**
     * 时间戳
     */
    String Timestamp = "Timestamp";


    /**
     * RTC实时时间
     */
    String RtcTime = "RtcTime";

    /**
     * 垃圾桶空余空间  探头1
     */
    String Remain1 = "Remain1";

    /**
     * 垃圾桶空余空间  探头3
     */
    String Remain2 = "Remain2";

    /**
     * 垃圾桶空余空间  探头3
     */
    String Remain3 = "Remain3";

    /**
     * 垃圾桶倾斜角度[1Byte]，单位度，范围0~180
     */
    String Tilt = "Tilt";

    /**
     * 垃圾桶电池电压[2Byte],单位mV
     */
    String Battery = "Battery";


    /**
     * 垃圾桶温度[2Byte]，单位0.1摄氏度
     */
    String Temperatue = "Temperatue";

    /**
     * 垃圾桶满阈值[1Byte]，单位cm
     */
    String Limit1 = "Limit1";

    /**
     * 垃圾桶半满阈值[1Byte]，单位cm
     */
    String Limit2 = "Limit2";

    /**
     * 垃圾桶上报间隔[1Byte]，单位小时
     */
    String ReportInterval = "ReportInterval";

    /**
     * 垃圾桶云端地址长度[1Byte]
     */
    String ConnectStringLen = "ConnectStringLen";

    /**
     * 垃圾桶云端地址[NByte],内容包括地址和端口，格式为 ip:port
     */
    String ConnectString = "ConnectString";

    /**
     * 移动报警[1Byte]，0:无报警，其他:有报警
     */
    String AlarmMove = "AlarmMove";

    /**
     * 传感器故障报警 探头1
     */
    String AlarmError1 = "AlarmError1";

    /**
     * 传感器故障报警 探头2
     */
    String AlarmError2 = "AlarmError2";

    /**
     * 传感器故障报警 探头3
     */
    String AlarmError3 = "AlarmError3";

}
