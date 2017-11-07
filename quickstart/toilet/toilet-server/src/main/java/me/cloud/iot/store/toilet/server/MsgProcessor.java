package me.cloud.iot.store.toilet.server;

import com.google.common.base.Strings;
import me.iot.store.toilet.common.protolcol.MsgCodes;
import me.iot.store.toilet.common.protolcol.MsgParams;
import me.iot.das.common.bean.MsgSender;
import me.iot.das.common.core.InboundMsgProcessor;
import me.iot.das.common.event.OtaCompletedEvent;
import me.iot.das.common.event.OtaEachPacketResponseEvent;
import me.iot.common.msg.DeviceDataMsg;
import me.iot.common.msg.DeviceMsg;
import me.iot.common.msg.IMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by sylar on 16/5/30.
 */
@Component
public class MsgProcessor implements InboundMsgProcessor {

    int RC_SUCCESS = 0;
    int RC_FAILED = 1;

    @Autowired
    ApplicationContext ctx;

    @Autowired
    MsgSender msgSender;

    @Override
    public boolean processInboundMsg(IMsg msg) {
        String deviceId = msg.getSourceDeviceId();
        DeviceMsg res = DeviceMsg.newMsgFromCloud(null, msg.getSourceDeviceType(), msg.getSourceDeviceId());

        boolean needThrowUp = true;
        int msgCode = Integer.valueOf(msg.getMsgCode());
        switch (msgCode) {
            case MsgCodes.SYNC_TIME:
                res.setMsgCode(String.valueOf(MsgCodes.SYNC_TIME_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.Time, Calendar.getInstance().getTimeInMillis());
                break;
            case MsgCodes.REPORT_SENSOR_DATA:
                DeviceDataMsg dataMsg = (DeviceDataMsg) msg;
                long reportDataTime = dataMsg.getTimestamp();
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_SENSOR_DATA_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.Time, reportDataTime);
                break;
            case MsgCodes.REPORT_RFID_DATA:
                long reportRfidTime = msg.get(MsgParams.Time);
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_RFID_DATA_RES));
                res.put(MsgParams.RC, RC_SUCCESS);
                res.put(MsgParams.Time, reportRfidTime);
                break;
            case MsgCodes.OTA_SEND_RES:
                int currentPacketIndex = msg.get(MsgParams.OtaCurrentPacketIndex);
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
