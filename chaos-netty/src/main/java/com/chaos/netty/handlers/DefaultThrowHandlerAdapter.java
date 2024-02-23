package com.chaos.netty.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * author: tangzw
 * date: 2024/2/23 17:34
 * description:
 **/
public class DefaultThrowHandlerAdapter extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //Close the connection when an exception is caught
        cause.printStackTrace();
        ctx.close();
    }
}
