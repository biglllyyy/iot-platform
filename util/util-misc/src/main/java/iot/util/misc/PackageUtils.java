package iot.util.misc;

import com.google.common.base.Splitter;

/**
 * File Name             :  PackageUtils
 * Author                :  sylar
 * Create Date           :  2018/4/15
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
public class PackageUtils {

    public static String getFirstFieldInPackageName() {
        String packageName = getPackageName(PackageUtils.class);
        return Splitter.on(".").splitToList(packageName).get(0);
    }

    public static String getPackageName(Class clazz) {
        return clazz.getPackage().getName();
    }
}
