package iot.dmp.dms;

import iot.common.dto.QueryResult;
import iot.dmp.dms.dto.DcsConnectionLogDto;

/**
 * @author :  sylar
 * @FileName :  IDcsConnectionLogService
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
public interface IDcsConnectionLogService {
    /**
     * 根据das节点id获取das的连接日志
     *
     * @param nodeId
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<DcsConnectionLogDto> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int
            pageIndex, int pageSize);
}
