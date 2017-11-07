package me.cloud.iot.store.toilet.common.dic;

import com.alibaba.fastjson.annotation.JSONField;

import com.google.common.base.Enums;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * Created by sylar on 16/4/8.
 */
public class FactorInfo implements Serializable {

    /**
     * 因子模板编码
     */
    private String id;

    /**
     * 因子名称
     */
    private String name;

    /**
     * 因子值的字节长度
     */
    private int len;

    /**
     * 因子值类型
     */
    private String type;

    /**
     * 系数,结果须乘以系数
     */
    private Float k;

    /**
     * 因子描述
     */
    private String desc;

    private DataTypeEnum dataTypeEnum;

    void checkValid() {

        Preconditions.checkState(!Strings.isNullOrEmpty(type), "param type 为空");
        if (Objects.equal(type.toLowerCase(), "boolean")) {
            type = "bool";
        }
        if (Objects.equal(type.toLowerCase(), "integer")) {
            type = "int";
        }
        if (Objects.equal(type.toLowerCase(), "byte[]")) {
            type = "bytes";
        }
        if (Objects.equal(type.toLowerCase(), "strings")) {
            type = "string";
        }
        if (Objects.equal(type.toLowerCase(), "array")) {
            type = "list";
        }

        boolean isValid = Enums.getIfPresent(DataTypeEnum.class, type.toUpperCase()).isPresent();
        Preconditions.checkState(isValid, "param type 无效");
        dataTypeEnum = DataTypeEnum.valueOf(type.toUpperCase());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getK() {
        return k;
    }

    public void setK(Float k) {
        this.k = k;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JSONField(serialize = false, deserialize = false)
    public DataTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }

}
