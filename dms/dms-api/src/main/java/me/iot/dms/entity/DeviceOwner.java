package me.iot.dms.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @FileName             :  DeviceOwner
 * @Author                :  luhao
 * @CreateDate           :  2016/6/28
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
@Entity
@Table(name = "device_owner")
public class DeviceOwner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 拥有者id
     */
    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    /**
     * 设备Id
     */
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    /**
     * 绑定时间
     */
    @Column(name = "bind_datetime", nullable = false)
    private long bindDatetime;

    /**
     * 是否已经绑定
     */
    @Column(name = "is_bound")
    private Boolean isBound;

    /**
     * 解除绑定时间
     */
    @Column(name = "unbind_datetime")
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
