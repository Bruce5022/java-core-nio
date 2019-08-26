package com.sky.nio.buffer;

import java.nio.ByteBuffer;

public class TestBufferRemain {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("abc".getBytes());

        // 判断缓冲区中是否有可以操作的空间
        // 写模式是可以写入的空间
        // 读模式是可以读取的空间
        buffer.flip();


        if(buffer.hasRemaining()){
            System.out.println(buffer.remaining());
        }
    }
}
