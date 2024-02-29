package com.chaos.netty.config;

import cn.hutool.core.collection.CollUtil;
import com.chaos.netty.encoder.TimeEncoder;
import io.netty.channel.ChannelOutboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: tangzw
 * date: 2024/2/29 15:18
 * description:
 **/
@Getter
@Slf4j
public enum AfterEncoderConfigEnum {
    TIME_ENCODER("time", TimeEncoder.class);
    ;

    private final String handlerName;
    private final List<Class<? extends ChannelOutboundHandler>> encoderClasses;

    @SafeVarargs
    AfterEncoderConfigEnum(String handlerName, Class<? extends ChannelOutboundHandler>... encoderClasses) {
        this.handlerName = handlerName;
        this.encoderClasses = CollUtil.newArrayList(encoderClasses);
    }

    public static List<? extends ChannelOutboundHandler> getEncoders(String handlerName) {
        for (AfterEncoderConfigEnum conf : values()) {
            if (conf.getHandlerName().equalsIgnoreCase(handlerName)) {
                return conf.getEncoderClasses().stream().map(clz -> {
                    try {
                        return clz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("after-encoder instant error: {}", e.toString());
                        return null;
                    }
                }).collect(Collectors.toList());
            }
        }

        return CollUtil.newArrayList();
    }
}
