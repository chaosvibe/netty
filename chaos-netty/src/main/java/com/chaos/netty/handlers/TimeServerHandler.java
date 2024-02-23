package com.chaos.netty.handlers;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.chaos.netty.listener.channel.CloseChannelFutureListener;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

/**
 * author: tangzw
 * date: 2024/2/23 17:25
 * description:
 **/
public class TimeServerHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeCharSequence(LocalDateTimeUtil.now().toString() + StrUtil.LF, StandardCharsets.US_ASCII);
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new CloseChannelFutureListener(f, ctx));
    }
}
