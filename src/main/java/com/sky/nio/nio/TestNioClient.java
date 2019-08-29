package com.sky.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 客户端
 */
public class TestNioClient {


    public static void main(String[] args) throws Exception {
        client();
    }

    public static void client() throws Exception {
        try {
            // 1.获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            // 2.切换非阻塞模式
            socketChannel.configureBlocking(false);

            // 3.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 4.读取本地文件,并发送到服务端
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String str = scanner.next();
                byteBuffer.put(str.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            // 5.关闭通道
            socketChannel.close();
        } catch (IOException e) {
            throw e;
        }
    }

}
