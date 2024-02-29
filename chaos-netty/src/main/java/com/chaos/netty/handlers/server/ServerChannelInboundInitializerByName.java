package com.chaos.netty.handlers.server;

import cn.hutool.core.util.StrUtil;
import com.chaos.netty.handlers.AbstractPreDecoderChannelInboundInitializer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * author: tangzw
 * date: 2024/2/23 17:01
 * description:
 **/
public class ServerChannelInboundInitializerByName extends AbstractPreDecoderChannelInboundInitializer {

    public ServerChannelInboundInitializerByName(String handlerName) {
        super(handlerName);
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        super.initChannel(ch);
        ch.pipeline().addLast(getHandlerByName());
    }

    private ChannelHandler getHandlerByName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ChannelHandler) Class.forName(StrUtil.subBefore(this.getClass().getPackage().getName(), StrUtil.DOT, true) + StrUtil.format(".server.{}ServerHandler", handlerName)).newInstance();
    }
}
