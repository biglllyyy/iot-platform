package iot.common.consts;

/**
 * @author :  sylar
 * @FileName :  MqConsts
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
public interface MqConsts {


    /**
     * 上行设备消息主题，从DCS到DMS
     */
    String TOPIC_DCS_TO_DMS = "IOT-DcsToDms";

    /**
     * DCS之间的消息主题，从DCS到DCS
     */
    String TOPIC_DCS_TO_DCS = "IOT-DcsToDcs";

    /**
     * 下行设备消息主题，从DMS到DCS
     */
    String TOPIC_DMS_TO_DCS = "IOT-DmsToDcs";

    /**
     * 下行设备消息主题，从DMS到APS
     */
    String TOPIC_DMS_TO_APS = "IOT-DmsToAps";


    /**
     * 生产者组：dcs to dms
     */
    String PID_DCS_TO_DMS_GROUP = "pid-iot-dcs-to-dms-group";


    /**
     * 生产者组：dcs to dcs
     */
    String PID_DCS_TO_DCS_GROUP = "pid-iot-dcs-to-dcs-group";

    /**
     * 生产者组：dms to dcs
     */
    String PID_DMS_TO_DCS_GROUP = "pid-iot-dms-to-dcs-group";

    /**
     * 生产者组：dms to aps
     */
    String PID_DMS_TO_APS_GROUP = "pid-iot-dms-to-aps-group";


    /**
     * 消费者组：dcs to dms
     */
    String CID_DCS_TO_DMS_GROUP = "cid-iot-dcs-to-dms-group";

    /**
     * 消费者组：dcs to dcs
     */
    String CID_DCS_TO_DCS_GROUP = "cid-iot-dcs-to-dcs-group";

    /**
     * 消费者组：dms to dcs
     */
    String CID_DMS_TO_DCS_GROUP = "cid-iot-dms-to-dcs-group";

    /**
     * 消费者组：dms to aps
     * 这个组应由业务自行定义，且不建议与其它业务协议共用相同组名
     */
//    String CID_DMS_TO_APS_GROUP = "cid-iot-dms-to-aps-group";
}
