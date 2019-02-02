package com.malirui.ep.sonar.netty.real.server;

import com.malirui.ep.sonar.netty.real.codec.PacketDecoder;
import com.malirui.ep.sonar.netty.real.codec.PacketEncoder;
import com.malirui.ep.sonar.netty.real.codec.Spliter;
import com.malirui.ep.sonar.netty.real.server.handler.AuthHandler;
import com.malirui.ep.sonar.netty.real.server.handler.LoginRequestHandler;
import com.malirui.ep.sonar.netty.real.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午9:55
 */
public class NettyServer {
    private static final int PORT = 8009;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        final ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });
         bind(bootstrap,PORT);
    }
    private static void bind(final ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            }else{
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
