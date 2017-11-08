package me.iot.common.msg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import me.iot.common.pojo.DeviceGuid;

import java.util.Map;
import java.util.Objects;


/**
 * 抽象设备消息
 */
public abstract class AbstractDeviceMsg implements IMsg {

    protected String msgCode;
    protected String sourceDeviceType;
    protected String sourceDeviceId;
    protected String targetDeviceType;
    protected String targetDeviceId;
    protected Object tag;
    protected long occurTime = System.currentTimeMillis();
    protected Map<String, Object> params = Maps.newLinkedHashMap();

    @JSONField(serialize = false, deserialize = false)
    @Override
    public abstract MsgType getMsgType();

    public void setDevice(DeviceGuid srcGuid, DeviceGuid tgtGuid) {
        setSourceDevice(srcGuid);
        setTargetDevice(tgtGuid);
    }

    public void setSourceDevice(DeviceGuid deviceGuid) {
        if (deviceGuid != null) {
            setSourceDevice(deviceGuid.getDeviceTypeId(), deviceGuid.getDeviceNumber());
        }
    }

    public void setTargetDevice(DeviceGuid deviceGuid) {
        if (deviceGuid != null) {
            setTargetDevice(deviceGuid.getDeviceTypeId(), deviceGuid.getDeviceNumber());
        }
    }

    public void setSourceDevice(String sourceDeviceType, String sourceDeviceId) {
        setSourceDeviceType(sourceDeviceType);
        setSourceDeviceId(sourceDeviceId);
    }

    public void setTargetDevice(String targetDeviceType, String targetDeviceId) {
        setTargetDeviceType(targetDeviceType);
        setTargetDeviceId(targetDeviceId);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof AbstractDeviceMsg) {
            AbstractDeviceMsg deviceMsg = (AbstractDeviceMsg) obj;
            return Objects.equals(getMsgType(), deviceMsg.getMsgType())
                    && Objects.equals(msgCode, deviceMsg.msgCode)
                    && Objects.equals(sourceDeviceType, deviceMsg.sourceDeviceType)
                    && Objects.equals(sourceDeviceId, deviceMsg.sourceDeviceId)
                    && Objects.equals(targetDeviceType, deviceMsg.targetDeviceType)
                    && Objects.equals(targetDeviceId, deviceMsg.targetDeviceId)
                    && Objects.equals(tag, deviceMsg.tag)
                    && Objects.equals(occurTime, deviceMsg.occurTime)
                    && Objects.equals(params, deviceMsg.params)
                    ;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public String getSourceDeviceType() {
        return sourceDeviceType;
    }

    public void setSourceDeviceType(String sourceDeviceType) {
        this.sourceDeviceType = sourceDeviceType;
    }

    @Override
    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    @Override
    public String getTargetDeviceType() {
        return targetDeviceType;
    }

    public void setTargetDeviceType(String targetDeviceType) {
        this.targetDeviceType = targetDeviceType;
    }

    @Override
    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    public void setTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
    }

    @Override
    public long getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(long occurTime) {
        this.occurTime = occurTime;
    }

    @Override
    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public <T> T get(String paramKey) {
        if (!params.containsKey(paramKey)) {
            return null;
        }
        return (T) params.get(paramKey);
    }

    public void put(String paramKey, Object paramValue) {
        params.put(paramKey, paramValue);
    }

}
