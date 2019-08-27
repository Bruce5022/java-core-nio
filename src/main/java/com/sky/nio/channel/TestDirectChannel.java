package com.sky.nio.channel;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestDirectChannel {

    public static void main(String[] args) throws Exception {
        test1();
    }

    /**
     * 2.使用直接缓冲区完成文件的复制(内存映射文件)
     * 耗时：0.09
     * 结论：这个是速度很快，但有时程序执行完了，流没有及时释放，比写文件到直接内存慢很多，不稳定
     * @throws Exception
     */
    public static void test1() throws Exception {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("111.qsv"), StandardOpenOption.READ);


        // 解决异常：Exception in thread "main" java.nio.channels.NonWritableChannelException
        // 因为outMappedByteBuffer设置的是读写模式，但channel设置的是

        FileChannel outChannel = FileChannel.open(Paths.get("333.qsv"), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE, StandardOpenOption.READ);
        // 内存映射文件
        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dst);
        outMappedByteBuffer.put(dst);

        outChannel.close();
        inChannel.close();
        System.out.println("耗时：" + (System.currentTimeMillis()-start)/1000.0);
    }



    // 3.通道之间的数据传输transferFrom()和transferTo()(直接缓冲区的方式)
    // 耗时：0.044
    public static void test2() throws Exception {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("111.qsv"), StandardOpenOption.READ);

        FileChannel outChannel = FileChannel.open(Paths.get("333.qsv"), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE, StandardOpenOption.READ);

        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.close();
        inChannel.close();
        System.out.println("耗时：" + (System.currentTimeMillis()-start)/1000.0);
    }
}
