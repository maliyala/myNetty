package com.malirui.ep.sonar.netty.server;

import com.malirui.ep.sonar.netty.server.ChatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 用来增加多个的处理类到 ChannelPipeline 上，包括编码、解码、SimpleChatServerHandler 等。
 */
public class ChatServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("HttpResponseEncoder",new HttpResponseEncoder());
        pipeline.addLast("HttpRequestDecoder",new HttpRequestDecoder());
        pipeline.addLast("chathandler", new ChatHandler());

        System.out.println("ChatClient:"+ch.remoteAddress() +"连接上");

    }

}