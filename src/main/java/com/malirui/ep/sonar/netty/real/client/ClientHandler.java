package com.malirui.ep.sonar.netty.real.client;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import com.malirui.ep.sonar.netty.real.protocol.PacketCodeC;
import com.malirui.ep.sonar.netty.real.protocol.request.LoginRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.request.MessageRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.response.LoginResponsePacket;
import com.malirui.ep.sonar.netty.real.protocol.response.MessageResponsePacket;
import com.malirui.ep.sonar.netty.real.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午9:50
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+":客户端开始登录");
        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("maliyala");
        loginRequestPacket.setPassword("niubi");

        //编码
        //ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);
        //写数据
        //ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if(loginResponsePacket.isSuccess()){
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date()+":客户端登陆成功");
            }else{
                System.out.println(new Date()+":客户端登录失败，原因："+loginResponsePacket.getReason());
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date()+":收到服务端的消息："+messageResponsePacket.getMessage());

        }
    }
}
