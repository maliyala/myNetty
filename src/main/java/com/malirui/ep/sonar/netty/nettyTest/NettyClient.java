package com.malirui.ep.sonar.netty.nettyTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author maliruimeituan.com
 * @create 2019-01-25:下午5:55
 */
public class NettyClient {
    private static int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                .group(group)//1.指定线程模型
                .channel(NioSocketChannel.class)//2.指定IO类型为NIO
                .handler(new ChannelInitializer<Channel>() {//IOc处理器
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        connect(bootstrap,"127.0.01",9099,MAX_RETRY);
        /*Channel channel = bootstrap.connect("127.0.0.1", 8099).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }*/
    }
    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("连接成功！");
            }else if(retry==0){
                System.out.println("重试次数已经用完，放弃连接！");
            }else{
                //第几次重连
                int order = (MAX_RETRY-retry)+1;
                //本次重连的间隔
                int delay = 1 << order;
                System.out.println(delay);
                System.out.println(new Date()+"连接失败，第"+order+"次重连。。。");
                bootstrap.config().group().schedule(()->
                        connect(bootstrap,host,port,retry-1),delay,TimeUnit.SECONDS);
            }
        });
    }
}
