package me.iot.dms.service;

import me.iot.common.msg.IMsg;

/**
 * Created by sylar on 16/5/31.
 */
public interface IDmsMsgProcessor<T extends IMsg> {
    void processMsg(T msg);
}
