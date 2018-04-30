package iot.dcp.common.bean;

import io.netty.channel.Channel;
import iot.util.misc.LocalCache;
import org.springframework.stereotype.Component;


/**
 * @author :  sylar
 * @FileName :  ChannelCache
 * @CreateDate :  2017/11/08
 * @Description :  netty channel 缓存，key : clientId   clientId = deviceType + deviceId
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
public class ChannelCache extends LocalCache<Channel> {
}
