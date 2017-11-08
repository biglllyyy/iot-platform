package me.cloud.iot.store.toilet.server;


import me.iot.common.constant.DeviceTypes;
import me.iot.common.msg.DeviceMsg;
import me.iot.common.msg.IMsg;
import me.iot.das.common.ota.AbstractOtaWorker;
import me.iot.das.common.ota.OtaSendType;
import me.iot.store.toilet.common.protolcol.MsgCodes;
import me.iot.store.toilet.common.protolcol.MsgParams;
import org.springframework.stereotype.Component;

/**
 * @author :  sylar
 * @FileName :  MqttConst
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
public class OtaWorker extends AbstractOtaWorker {

    @Override
    public String getDeviceType() {
        return DeviceTypes.DEVICE_TYPE_VTXGC;
    }

    @Override
    public OtaSendType getSendType() {
        return OtaSendType.LastPacketResponse;
    }

    @Override
    public int getMaxFrameLength() {
        return 1024;
    }

    @Override
    protected IMsg buildEachPacketMsg(String deviceId, int packetCount, int packetIndex, byte[] eachPacketData) {
        DeviceMsg msg = DeviceMsg.newMsgFromCloud(String.valueOf(MsgCodes.OTA_SEND), getDeviceType(), deviceId);

        msg.put(MsgParams.OtaPacketCount, packetCount);
        msg.put(MsgParams.OtaCurrentPacketIndex, packetIndex);
        msg.put(MsgParams.OtaPacketData, eachPacketData);
        return msg;
    }
}
