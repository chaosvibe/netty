package com.chaos.netty.discard;

import com.chaos.netty.handlers.ChannelInitializerByName;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * author: tangzw
 * date: 2024/2/23 16:00
 * description:
 **/
public class Server {

    private int port;
    private String handlerName;

    public Server(int port, String handlerName) {
        this.port = port;
        this.handlerName = handlerName;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializerByName(handlerName))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //Bind and start to accept incoming connections
            ChannelFuture f = b.bind(port).sync();

            //Wait until the server socket is closed.
            //In this example this situation does not happen, but you can do that to gracefully
            //shut down you server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8081;
        String defaultHandler = "Discard";
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            defaultHandler = args[1];
        }
        new Server(port, defaultHandler).run();
    }
}
