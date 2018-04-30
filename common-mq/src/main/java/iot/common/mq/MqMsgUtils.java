package iot.common.mq;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import iot.common.msg.IMsg;
import iot.common.pojo.CacheMsgWrap;
import iot.util.mq.Message;

/**
 * File Name             :  MqMsgUtils
 * Author                :  sylar
 * Create Date           :  2018/4/22
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
public class MqMsgUtils {

    public static IMsg convert(Message message) {
        if (message == null || Strings.isNullOrEmpty(message.getContent())) {
            return null;
        }

        CacheMsgWrap wrap = JSON.parseObject(message.getContent(), CacheMsgWrap.class);
        if (wrap != null) {
            return wrap.getMsg();
        }

        return null;
    }
}
