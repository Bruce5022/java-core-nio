package com.sky.nio.channel;

/**
 * 一.通道:用于源节点与目标节点的连接.在java nio中负责缓冲区的传输.channel本身不存储数据,因此需要配合缓冲区进行传输.
 *
 * 二.通道的主要实现类
 * java.nio.channels.Channel 接口:
 *  |--FileChannel:本地文件
 *  |--SocketChannel:tcp网络传输
 *  |--ServerSocketChannel:tcp网络传输
 *  |--DatagramChannel:utp网络传输
 *
 *  三.获取通道
 *  1.Java针对支持通道的类提供了getChannel()方法
 *  本地io:FileInputStream/FileOutputStream,RandomAccessFile
 *  网络io:Socket/ServerSocket,DatagramSocket
 *
 *  2.在JDK 1.7中的NIO.2针对各个通道提供了静态方法open()
 *
 *  3.在JDK 1.7中的NIO.2的Files工具类的newByteChannel()
 */
public class TestChannel {
}
