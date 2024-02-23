package com.chaos.netty.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * author: tangzw
 * date: 2024/2/23 16:44
 * description:
 **/
public class EchoServerHandler extends DefaultThrowHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("received data:");
        System.out.println(((ByteBuf) msg).toString(CharsetUtil.US_ASCII));
        System.out.flush();
        ctx.write(msg);
        ctx.flush();
    }
}
