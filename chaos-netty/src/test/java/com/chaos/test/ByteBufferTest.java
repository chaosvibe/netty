package com.chaos.test;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * author: tangzw
 * date: 2024/2/29 15:53
 * description:
 **/
public class ByteBufferTest {

    @Test
    public void test() {
        ByteBuffer bf = ByteBuffer.allocateDirect(8);
        bf.putLong(0xffffffffL);
        System.out.println(bf.getFloat(3));
        System.out.println(bf.getInt(3));
        System.out.println(0xffffff);
        System.out.println((0xfffffffL) / (1L << 32) );
        byte b1 = bf.get(3);
        byte b2 = bf.get(4);
        System.out.println(b1);
        System.out.println(b2);
    }
}
