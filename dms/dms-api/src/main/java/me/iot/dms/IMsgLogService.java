package me.iot.dms;

import me.iot.dms.entity.MsgLog;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IMsgLogService {
    QueryResult<MsgLog> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long endTime, int pageIndex, int pageSize);
}
