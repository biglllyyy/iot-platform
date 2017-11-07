package me.iot.dms.controller;

import me.iot.dms.service.DasStatusService;
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
public class DasStatusController {

    @Autowired
    DasStatusService dasStatusService;

    @RequestMapping(value = "/getDasStatus", method = RequestMethod.GET)
    public Result<?> getDasStatus(String nodeId) {
        return Result.newSuccess(dasStatusService.getDasStatus(nodeId));
    }
}
