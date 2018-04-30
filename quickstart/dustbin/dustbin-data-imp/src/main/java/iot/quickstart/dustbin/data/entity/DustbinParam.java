package iot.quickstart.dustbin.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author :  sylar
 * @FileName :  DustbinParam
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
public class DustbinParam extends BaseEntity {

    /**
     * 设备类型
     */
    @Column
    private String deviceType;

    /**
     * 设备编码
     */
    @Column(nullable = false)
    private String deviceId;

    /**
     * 高度
     */
    @Column
    private Integer height;

    /**
     * 全满阈值
     */
    @Column
    private Integer fullThreshold;

    /**
     * 半满阈值
     */
    @Column
    private Integer halfFullThreshold;

    @Column
    private String userId;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFullThreshold() {
        return fullThreshold;
    }

    public void setFullThreshold(Integer fullThreshold) {
        this.fullThreshold = fullThreshold;
    }

    public Integer getHalfFullThreshold() {
        return halfFullThreshold;
    }

    public void setHalfFullThreshold(Integer halfFullThreshold) {
        this.halfFullThreshold = halfFullThreshold;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
