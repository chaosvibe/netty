package com.chaos.netty.handlers;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * author: tangzw
 * date: 2024/2/23 17:01
 * description:
 **/
public class ChannelInitializerByName extends ChannelInitializer<SocketChannel> {

    private String handlerName;

    public ChannelInitializerByName(String handlerName) {
        this.handlerName = handlerName;
    }
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(getHandlerByName());
    }
    private ChannelHandler getHandlerByName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ChannelHandler) Class.forName(StrUtil.subBefore(this.getClass().getPackage().getName(), StrUtil.DOT, true) + StrUtil.format(".handlers.{}ServerHandler", handlerName)).newInstance();
    }
}
