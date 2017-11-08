package com.vortex.dustbin.das;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import me.iot.store.dustbin.common.MsgCodes;
import me.iot.store.dustbin.common.MsgParams;
import io.netty.channel.ChannelHandlerContext;
import me.iot.common.util.ByteUtils;
import me.iot.das.common.DasConfig;
import me.iot.common.msg.*;
import me.iot.common.pojo.DeviceGuid;
import me.iot.das.simple.ISimpleMsgResolver;
import me.iot.das.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;

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
public class MsgResolver extends AbstractFrameCodec implements ISimpleMsgResolver {

    @Autowired
    DasConfig dasConfig;

    @Override
    public List<IMsg> bufToMsg(ChannelHandlerContext ctx, ByteBuffer buf) {

        IMsg msg = decode(buf);
        if (msg == null || msg.getSourceDeviceId() == null) {
            return null;
        }

        DeviceConnectionMsg dcMsg = new DeviceConnectionMsg();
        dcMsg.setSourceDevice(msg.getSourceDeviceType(), msg.getSourceDeviceId());
        // 云平台的deviceType,deviceId 是约定的，使用内置实现
        dcMsg.setTargetDevice(DeviceGuid.getCloudType(), DeviceGuid.getCloudNum());
        dcMsg.setTag(msg.getMsgCode());
        dcMsg.setConnected(true);
        dcMsg.setDasNodeId(dasConfig.getDasNodeId());

        List<IMsg> msgList = Lists.newArrayList();
        msgList.add(dcMsg);
        msgList.add(msg);

        return msgList;
    }

    @Override
    public ByteBuffer msgToBuf(IMsg msg) {
        return encode(msg);
    }

    @Override
    protected void onEncodeMsg(ByteBuffer buf, IMsg msg) {
        byte rc = msg.get(MsgParams.RC) == null ? 0 : Byte.valueOf(msg.get(MsgParams.RC).toString());
        buf.put(rc);

        int cmdId = Integer.parseInt(msg.getMsgCode());
        switch (cmdId) {
            case MsgCodes.SYNC_TIME_RES:
                long mills = msg.get(MsgParams.RtcTime) == null ? 0 : Long.parseLong(msg.get(MsgParams.RtcTime).toString());
                buf.put(DateUtil.millisecond2Bytes(mills));
                break;
            case MsgCodes.REPORT_STATUS_RES:
                break;
            case MsgCodes.REPORT_PARAMS_RES:

                int limit1 = msg.get(MsgParams.Limit1) == null ? 0 : Integer.parseInt(msg.get(MsgParams.Limit1).toString());
                int limit2 = msg.get(MsgParams.Limit2) == null ? 0 : Integer.parseInt(msg.get(MsgParams.Limit2).toString());
                int reportIndterval = msg.get(MsgParams.ReportInterval) == null ? 0 : Integer.parseInt(msg.get(MsgParams.ReportInterval).toString());
                String connectStr = msg.get(MsgParams.ConnectString) == null ? null : msg.get(MsgParams.ConnectString).toString();
                int connectStrLen = Strings.isNullOrEmpty(connectStr) ? 0 : connectStr.length();

                buf.put((byte) limit1);
                buf.put((byte) limit2);
                buf.put((byte) reportIndterval);
                buf.put((byte) connectStrLen);
                if (connectStrLen > 0) {
                    buf.put(connectStr.getBytes());
                }

                break;
            case MsgCodes.REPORT_ALARM_RES:
                break;
            default:
                break;
        }

    }

    @Override
    protected IMsg onDecodeMsg(MsgWrap wrap) {
        int cmdId = wrap.cmdId;
        String msgCode = String.valueOf(cmdId);
        AbstractDeviceMsg msg = new DeviceMsg();

        byte[] tmp;
        ByteBuffer buf = wrap.content;

        switch (cmdId) {
            case MsgCodes.SYNC_TIME:
                break;
            case MsgCodes.REPORT_STATUS:
                DeviceDataMsg dataMsg = new DeviceDataMsg();
                dataMsg.setTimestamp(wrap.timestamp);

                dataMsg.put(MsgParams.Remain1, ByteUtils.toInt(buf.get()));
                dataMsg.put(MsgParams.Remain2, ByteUtils.toInt(buf.get()));
                dataMsg.put(MsgParams.Remain3, ByteUtils.toInt(buf.get()));
                dataMsg.put(MsgParams.Tilt, ByteUtils.toInt(buf.get()));
                dataMsg.put(MsgParams.Battery, buf.getShort());
                dataMsg.put(MsgParams.Temperatue, buf.getShort() / 10F);

                msg = dataMsg;
                break;
            case MsgCodes.REPORT_PARAMS:
                msg.put(MsgParams.Limit1, ByteUtils.toInt(buf.get()));
                msg.put(MsgParams.Limit2, ByteUtils.toInt(buf.get()));
                msg.put(MsgParams.ReportInterval, ByteUtils.toInt(buf.get()));
                int connectStringLen = ByteUtils.toInt(buf.get());
                tmp = new byte[connectStringLen];
                buf.get(tmp);
                msg.put(MsgParams.ConnectString, new String(tmp));
                break;
            case MsgCodes.REPORT_ALARM:
                msg.put(MsgParams.AlarmMove, buf.get() != 0x00);
                msg.put(MsgParams.AlarmError1, buf.get() != 0x00);
                msg.put(MsgParams.AlarmError2, buf.get() != 0x00);
                msg.put(MsgParams.AlarmError3, buf.get() != 0x00);
                break;
            default:
                break;
        }

        msg.setMsgCode(msgCode);
        msg.setOccurTime(wrap.timestamp);
        msg.setSourceDeviceType(wrap.deviceType);
        msg.setSourceDeviceId(wrap.deviceId);
        msg.setTargetDeviceType(DeviceGuid.getCloudType());
        msg.setTargetDeviceId(DeviceGuid.getCloudNum());

        return msg;
    }
}
