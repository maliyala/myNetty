package com.malirui.ep.sonar.netty.real.server.handler;

import com.malirui.ep.sonar.netty.real.protocol.request.MessageRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.response.MessageResponsePacket;
import com.malirui.ep.sonar.netty.real.session.Session;
import com.malirui.ep.sonar.netty.real.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        //1.拿到消息发送方得会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        //2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        //3.拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        //4.将消息发送给消息接收方
        if(toUserChannel != null&&SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else{
            System.out.println("["+messageRequestPacket.getToUserId()+"]不在线，发送失败!");
        }

    }
}
