package iot.util.misc;

/**
 * File Name             :  JsonUtils
 * Author                :  sylar
 * Create Date           :  2018/4/10
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
public class JsonUtils {
    private JsonUtils() {
    }

    /**
     * 用于校验一个字符串是否是合法的JSON格式
     *
     * @param input
     * @return
     */
    static public boolean isValid(String input) {
        return new JsonValidator().validate(input);
    }
}
