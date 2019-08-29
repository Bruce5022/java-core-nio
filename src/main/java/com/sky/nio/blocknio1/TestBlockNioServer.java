package com.sky.nio.blocknio1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 服务端
 */
public class TestBlockNioServer {
    public static void main(String[] args) throws Exception {
        server();
    }

    public static void server() throws Exception {
        try {
            // 1.获取通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // 2.绑定连接
            serverSocketChannel.bind(new InetSocketAddress(9898));
            FileChannel outChannel = FileChannel.open(Paths.get("server/README.md"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // 3.获取客户端连接的通道
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 4.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 5.接收客户端的数据，并保存到本地
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            // 6.关闭通道
        } catch (IOException e) {
            throw e;
        }
    }
}
