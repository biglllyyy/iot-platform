package iot.quickstart.toilet.server;


import iot.common.msg.DeviceMsg;
import iot.common.msg.IMsg;
import iot.dcp.common.ota.AbstractOtaWorker;
import iot.dcp.common.ota.OtaSendType;
import iot.quickstart.toilet.common.DeviceTypes;
import iot.quickstart.toilet.common.protolcol.MsgCodes;
import iot.quickstart.toilet.common.protolcol.MsgParams;
import org.springframework.stereotype.Component;

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

        msg.put(MsgParams.OTA_PACKET_COUNT, packetCount);
        msg.put(MsgParams.OTA_CURRENT_PACKET_INDEX, packetIndex);
        msg.put(MsgParams.OTA_PACKET_DATA, eachPacketData);
        return msg;
    }
}
