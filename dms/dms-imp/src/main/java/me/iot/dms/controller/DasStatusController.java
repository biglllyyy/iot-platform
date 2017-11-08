package me.iot.dms.controller;

import me.iot.dms.service.DasStatusServiceImpl;
import me.iot.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@RestController
@RequestMapping("/dms")
public class DasStatusController {

    @Autowired
    DasStatusServiceImpl dasStatusServiceImpl;

    @RequestMapping(value = "/getDasStatus", method = RequestMethod.GET)
    public Result<?> getDasStatus(String nodeId) {
        return Result.newSuccess(dasStatusServiceImpl.getDasStatus(nodeId));
    }
}
