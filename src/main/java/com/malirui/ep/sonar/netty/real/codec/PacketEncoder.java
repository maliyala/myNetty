package com.malirui.ep.sonar.netty.real.codec;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import com.malirui.ep.sonar.netty.real.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:下午1:42
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out,packet);
    }
}
