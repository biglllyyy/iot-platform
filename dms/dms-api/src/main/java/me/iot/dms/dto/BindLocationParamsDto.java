package me.iot.dms.dto;

/**
 * Created by sylar on 16/8/27.
 */
public class BindLocationParamsDto {
    String userId;
    String deviceType;
    String deviceId;
    int coorType;
    double lon;
    double lat;
    String locationDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public int getCoorType() {
        return coorType;
    }

    public void setCoorType(int coorType) {
        this.coorType = coorType;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }
}
