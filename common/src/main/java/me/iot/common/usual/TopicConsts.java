package me.iot.common.usual;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * @FileName :  TopicConsts
 * @Author :  sylar
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
public class TopicConsts {
    private static final String PREFIX = "iot";
    private static final String SEPARATOR = ".";
    private static final String DAS_TO_DMS = "DasToDms";
    private static final String DMS_TO_DAS = "DmsToDas";


    public static String getTopicFromDmsToDas(String dasNodeId) {
        return join(PREFIX, DMS_TO_DAS, dasNodeId);
    }

    public static String getTopicFromDasToDms() {
        return join(PREFIX, DAS_TO_DMS);
    }


    public static String join(String... params) {
        Preconditions.checkNotNull(params);
        Preconditions.checkState(params.length > 0, "params is empty");
        return Joiner.on(SEPARATOR).skipNulls().join(params);
    }
}
