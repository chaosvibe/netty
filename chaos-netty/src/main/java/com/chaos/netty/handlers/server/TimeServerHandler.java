package com.chaos.netty.handlers.server;

import com.chaos.netty.entity.UnixTime;
import com.chaos.netty.handlers.DefaultThrowHandlerAdapter;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;


/**
 * author: tangzw
 * date: 2024/2/23 17:25
 * description:
 **/
public class TimeServerHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
    }
}
