package me.iot.dms;

import me.iot.dms.entity.DasConnectionLog;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDasConnectionLogService {
    QueryResult<DasConnectionLog> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int pageSize);
}
