package iot.dmp.dms;

import iot.common.dto.QueryResult;
import iot.dmp.dms.dto.MsgLogDto;

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
     * get 消息日志
     *
     * @param deviceType
     * @param deviceId
     * @param msgType
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<MsgLogDto> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long endTime,
                                      int pageIndex, int pageSize);
}
