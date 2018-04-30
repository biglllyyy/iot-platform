package iot.quickstart.toilet.server;

import com.google.common.base.Strings;
import iot.common.msg.DeviceDataMsg;
import iot.common.msg.DeviceMsg;
import iot.common.msg.IMsg;
import iot.dcp.common.bean.MsgSender;
import iot.dcp.common.core.IResponder;
import iot.dcp.common.event.OtaCompletedEvent;
import iot.dcp.common.event.OtaEachPacketResponseEvent;
import iot.quickstart.toilet.common.protolcol.MsgCodes;
import iot.quickstart.toilet.common.protolcol.MsgParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author :  sylar
 * @FileName :
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
@Component
public class MsgProcessor implements IResponder {

    private static final int RC_SUCCESS = 0;
    private static final int RC_FAILED = 1;

    @Autowired
    ApplicationContext ctx;

    @Autowired
    MsgSender msgSender;

    @Override
    public boolean onRespond(IMsg msg) {
        String deviceId = msg.getSourceDeviceId();
        DeviceMsg res = DeviceMsg.newMsgFromCloud(null, msg.getSourceDeviceType(), msg.getSourceDeviceId());

        boolean needThrowUp = true;
        int msgCode = Integer.valueOf(msg.getMsgCode());
        switch (msgCode) {
            case MsgCodes.SYNC_TIME:
                res.setMsgCode(String.valueOf(MsgCodes.SYNC_TIME_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.TIME, Calendar.getInstance().getTimeInMillis());
                break;
            case MsgCodes.REPORT_SENSOR_DATA:
                DeviceDataMsg dataMsg = (DeviceDataMsg) msg;
                long reportDataTime = dataMsg.getTimestamp();
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_SENSOR_DATA_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.TIME, reportDataTime);
                break;
            case MsgCodes.REPORT_RFID_DATA:
                long reportRfidTime = msg.get(MsgParams.TIME);
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_RFID_DATA_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.TIME, reportRfidTime);
                break;
            case MsgCodes.OTA_SEND_RES:
                int currentPacketIndex = msg.get(MsgParams.OTA_CURRENT_PACKET_INDEX);
                ctx.publishEvent(new OtaEachPacketResponseEvent(this, deviceId, currentPacketIndex));
                break;
            case MsgCodes.OTA_SUCCESS_NOTIFY:
                ctx.publishEvent(new OtaCompletedEvent(this, deviceId));
                break;
            default:
                break;
        }

        if (!Strings.isNullOrEmpty(res.getMsgCode())) {
            msgSender.sendMsg(res);
        }

        return needThrowUp;
    }
}
