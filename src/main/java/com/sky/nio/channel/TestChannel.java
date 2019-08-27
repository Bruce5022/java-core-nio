package com.sky.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 一.通道:用于源节点与目标节点的连接.在java nio中负责缓冲区的传输.channel本身不存储数据,因此需要配合缓冲区进行传输.
 * <p>
 * 二.通道的主要实现类
 * java.nio.channels.Channel 接口:
 * |--FileChannel:本地文件
 * |--SocketChannel:tcp网络传输
 * |--ServerSocketChannel:tcp网络传输
 * |--DatagramChannel:utp网络传输
 * <p>
 * 三.获取通道
 * 1.Java针对支持通道的类提供了getChannel()方法
 * 本地io:FileInputStream/FileOutputStream,RandomAccessFile
 * 网络io:Socket/ServerSocket,DatagramSocket
 * <p>
 * 2.在JDK 1.7中的NIO.2针对各个通道提供了静态方法open()
 * <p>
 * 3.在JDK 1.7中的NIO.2的Files工具类的newByteChannel()
 */
public class TestChannel {

    public static void main(String[] args) throws Exception {

        test1();
    }

    public static void test1() throws Exception {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");

        // 获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 将通道中的数据存入缓冲区中

        while (inChannel.read(buffer) != -1){
            // 将缓冲区中的数据写入通道中
            buffer.flip(); // 切换成读取模式
            outChannel.write(buffer);
            buffer.clear();
        }


        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();
    }
}
