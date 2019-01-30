package com.malirui.ep.sonar.netty.real.server;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import com.malirui.ep.sonar.netty.real.protocol.PacketCodeC;
import com.malirui.ep.sonar.netty.real.protocol.request.LoginRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.request.MessageRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.response.LoginResponsePacket;
import com.malirui.ep.sonar.netty.real.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:04
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 收到客户端登录请求……");

        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        //判断是否是登录请求的数据包
        if(packet instanceof LoginRequestPacket){
            System.out.println(new Date() +":客户端开始登陆。。。。。");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            //验证登录
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            //编码登录响应
           /* ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);*/
        }else if(packet instanceof MessageRequestPacket){
            //客户端发来消息
            /*MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date()+"收到客户端的消息:"+messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复:你好『"+messageRequestPacket.getMessage()+"』");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);*/

        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
