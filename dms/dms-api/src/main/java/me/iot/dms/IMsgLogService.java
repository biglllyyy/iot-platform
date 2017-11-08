package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.MsgLog;

/**
 * @author :  sylar
 * @FileName :  IMsgLogService
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
public interface IMsgLogService {
    /**
     * @param deviceType
     * @param deviceId
     * @param msgType
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<MsgLog> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long endTime,
                                   int pageIndex, int pageSize);
}
