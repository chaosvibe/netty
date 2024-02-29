package com.chaos.netty.handlers.client;

import com.chaos.netty.entity.UnixTime;
import com.chaos.netty.handlers.DefaultThrowHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * author: tangzw
 * date: 2024/2/27 16:45
 * description:
 **/
public class TimeClientHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime unixTime = (UnixTime) msg;
        System.out.println(unixTime);
        ctx.close();
    }
}
