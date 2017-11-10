package me.iot.dms.service;

import com.google.common.collect.Lists;
import me.iot.common.AbstractDeviceMessagePipe;
import me.iot.common.Callback;
import me.iot.common.msg.IMsg;
import me.iot.common.usual.TopicConsts;
import me.iot.dms.DmsConfig;
import me.iot.util.mq.Message;
import me.iot.util.mq.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  CacheMsgHandler
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
@Component
public class CacheMsgHandler extends AbstractDeviceMessagePipe {

    @Autowired
    DeviceManageServiceImpl deviceManageService;
    @Autowired
    private DmsConfig dmsConfig;

    @Override
    public void input(Callback<IMsg> callback) {
        try {
            String topic = TopicConsts.DAS_TO_DMS;
            dmsConfig.getConsumer().subscribe(Lists.newArrayList(topic), new MessageListener() {
                @Override
                public void onSuccess(Message message) {
                    IMsg msg = convert(message.getContent());
                    callback.onSuccess(msg);
                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onFailure(t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void output(IMsg msg) {
        deviceManageService.processMsg(msg);
    }
}
