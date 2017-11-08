package me.iot.das.simple;

import me.iot.common.msg.IMsg;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface ISimpleMsgResolver {

    List<IMsg> bufToMsg(ChannelHandlerContext ctx, ByteBuffer buf);

    ByteBuffer msgToBuf(IMsg msg);
}
