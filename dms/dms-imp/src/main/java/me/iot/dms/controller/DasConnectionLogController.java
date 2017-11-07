package me.iot.dms.controller;

import me.iot.dms.service.DasConnectionLogService;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylar on 16/6/6.
 */
@RestController
@RequestMapping("/dms")
public class DasConnectionLogController {

    @Autowired
    DasConnectionLogService dasConnectionLogService;

    @RequestMapping(value = "/getDasConnectionLogsByNodeId", method = RequestMethod.GET)
    public Result<?> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int pageSize) {
        return Result.newSuccess(dasConnectionLogService.getDasConnectionLogsByNodeId(nodeId, beginTime, endTime, pageIndex, pageSize));
    }
}
