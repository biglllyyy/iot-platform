package iot.dcp.common.bean;

import iot.common.msg.DcsConnectionMsg;
import iot.dcp.common.event.ServerConnectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  ServerConnnectMonitor
 * @CreateDate :  2017/11/08
 * @Description : 服务状态监控 :上下线时发送消息给DMS
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
public class ServerConnnectEventListener implements ApplicationListener<ServerConnectEvent> {
    @Autowired
    DcsToDmsProducer dcsToDmsProducer;

    @Override
    public void onApplicationEvent(ServerConnectEvent event) {
        DcsConnectionMsg msg = new DcsConnectionMsg();
        msg.setDcsInfo(event.getDcsNodeId(), event.getIp(), event.getPort(), event.isConnected());
        dcsToDmsProducer.publish(msg);
    }
}
