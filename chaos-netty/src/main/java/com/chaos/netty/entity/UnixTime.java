package com.chaos.netty.entity;

import lombok.Getter;

import java.util.Date;

/**
 * author: tangzw
 * date: 2024/2/29 15:06
 * description:
 **/
@Getter
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new Date((value - 2208988800L) * 1000L).toString();
    }
}
