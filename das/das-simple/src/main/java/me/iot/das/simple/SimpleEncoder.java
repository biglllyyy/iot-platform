package me.iot.das.simple;

import me.iot.common.msg.IMsg;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
@ChannelHandler.Sharable
public class SimpleEncoder extends MessageToMessageEncoder<IMsg> {

    @Autowired
    protected ISimpleMsgResolver simpleMsgResolver;

    @Override
    protected void encode(ChannelHandlerContext ctx, IMsg msg, List<Object> out) throws Exception {

        if (msg == null || simpleMsgResolver == null) {
            return;
        }

        try {
            ByteBuffer buf = simpleMsgResolver.msgToBuf(msg);
            buf.flip();
            out.add(Unpooled.copiedBuffer(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
