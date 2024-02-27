package com.chaos.netty.handlers.server;

import com.chaos.netty.handlers.DefaultThrowHandlerAdapter;
import com.chaos.netty.listener.channel.CloseChannelFutureListener;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;


/**
 * author: tangzw
 * date: 2024/2/23 17:25
 * description:
 **/
public class TimeServerHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new CloseChannelFutureListener(f, ctx));
    }
}
