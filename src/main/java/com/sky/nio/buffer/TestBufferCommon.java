package com.sky.nio.buffer;


import java.nio.ByteBuffer;

/**
 * 一.缓冲区Buffer:在NIO 中负责数据的存取.缓冲区就是数组.用于存储不同数据类型的数据.
 *
 * 根据数据类型不同(boolean类型除外),提供了相应类型的缓冲区:
 * ByteBuffer(最常用,不管是字符还是文件,不管本地,还是网络都是字节流)
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致,通过allocate()获取缓冲区
 *
 * 二.缓冲区存取数据的两个核心方法:
 * put():存入数据到缓冲区中
 * get():获取缓冲区中的数据
 *
 * 三.缓冲区中的四个核心属性:
 *  mark <= position <= limit <= capacity
 *  mark: 标记,表示记录当前position的位置,可以通过reset()恢复到mark的位置
 *  position: 位置,表示缓冲区中正在操作数据的位置.
 *  limit: 界限或上限,表示缓冲区中可以操作数据的大小.(limit后面的数据不能进行读写)
 *  capacity:容量,表示缓冲区中最大存储数据的容量.一旦声明不能改变.
 */
public class TestBufferCommon {

    public static void main(String[] args) {
        // 1.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        BufferUtils.printLog(buffer,"allocate");

        buffer.put("abc".getBytes());
        BufferUtils.printLog(buffer,"put");

        buffer.flip();
        BufferUtils.printLog(buffer,"flip");


        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes,0,1);
        System.out.println(new String(bytes));
        BufferUtils.printLog(buffer,"get");

        // 设置重复读,再从原始读模式的状态读
        buffer.rewind();
        BufferUtils.printLog(buffer,"rewind");

        // 清空缓冲区,但是缓冲区的数据依然存在,但是处于"被遗忘"的状态
        buffer.clear();
        BufferUtils.printLog(buffer,"clear");
    }
}
