package iot.dmp.dms.service;

import com.alibaba.fastjson.JSON;
import iot.common.msg.DcsConnectionMsg;
import iot.dmp.dms.DmsCacheKeys;
import iot.dmp.dms.DmsConfig;
import iot.dmp.dms.IDcsStatusService;
import iot.dmp.dms.dto.DcsStatusDto;
import iot.dmp.dms.po.DcsStatus;
import iot.util.redis.RedisOperations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author :  sylar
 * @FileName :  DcsStatusServiceImpl
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
public class DcsStatusServiceImpl implements IDmsMsgProcessor<DcsConnectionMsg>, IDcsStatusService {

    @Autowired
    DmsConfig dmsConfig;

    @Autowired
    RedisOperations redisOperations;

    @PostConstruct
    private void init() {
    }

    @Override
    public void processMsg(DcsConnectionMsg msg) {

        String nodeId = msg.getDcsNodeId();
        String key = DmsCacheKeys.getKey_dcsStatus(nodeId);

        if (msg.isConnected()) {
            DcsStatus po = new DcsStatus();
            po.setNodeId(nodeId);
            redisOperations.opsValue(key).set(JSON.toJSONString(po));
        } else {
            redisOperations.delete(key);
        }
    }

    @Override
    public DcsStatusDto getDasStatus(String nodeId) {
        String key = DmsCacheKeys.getKey_dcsStatus(nodeId);
        if (!redisOperations.hasKey(key)) {
            return null;
        }

        DcsStatus dcsStatus = JSON.parseObject(redisOperations.opsValue(key).get(), DcsStatus.class);
        DcsStatusDto dto = new DcsStatusDto();
        BeanUtils.copyProperties(dcsStatus, dto);
        return dto;
    }

    public void updateDeviceConnection(String nodeId, String deviceId, boolean isConnected) {

        DcsStatusDto dto = getDasStatus(nodeId);
        if (dto == null) {
            dto = new DcsStatusDto();
            dto.setNodeId(nodeId);
        }

        if (isConnected) {
            dto.addDeviceId(deviceId);
        } else {
            dto.removeDeviceId(deviceId);
        }

        String ccsKey = DmsCacheKeys.getKey_dcsStatus(nodeId);
        redisOperations.opsValue(ccsKey).set(JSON.toJSONString(dto));
    }
}
