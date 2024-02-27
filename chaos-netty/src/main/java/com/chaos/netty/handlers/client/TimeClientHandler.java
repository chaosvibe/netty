package com.chaos.netty.handlers.client;

import com.chaos.netty.handlers.DefaultThrowHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * author: tangzw
 * date: 2024/2/27 16:45
 * description:
 **/
public class TimeClientHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }
}
