package me.iot.das.common.core;

import me.iot.das.common.DasConfig;
import me.iot.das.common.DasProperties;
import me.iot.das.common.event.ServerConnectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by sylar on 16/7/26.
 */
public abstract class AbstractServer implements IServer {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ApplicationContext ctx;

    @Autowired
    protected DasConfig dasConfig;

    @Autowired
    protected InboundMsgHandler inboundMsgHandler;

    protected boolean isRunning;

    protected DasProperties dasProperties;

    @PostConstruct
    private void init() {
        dasProperties = dasConfig.getDasProperties();
        onInitServer();
    }

    @PreDestroy
    private void dispose() {
        onDisposeServer();
    }


    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    protected void onInitServer() {
        ctx.publishEvent(new ServerConnectEvent(this, dasConfig.getDasNodeId(), dasConfig.getDasProperties().getHost(), dasConfig.getDasProperties().getPort(), true));
    }

    protected void onDisposeServer() {
        ctx.publishEvent(new ServerConnectEvent(this, dasConfig.getDasNodeId(), dasConfig.getDasProperties().getHost(), dasConfig.getDasProperties().getPort(), false));
    }

}
