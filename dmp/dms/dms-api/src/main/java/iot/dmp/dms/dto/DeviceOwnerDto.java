package iot.dmp.dms.dto;

import java.io.Serializable;

/**
 * @author :  luhao
 * @FileName :  DeviceOwnerDto
 * @CreateDate :  2016/6/28
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
public class DeviceOwnerDto implements Serializable {

    private Long id;

    /**
     * 拥有者id
     */
    private String ownerId;

    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 绑定时间
     */
    private long bindDatetime;

    /**
     * 是否已经绑定
     */
    private Boolean isBound;

    /**
     * 解除绑定时间
     */
    private long unBindDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getBindDatetime() {
        return bindDatetime;
    }

    public void setBindDatetime(long bindDatetime) {
        this.bindDatetime = bindDatetime;
    }

    public Boolean getBound() {
        return isBound;
    }

    public void setBound(Boolean bound) {
        isBound = bound;
    }

    public long getUnBindDatetime() {
        return unBindDatetime;
    }

    public void setUnBindDatetime(long unBindDatetime) {
        this.unBindDatetime = unBindDatetime;
    }
}
