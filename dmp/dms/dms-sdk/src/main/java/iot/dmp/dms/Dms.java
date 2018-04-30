package iot.dmp.dms;

import iot.util.dubbo.DubboUtils;

/**
 * @author :  sylar
 * @FileName :
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class Dms {
    final static String APPNAME = "dms-sdk";

    public static IDeviceManageService getService(String zkConnectString) {
        return DubboUtils.getServiceReference(APPNAME, zkConnectString, IDeviceManageService.class);
    }
}
