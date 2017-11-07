package me.iot.common.usual;

import me.iot.common.msg.MsgType;
import me.iot.common.usual.CacheKeys;

/**
 * Created by sylar on 16/5/25.
 */
public class DasCacheKeys extends CacheKeys {
    protected final static String NODE = "node";
    protected final static String CLIENT = "client";
    protected final static String TOPIC = "topic";

    public static String getMqsKeyFromDmsToDas(String nodeId) {
        return getDasKey(DMS + SYMBOL_TO + DAS, NODE + SYMBOL_EQUAL + nodeId);
    }

    public static String getMqsKeyFromDasToDms() {
        return getDasKey(DAS + SYMBOL_TO + DMS);
    }

    public static String getMqsKeyByNodeIdAndMsgType(int nodeId, MsgType msgType) {
        return getDasKey(String.valueOf(nodeId), msgType.toString());
    }

    public static String getCcsKeyForByClientIdAnsMsgType(String ClientId, MsgType msgType) {
        return getDasKey(CLIENT, ClientId, msgType.toString());
    }
}
