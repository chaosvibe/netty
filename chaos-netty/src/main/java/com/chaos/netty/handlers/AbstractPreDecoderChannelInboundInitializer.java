package com.chaos.netty.handlers;

import com.chaos.netty.config.PreDecoderConfigEnum;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.List;
import java.util.Objects;

/**
 * author: tangzw
 * date: 2024/2/29 10:59
 * description:
 **/
public abstract class AbstractPreDecoderChannelInboundInitializer extends ChannelInitializer<SocketChannel> {
    protected final String handlerName;

    protected AbstractPreDecoderChannelInboundInitializer(String handlerName) {
        this.handlerName = handlerName;
    }

    protected List<? extends ChannelInboundHandler> getPreDecoders() {
        return PreDecoderConfigEnum.getDecoders(handlerName);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        List<? extends ChannelInboundHandler> preDecoders = getPreDecoders();
        preDecoders.stream().filter(Objects::nonNull).forEach(ch.pipeline()::addLast);
    }
}
