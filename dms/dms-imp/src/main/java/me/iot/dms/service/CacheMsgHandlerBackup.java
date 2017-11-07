//package me.iot.dms.service;
//
//import com.google.common.collect.Lists;
//import me.iot.common.AbstractCacheMsgHandler;
//import me.iot.common.msg.IMsg;
//import me.iot.common.pojo.CacheMsgWrap;
//import me.iot.common.usual.DasCacheKeys;
//import me.iot.common.usual.TopicConsts;
//import me.iot.dms.DmsConfig;
//import me.iot.util.mq.Message;
//import me.iot.util.mq.MessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by sylar on 16/5/31.
// */
//@Component
//@Deprecated
//public class CacheMsgHandlerBackup extends AbstractCacheMsgHandler {
//
//    @Autowired
//    DeviceManageService deviceManageService;
//    @Autowired
//    private DmsConfig dmsConfig;
//
//    @Override
//    public IMsg getMsgFromCache() {
//
//        String queueName = DasCacheKeys.getMqsKeyFromDasToDms();
//        CacheMsgWrap wrap = dmsConfig.getMqs().receiveMessage(queueName, CacheMsgWrap.class);
//        if (wrap != null) {
//            return wrap.getMsg();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public void handleMsg(IMsg msg) {
//        if (msg == null) {
//            return;
//        }
//
//        deviceManageService.processMsg(msg);
//    }
//
//}
