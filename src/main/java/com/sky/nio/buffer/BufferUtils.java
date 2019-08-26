package com.sky.nio.buffer;

import java.nio.ByteBuffer;

public class BufferUtils {

    public static void printLog(ByteBuffer buffer, String method) {
        System.out.println(String.format("-------------------%s--------------------",method));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println();
    }
}
