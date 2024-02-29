package com.chaos.netty.handlers.client;

import cn.hutool.core.util.StrUtil;
import com.chaos.netty.handlers.AbstractPreDecoderChannelInboundInitializer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * author: tangzw
 * date: 2024/2/23 17:01
 * description:
 **/
public class ClientChannelInboundInitializerByName extends AbstractPreDecoderChannelInboundInitializer {

    public ClientChannelInboundInitializerByName(String handlerName) {
        super(handlerName);
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        super.initChannel(ch);
        ch.pipeline().addLast(getHandlerByName());
    }

    private ChannelHandler getHandlerByName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ChannelHandler) Class.forName(StrUtil.subBefore(this.getClass().getPackage().getName(), StrUtil.DOT, true) + StrUtil.format(".client.{}ClientHandler", handlerName)).newInstance();
    }
}
