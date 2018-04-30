package iot.dmp.dms.controller;

import iot.common.dto.Result;
import iot.dmp.dms.service.DcsStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  sylar
 * @FileName :  DasStatusController
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
public class DcsStatusController {

    @Autowired
    DcsStatusServiceImpl dasStatusServiceImpl;

    @RequestMapping(value = "/getDasStatus", method = RequestMethod.GET)
    public Result<?> getDasStatus(String nodeId) {
        return Result.newSuccess(dasStatusServiceImpl.getDasStatus(nodeId));
    }
}
