package iot.common.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * File Name             :  AbstractPojo
 * Author                :  sylar
 * Create Date           :  2018/4/19
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public abstract class AbstractPojo implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
