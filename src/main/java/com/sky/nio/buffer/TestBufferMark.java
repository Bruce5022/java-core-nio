package com.sky.nio.buffer;

import java.nio.ByteBuffer;

public class TestBufferMark {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("abc".getBytes());

        buffer.flip();


        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes,0,1);
        System.out.println(new String(bytes));
        BufferUtils.printLog(buffer,"get1");

        // mark()标记当前position位置
        buffer.mark();

        bytes = new byte[buffer.limit()];
        buffer.get(bytes,1,1);
        System.out.println(new String(bytes));
        BufferUtils.printLog(buffer,"get2");

        buffer.reset();
        bytes = new byte[buffer.limit()];
        buffer.get(bytes,1,1);
        System.out.println(new String(bytes));
        BufferUtils.printLog(buffer,"get3");


    }

}