package me.iot.dms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

@Entity
@Table
public class DeviceInfo extends AbstractDeviceEntity {

    @Column(nullable = false)
    private int versionCode;

    @Column
    private String mac;

    @Column
    private String bid;

    @Column
    private boolean enable = true;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
