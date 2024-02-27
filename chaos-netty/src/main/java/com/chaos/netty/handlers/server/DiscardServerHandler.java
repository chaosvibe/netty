package com.chaos.netty.handlers.server;

import com.chaos.netty.handlers.DefaultThrowHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * author: tangzw
 * date: 2024/2/23 15:38
 * description:
 **/
public class DiscardServerHandler extends DefaultThrowHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = ((ByteBuf) msg);
        try {
//            System.out.println(in.toString(StandardCharsets.US_ASCII));  //it's the same
            while (in.isReadable()) {
                System.out.print(new String(new byte[]{in.readByte()}));
            }
        } finally {
            ReferenceCountUtil.release(in);
        }
    }
}
