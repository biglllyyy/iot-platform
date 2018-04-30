package iot.dmp.dms.controller;

import iot.common.dto.Result;
import iot.dmp.dms.service.DcsConnectionLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  sylar
 * @FileName :  DasConnectionLogController
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
@RestController
@RequestMapping("/dms")
public class DcsConnectionLogController {

    @Autowired
    DcsConnectionLogServiceImpl dasConnectionLogServiceImpl;

    @RequestMapping(value = "/getDasConnectionLogsByNodeId", method = RequestMethod.GET)
    public Result<?> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int
            pageSize) {
        return Result.newSuccess(dasConnectionLogServiceImpl.getDasConnectionLogsByNodeId(nodeId, beginTime, endTime,
                pageIndex, pageSize));
    }
}
