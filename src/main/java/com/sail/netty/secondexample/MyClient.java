package com.sail.netty.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yangfan
 * @date 2017/05/22
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        // 客户端只需要一个EventLoopGroup
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 9090).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
