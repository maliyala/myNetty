package com.malirui.ep.sonar.netty.real.client.handler;

import com.malirui.ep.sonar.netty.real.protocol.PacketCodeC;
import com.malirui.ep.sonar.netty.real.protocol.request.LoginRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.response.LoginResponsePacket;
import com.malirui.ep.sonar.netty.real.session.Session;
import com.malirui.ep.sonar.netty.real.utils.LoginUtil;
import com.malirui.ep.sonar.netty.real.utils.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:下午1:47
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserid();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserid());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
