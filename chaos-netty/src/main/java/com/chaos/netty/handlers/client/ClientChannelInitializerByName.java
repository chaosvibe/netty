package com.chaos.netty.handlers.client;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * author: tangzw
 * date: 2024/2/23 17:01
 * description:
 **/
public class ClientChannelInitializerByName extends ChannelInitializer<SocketChannel> {

    private final String handlerName;

    public ClientChannelInitializerByName(String handlerName) {
        this.handlerName = handlerName;
    }
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(getHandlerByName());
    }
    private ChannelHandler getHandlerByName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ChannelHandler) Class.forName(StrUtil.subBefore(this.getClass().getPackage().getName(), StrUtil.DOT, true) + StrUtil.format(".client.{}ClientHandler", handlerName)).newInstance();
    }
}
