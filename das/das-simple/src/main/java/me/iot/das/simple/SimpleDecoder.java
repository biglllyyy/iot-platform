package me.iot.das.simple;

import me.iot.common.msg.IMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component@ChannelHandler.Sharable
public class SimpleDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Autowired
    protected ISimpleMsgResolver simpleMsgResolver;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        if (simpleMsgResolver == null) {
            return;
        }

        try {
            List<IMsg> msgList = simpleMsgResolver.bufToMsg(ctx, buf.nioBuffer());
            if (msgList != null && !msgList.isEmpty()) {
                out.addAll(msgList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
