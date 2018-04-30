package iot.dmp.dms.service;

import com.google.common.base.Strings;
import iot.common.msg.DcsConnectionMsg;
import iot.common.msg.IMsg;
import iot.dmp.dms.IDeviceMessageService;
import iot.dmp.dms.bean.DmsToApsProducer;
import iot.dmp.dms.bean.DmsToDcsProducer;
import iot.dmp.dms.po.DeviceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :  sylar
 * @FileName :  DeviceMessageServiceImpl
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
public class DeviceMessageServiceImpl implements IDmsMsgProcessor<IMsg>, IDeviceMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceMessageServiceImpl.class);

    @Autowired
    private DeviceStatusServiceImpl deviceStatusServiceImpl;

    @Autowired
    private DmsToDcsProducer dmsToDcsProducer;


    @Autowired
    private DmsToApsProducer dmsToApsProducer;

    @Override
    public void processMsg(IMsg msg) throws Exception {
        if (msg instanceof DcsConnectionMsg) {
            //dcs node connection msg
            return;
        }
        if (Strings.isNullOrEmpty(msg.getSourceDeviceId())
                || Strings.isNullOrEmpty(msg.getMsgCode())) {
            LOG.warn("sourceDeviceId is null or msgCode is null:{}", msg);
            return;
        }
        dmsToApsProducer.publish(msg, msg.getSourceDeviceType());
    }


    @Override
    public void sendMsg(IMsg msg) throws Exception {

        if (msg == null) {
            return;
        }

        DeviceStatus deviceStatus = deviceStatusServiceImpl.getDeviceStatus(msg.getTargetDeviceType() + msg
                .getTargetDeviceId());
        if (deviceStatus == null) {
            LOG.warn("can not send msg: deviceStatus not found, nodeId is unknown");
            return;
        }

        if (!deviceStatus.isConnected()) {
            LOG.warn("can not send msg: device is not connected.");
            return;
        }

        dmsToDcsProducer.publish(msg, deviceStatus.getNodeId());
        LOG.info("send msg to device {}", msg.getSourceDeviceId());
    }
}
