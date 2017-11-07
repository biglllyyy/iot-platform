package me.iot.das.common.bean;

import me.iot.das.common.event.ServerConnectEvent;
import me.iot.common.msg.DasConnectionMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * DAS 服务状态监控 :上下线时发送消息给DMS
 */
@Component
public class ServerConnnectMonitor implements ApplicationListener<ServerConnectEvent> {
    @Autowired
    MsgThrower msgThrower;

    @Override
    public void onApplicationEvent(ServerConnectEvent event) {
        DasConnectionMsg msg = new DasConnectionMsg();
        msg.setDasInfo(event.getDasNodeId(), event.getIp(), event.getPort(), event.isConnected());
        msgThrower.sendToQueue(msg);
    }
}
