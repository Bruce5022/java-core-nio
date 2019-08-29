package com.sky.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 服务端
 */
public class TestNioServer {
    public static void main(String[] args) throws Exception {
        server();
    }

    public static void server() throws Exception {
        try {
            // 1.获取通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // 2.切换非阻塞模式
            serverSocketChannel.configureBlocking(false);

            // 3.绑定连接
            serverSocketChannel.bind(new InetSocketAddress(9898));


            // 4.获取选择器
            Selector selector = Selector.open();

            // 5.将通道注册到选择器上,并指定监听事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 6.轮训式的获取选择器上已经准备就绪的事件
            while (selector.select()>0){


            }
            // 3.获取客户端连接的通道
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 4.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 5.接收客户端的数据，并保存到本地
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                byteBuffer.clear();
            }

            // 6.关闭通道
        } catch (IOException e) {
            throw e;
        }
    }
}
