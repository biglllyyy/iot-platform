package iot.quickstart.dustbin.common;

/**
 * @author :  sylar
 * @FileName :
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
public interface MsgCodes {

    /**
     * 时间同步
     */
    int SYNC_TIME = 5;
    int SYNC_TIME_RES = 6;

    /**
     * 状态值上报
     */
    int REPORT_STATUS = 128;
    int REPORT_STATUS_RES = 129;

    /**
     * 参数值上报
     */
    int REPORT_PARAMS = 130;
    int REPORT_PARAMS_RES = 131;


    /**
     * 报警上报
     */
    int REPORT_ALARM = 132;
    int REPORT_ALARM_RES = 133;

}
