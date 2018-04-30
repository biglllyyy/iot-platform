package iot.dcp.common;

/**
 * @author :  sylar
 * @FileName :  NettyConst
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
public interface NettyConst {
    String IDLE_STATE_HANDLER_NAME = "idleStateHandler";
    String LOG_HANDLER_NAME = "logHandler";
    String INBOUND_MSG_HANDLER_NAME = "inboundMsgHandler";

    int MIN_IDLETIME = 30;
    int MAX_IDLETIME = 3600;
}
