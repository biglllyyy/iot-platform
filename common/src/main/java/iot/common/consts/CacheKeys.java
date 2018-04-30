package iot.common.consts;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

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
public class CacheKeys {
    private final static String SEPARATOR = ":";
    private final static String PREFIX = AppConsts.TOP_PACKAGE_NAME;

    protected static String generateKeyBySyscode(String sysCode, String... params) {
        return generateKey(PREFIX, sysCode, generateKey(params));
    }

    public static String generateKey(String... params) {
        Preconditions.checkNotNull(params);
        Preconditions.checkState(params.length > 0, "params is empty");
        return Joiner.on(SEPARATOR).skipNulls().join(params);
    }


}
