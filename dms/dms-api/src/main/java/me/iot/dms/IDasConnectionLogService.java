package me.iot.dms;

import me.iot.common.dto.QueryResult;
import me.iot.dms.entity.DasConnectionLog;

/**
 * @author :  sylar
 * @FileName :  IDasConnectionLogService
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
public interface IDasConnectionLogService {
    /**
     * @param nodeId
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DasConnectionLog> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int
            pageIndex, int pageSize);
}
