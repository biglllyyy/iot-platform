package me.iot.dms.service;

import me.iot.common.msg.DasConnectionMsg;
import me.iot.dms.DmsCacheKeys;
import me.iot.dms.DmsConfig;
import me.iot.dms.IDasStatusService;
import me.iot.dms.entity.DasStatus;
import me.iot.util.redis.ICentralCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author :  sylar
 * @FileName :  DasStatusServiceImpl
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
@Service
public class DasStatusServiceImpl implements IDmsMsgProcessor<DasConnectionMsg>, IDasStatusService {

    @Autowired
    DmsConfig dmsConfig;

    ICentralCacheService ccs;

    @PostConstruct
    private void init() {
        ccs = dmsConfig.getCcs();
    }

    @Override
    public void processMsg(DasConnectionMsg msg) {

        String nodeId = msg.getDasNodeId();
        String ccsKey = DmsCacheKeys.getCcsKeyForDasStatus(nodeId);

        if (msg.isConnected()) {
            DasStatus pojo = new DasStatus(nodeId);
            ccs.putObject(ccsKey, pojo);
        } else {
            ccs.removeObject(ccsKey);
        }
    }

    @Override
    public DasStatus getDasStatus(String nodeId) {
        String ccsKey = DmsCacheKeys.getCcsKeyForDasStatus(nodeId);
        if (!ccs.containsKey(ccsKey)) {
            return null;
        }

        return ccs.getObject(ccsKey, DasStatus.class);
    }

    public void updateDeviceConnection(String nodeId, String deviceId, boolean isConnected) {

        DasStatus pojo = getDasStatus(nodeId);
        if (pojo == null) {
            pojo = new DasStatus(nodeId);
        }

        if (isConnected) {
            pojo.addDeviceId(deviceId);
        } else {
            pojo.removeDeviceId(deviceId);
        }

        String ccsKey = DmsCacheKeys.getCcsKeyForDasStatus(nodeId);
        ccs.putObject(ccsKey, pojo);
    }
}
