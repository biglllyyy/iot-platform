package me.iot.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class DeviceLocation extends AbstractDeviceEntity {

    @Column
    private String userId;

    @Column
    private String locationDesc;

    /**
     * wgs84 国际坐标, 形如"x,y"
     */
    @Column
    private String wgsCoor;

    /**
     * gcj02 国测局加密坐标, 形如"x,y"
     */
    @Column
    private String gcjCoor;


    /**
     * bd09ll 百度加密坐标, 形如"x,y"
     */
    @Column
    private String bdCoor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getWgsCoor() {
        return wgsCoor;
    }

    public void setWgsCoor(String wgsCoor) {
        this.wgsCoor = wgsCoor;
    }

    public String getGcjCoor() {
        return gcjCoor;
    }

    public void setGcjCoor(String gcjCoor) {
        this.gcjCoor = gcjCoor;
    }

    public String getBdCoor() {
        return bdCoor;
    }

    public void setBdCoor(String bdCoor) {
        this.bdCoor = bdCoor;
    }
}
