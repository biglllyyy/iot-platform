package iot.dcp.common.bean;

import com.google.common.base.Strings;
import io.netty.channel.Channel;
import iot.common.msg.IMsg;
import iot.dcp.common.NettyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  MsgSender
 * @CreateDate :  2017/11/08
 * @Description :  并将消息发给 netty channel
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
public class MsgSender {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelCache channelCache;

    public void sendMsg(IMsg msg) {
        String targetDeviceType = msg.getTargetDeviceType();
        String targetDeviceId = msg.getTargetDeviceId();

        if (Strings.isNullOrEmpty(targetDeviceType)) {
            LOG.warn("sendMsg error: targetDeviceType is null or empty");
            return;
        }

        if (Strings.isNullOrEmpty(targetDeviceId)) {
            LOG.warn("sendMsg error: targetDeviceId is null or empty");
            return;
        }

        //channel查找规则: deviceId 或者 deviceType + deviceId
        Channel channel = channelCache.get(targetDeviceId);
        if (channel == null) {
            channel = channelCache.get(targetDeviceType + targetDeviceId);
        }

        if (channel == null) {
            LOG.warn("sendMsg error. can't find channel for deviceId: {}", targetDeviceId);
            return;
        }

        NettyUtil.writeData(channel, msg);
        LOG.info("send message : {} -> {} \n message: {}", msg.getSourceDeviceId(), targetDeviceId, msg);
    }

}
