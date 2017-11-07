package me.iot.util.json;

public class JsonUtils {

    private JsonUtils() {
    }

    /**
     * 用于校验一个字符串是否是合法的JSON格式
     * @param input
     * @return
     */
    static public boolean isValid(String input) {
        return new JsonValidator().validate(input);
    }
}
