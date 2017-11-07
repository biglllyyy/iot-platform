package me.iot.util.misc;

import com.google.common.base.Joiner;

import java.util.List;

/**
 * glob 模式（globbing）也被称之为 shell 通配符
 * <p>
 * http://www.linuxidc.com/Linux/2016-08/134192.htm
 * Created by sylar on 2017/2/10.
 */
public class GlobUtils {
    public static String genGlobStr(List<String> items) {
        if (items == null || items.size() == 0) {
            return "*";
        } else {
            return "{" + Joiner.on(",").skipNulls().join(items) + "}";
        }
    }
}
