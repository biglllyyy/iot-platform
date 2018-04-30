package iot.dcp.common.core;

import com.google.common.base.Preconditions;
import iot.dcp.common.DcsProperties;
import iot.dcp.common.event.ServerConnectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author :  sylar
 * @FileName :  AbstractServer
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
public abstract class AbstractServer implements IServer {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected boolean isRunning;

    @Autowired
    protected ApplicationContext ctx;

    @Autowired
    protected DcsProperties dcsProperties;

    @Autowired
    protected InboundMsgHandler inboundMsgHandler;

    @PostConstruct
    protected void onInit() {
        initAndCheckProperties();
        onInitServer();
    }

    @PreDestroy
    protected void onDestory() {
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

    protected void initAndCheckProperties() {
        Preconditions.checkNotNull(dcsProperties, "dcs's properties can not be null");
        Preconditions.checkNotNull(dcsProperties.getNodeId(), "dcs.nodeId can not be null");
        Preconditions.checkState(dcsProperties.getNodeId() != null, "dcs.nodeId can not be empty");
        Preconditions.checkState(dcsProperties.getPort() > 1024, "dcs.nodeId can not be empty");
    }

    protected void onInitServer() {
        ctx.publishEvent(new ServerConnectEvent(this,
                dcsProperties.getNodeId(),
                dcsProperties.getHost(),
                dcsProperties.getPort(),
                true));
    }

    protected void onDisposeServer() {
        ctx.publishEvent(new ServerConnectEvent(this,
                dcsProperties.getNodeId(),
                dcsProperties.getHost(),
                dcsProperties.getPort(),
                false));
    }
}
