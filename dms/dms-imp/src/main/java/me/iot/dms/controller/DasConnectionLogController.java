package me.iot.dms.controller;

import me.iot.common.dto.Result;
import me.iot.dms.service.DasConnectionLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public class DasConnectionLogController {

    @Autowired
    DasConnectionLogServiceImpl dasConnectionLogServiceImpl;

    @RequestMapping(value = "/getDasConnectionLogsByNodeId", method = RequestMethod.GET)
    public Result<?> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int
            pageSize) {
        return Result.newSuccess(dasConnectionLogServiceImpl.getDasConnectionLogsByNodeId(nodeId, beginTime, endTime,
                pageIndex, pageSize));
    }
}
