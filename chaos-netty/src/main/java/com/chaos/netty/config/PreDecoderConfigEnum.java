package com.chaos.netty.config;

import cn.hutool.core.collection.CollUtil;
import com.chaos.netty.decoder.TimeDecoder;
import io.netty.channel.ChannelInboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: tangzw
 * date: 2024/2/29 10:20
 * description:
 **/
@Getter
@Slf4j
public enum PreDecoderConfigEnum {
    TIME_HANDLER("time", TimeDecoder.class),
    ;
    private final String handlerName;
    private final List<Class<? extends ChannelInboundHandler>> decoderClazz;

    @SafeVarargs
    PreDecoderConfigEnum(String handlerName, Class<? extends ChannelInboundHandler>...decoderClasses) {
        this.handlerName = handlerName;
        this.decoderClazz = CollUtil.newArrayList(decoderClasses);
    }

    public static List<? extends ChannelInboundHandler> getDecoders(String handlerName) {
        for (PreDecoderConfigEnum conf : values()) {
            if (conf.getHandlerName().equalsIgnoreCase(handlerName)) {
                return conf.getDecoderClazz().stream().map(clz -> {
                    try {
                        return clz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("pre-decoder instant error: {}", e.toString());
                        return null;
                    }
                }).collect(Collectors.toList());
            }
        }

        return CollUtil.newArrayList();
    }
}
