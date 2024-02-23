package com.chaos.netty.listener.channel;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

/**
 * author: tangzw
 * date: 2024/2/23 17:31
 * description:
 **/
public class CloseChannelFutureListener implements ChannelFutureListener {

    private final ChannelFuture originalFuture;
    private final ChannelHandlerContext ctx;

    public CloseChannelFutureListener(ChannelFuture originalFuture, ChannelHandlerContext ctx) {
        this.originalFuture = originalFuture;
        this.ctx = ctx;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        assert originalFuture == future;
        ctx.close();
    }
}
