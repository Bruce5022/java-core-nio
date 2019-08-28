package com.sky.nio.channel;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * 五.分散Scatter与聚集Gather
 * 分散读取Scattering reads：将通道中的数据分散到多个缓冲区中
 * 聚集写入Gather writes：将多个缓冲区的数据聚集到一个通道
 */

public class TestScatterAndGather {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {

        RandomAccessFile inRandomAccessFile = new RandomAccessFile("README.md","rw");

        // 1.获取通道
        FileChannel inChannel = inRandomAccessFile.getChannel();
        // 2.获取指定大小的缓冲区
        ByteBuffer[] buffers = new ByteBuffer[]{ByteBuffer.allocate(100),ByteBuffer.allocate(200)};
        inChannel.read(buffers);
        for (ByteBuffer buffer:buffers){
            buffer.flip();
        }
        System.out.println(new String(buffers[0].array(),0,buffers[0].limit()));
        System.out.println(new String(buffers[1].array(),0,buffers[1].limit()));

        RandomAccessFile outRandomAccessFile = new RandomAccessFile("README2.md","rw");
        FileChannel outChannel = outRandomAccessFile.getChannel();
        outChannel.write(buffers);


        inChannel.close();
        outChannel.close();
        inRandomAccessFile.close();
        outRandomAccessFile.close();
    }
}
