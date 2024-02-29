package com.chaos.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.chaos.netty.handlers.client.ClientChannelInboundInitializerByName;

/**
 * author: tangzw
 * date: 2024/2/27 16:40
 * description:
 **/
public class Handler {

    public static void main(String[] args) {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String handlerName = args[2];
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ClientChannelInboundInitializerByName(handlerName));

            //Start the client
            ChannelFuture f = b.connect(host, port).sync();

            //Wait until the connection is closed
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
