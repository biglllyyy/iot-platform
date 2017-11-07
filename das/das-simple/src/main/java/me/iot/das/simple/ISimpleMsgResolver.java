package me.iot.das.simple;

import me.iot.common.msg.IMsg;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by sylar on 16/5/29.
 */
public interface ISimpleMsgResolver {

    List<IMsg> bufToMsg(ChannelHandlerContext ctx, ByteBuffer buf);

    ByteBuffer msgToBuf(IMsg msg);
}
