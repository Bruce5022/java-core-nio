package com.sky.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

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
            while (selector.select() > 0) {

                // 7.获取当前选择器上所有注册的"选择键(已就绪的监听事件)"
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    // 8.获取准备"就绪"的事件
                    SelectionKey selectionKey = iterator.next();

                    // 9.判断具体是什么事件准备就绪
                    if(selectionKey.isAcceptable()){
                        // 10.若"接收就绪"，获取客户的连接
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        // 11.切换非阻塞模式
                        socketChannel.configureBlocking(false);

                        // 12.将该通道注册到选择器上
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        // 13.获取当前选择器上"读就绪"状态的通道
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        // 14.读取数据
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int len;
                        while ((len = socketChannel.read(byteBuffer)) != -1) {
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(),0,len));
                            byteBuffer.clear();
                        }
                    }
                    // 15.取消选择键
                    iterator.remove();
                }
            }
            // 6.关闭通道
        } catch (IOException e) {
            throw e;
        }
    }
}
