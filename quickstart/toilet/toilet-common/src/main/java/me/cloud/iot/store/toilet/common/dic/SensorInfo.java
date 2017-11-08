package me.cloud.iot.store.toilet.common.dic;


import com.alibaba.fastjson.annotation.JSONField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
public class SensorInfo {
    private static final Logger LOG = LoggerFactory.getLogger(SensorInfo.class);

    private String id;
    private List<FactorInfo> factors;

    public boolean checkValid() {
        try {
            if (factors != null) {
                for (FactorInfo factor : factors) {
                    factor.checkValid();
                }
            }
            return true;
        } catch (Exception e) {
            LOG.error("SensorInfo checkInvalid error: {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @JSONField(serialize = false, deserialize = false)
    public int getFactorCount() {
        if (factors != null) {
            return factors.size();
        } else {
            return 0;
        }
    }

    @JSONField(serialize = false, deserialize = false)
    public int getByteLength() {
        int len = 0;
        if (factors != null) {
            for (FactorInfo factor : factors) {
                len += factor.getLen();
            }
        }
        return len;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FactorInfo> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorInfo> factors) {
        this.factors = factors;
    }
}
