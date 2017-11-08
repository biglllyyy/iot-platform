package me.iot.dms.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@MappedSuperclass
public abstract class AbstractDeviceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String deviceType;

    @Column(nullable = false)
    private String deviceId;

    @Column
    private long createTime = System.currentTimeMillis();

    /**
     * 自定义参数
     */
    @Column(name = "params", columnDefinition = "text")
    @JSONField(serialize = false, deserialize = false)
    private String params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @JSONField(serialize = false, deserialize = false)
    public String getParams() {
        return params;
    }

    @JSONField(serialize = false, deserialize = false)
    public void setParams(String params) {
        this.params = params;
    }

    @JSONField(name = "params")
    public Map<String, Object> getMapParams() {

        try {
            if (Strings.isNullOrEmpty(params)) {
                return JSON.parseObject(this.params);
            } else {
                return Maps.newHashMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Maps.newHashMap();
        }
    }

    @JSONField(name = "params")
    public void setMapParams(Map<String, ?> mapParams) {
        try {
            this.params = JSON.toJSONString(mapParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
