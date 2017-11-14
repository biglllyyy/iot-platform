package me.iot.das.common.event;

import me.iot.common.msg.DeviceOtaMsg;

/**
 * @author :  sylar
 * @FileName :  OtaNewTaskEvent
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
public class OtaNewTaskEvent extends OtaEvent {
    private DeviceOtaMsg msg;

    public OtaNewTaskEvent(Object source, DeviceOtaMsg msg) {
        super(source);
        this.msg = msg;
    }

    public DeviceOtaMsg getMsg() {
        return msg;
    }

    public void setMsg(DeviceOtaMsg msg) {
        this.msg = msg;
    }
}
