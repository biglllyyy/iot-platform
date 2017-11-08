package com.vortex.dustbin.das;

import com.google.common.base.Strings;

import me.iot.store.dustbin.common.MsgCodes;
import me.iot.store.dustbin.common.MsgParams;
import me.iot.das.common.bean.MsgSender;
import me.iot.das.common.core.InboundMsgProcessor;
import me.iot.common.msg.DeviceMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.DeviceGuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

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
@Component
public class MsgProcessor implements InboundMsgProcessor {

    private static final int RC_SUCCESS = 0;
    private static final int RC_FAILED = 1;


    @Autowired
    MsgSender msgSender;

    @Override
    public boolean processInboundMsg(IMsg msg) {
        String deviceType = msg.getSourceDeviceType();
        String deviceId = msg.getSourceDeviceId();

        DeviceMsg res = new DeviceMsg();
        //回应指令须使用请求指令的时间戳
        res.setOccurTime(msg.getOccurTime());
        res.setTargetDeviceType(deviceType);
        res.setTargetDeviceId(deviceId);
        res.setSourceDeviceType(DeviceGuid.getCloudType());
        res.setSourceDeviceId(DeviceGuid.getCloudNum());
        res.put(MsgParams.RC, RC_SUCCESS);

        boolean needThrowUp = true;
        int cmdId = Integer.valueOf(msg.getMsgCode());
        switch (cmdId) {
            case MsgCodes.SYNC_TIME:
                res.setMsgCode(String.valueOf(MsgCodes.SYNC_TIME_RES));
                res.put(MsgParams.RtcTime, Calendar.getInstance().getTimeInMillis());
                break;
            case MsgCodes.REPORT_STATUS:
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_STATUS_RES));
                break;
            case MsgCodes.REPORT_PARAMS:
                //参数上报的回应由上层业务处理,不用自动回应
                break;
            case MsgCodes.REPORT_ALARM:
                res.setMsgCode(String.valueOf(MsgCodes.REPORT_ALARM_RES));
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
