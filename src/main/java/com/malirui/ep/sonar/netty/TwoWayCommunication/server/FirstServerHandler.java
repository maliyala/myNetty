package com.malirui.ep.sonar.netty.TwoWayCommunication.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author maliruimeituan.com
 * @create 2019-01-26:上午10:29
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1、收数据
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date()+": 服务端读到数据 -> "+buf.toString(Charset.forName("utf-8")));
        //2、回复数据到客户端
        System.out.println(new Date()+": 服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "您好，欢迎关注马理亚拉的学习记录,查看《马理亚拉学习点滴》".getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
