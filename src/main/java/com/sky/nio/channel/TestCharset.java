package com.sky.nio.channel;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class TestCharset {

    public static void main(String[] args)  throws Exception {
        test2();
    }

    public static void test1() {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();

        System.out.println(stringCharsetSortedMap);
    }

    public static void test2() throws Exception {
        Charset charset = Charset.forName("gbk");
        System.out.println(charset);

        // 获取编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();

        // 获取解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("史战伟");

        charBuffer.flip();

        ByteBuffer encode = charsetEncoder.encode(charBuffer);

        for(int i=0;i<6;i++){
            System.out.println(encode.get());
        }

        // 解码
        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode);
    }
}
