package iot.dmp.dms.po;

import iot.util.data.jpa.po.AbstractJpaPO;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author :  sylar
 * @FileName :  AbstractDeviceDto
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
@MappedSuperclass
public abstract class AbstractDeviceJpaPO extends AbstractJpaPO {

    @Column(nullable = false)
    private String deviceType;

    @Column(nullable = false)
    private String deviceId;

    /**
     * 自定义参数
     */
    @Column(name = "params", columnDefinition = "text")
    private String params;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

//    @JSONField(name = "params")
//    public Map<String, Object> getMapParams() {
//
//        try {
//            if (!Strings.isNullOrEmpty(params)) {
//                return JSON.parseObject(this.params);
//            } else {
//                return Maps.newHashMap();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Maps.newHashMap();
//        }
//    }
//
//    @JSONField(name = "params")
//    public void setMapParams(Map<String, ?> mapParams) {
//        try {
//            this.params = JSON.toJSONString(mapParams);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
