package com.malirui.ep.sonar.netty.real.client.handler;

import com.malirui.ep.sonar.netty.real.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:下午1:52
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId =messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(fromUserId+":"+fromUserName+" -> "+messageResponsePacket.getMessage());
    }
}
