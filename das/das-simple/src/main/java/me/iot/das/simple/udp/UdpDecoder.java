package me.iot.das.simple.udp;

import com.google.common.base.Strings;
import me.iot.das.common.bean.TerminalCache;
import me.iot.common.msg.IMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.DatagramPacketDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
public class UdpDecoder extends DatagramPacketDecoder {

    @Autowired
    TerminalCache terminalCache;

    @Autowired
    public UdpDecoder(@Qualifier(value = "simpleDecoder") MessageToMessageDecoder<ByteBuf> decoder) {
        super(decoder);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        super.decode(ctx, datagramPacket, out);

        //记录每个udp client 的地址端口信息
        for (Object obj : out) {
            if (obj instanceof IMsg) {
                IMsg msg = (IMsg) obj;
                if (!Strings.isNullOrEmpty(msg.getSourceDeviceId())) {
                    terminalCache.put(msg.getSourceDeviceId(), datagramPacket.sender());
                }
            }
        }
    }
}
