package me.iot.dms;

import me.iot.util.dubbo.DubboUtils;

/**
 * Created by sylar on 16/6/3.
 */
public class DMS {
    final static String APPNAME = "dms-sdk";

    public static IDeviceManageService getService(String zkConnectString) {
        return DubboUtils.getServcieReference(APPNAME, zkConnectString, IDeviceManageService.class);
    }
}
