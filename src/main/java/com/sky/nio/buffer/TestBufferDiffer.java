package com.sky.nio.buffer;

/**
 * 直接缓冲区与非直接缓冲区:
 * 非直接缓冲区:通过allocate()方法分配缓冲区,将缓冲区建立在JVM的内存中
 * 直接缓冲区:通过allocateDirect()方法分配直接缓冲区,将缓冲区建立在os物理内存中,可以提高效率.
 */
public class TestBufferDiffer {
}
