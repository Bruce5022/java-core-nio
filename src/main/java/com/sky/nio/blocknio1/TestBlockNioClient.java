package com.sky.nio.blocknio1;

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
public class TestBlockNioClient {


    public static void main(String[] args) throws Exception {
        client();
    }

    public static void client() throws Exception {
        try ( // 1.获取通道
              SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
              // 3.读取本地文件,并发送到服务端
              FileChannel inChannel = FileChannel.open(Paths.get("client/README.md"), StandardOpenOption.READ);
        ){

            // 2.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            // 4.关闭通道
        } catch (IOException e) {
            throw e;
        }
    }

}
