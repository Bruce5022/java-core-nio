package com.sky.nio.blocknio2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 客户端
 */
public class TestBlockNio2Client {


    public static void main(String[] args) throws Exception {
        client();
    }

    public static void client() throws Exception {
        try {
            // 1.获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            // 2.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 3.读取本地文件,并发送到服务端
            FileChannel inChannel = FileChannel.open(Paths.get("client/README.md"), StandardOpenOption.READ);
            while (inChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            // 55555
            socketChannel.shutdownOutput();

            // 4.接收服务端反馈
            int len;
            while ((len = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(),0,len));
                byteBuffer.clear();
            }



            // 5.关闭通道
            inChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            throw e;
        }
    }

}
