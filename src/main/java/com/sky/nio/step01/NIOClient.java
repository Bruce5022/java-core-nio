package com.sky.nio.step01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NIOClient {
    /**
     * 通道
     */
    SocketChannel channel;

    public void initClient(String host, int port) throws IOException {
        //构造socket连接
        InetSocketAddress servAddr = new InetSocketAddress(host, port);

        //打开连接
        this.channel = SocketChannel.open(servAddr);
    }

    public void sendAndRecv(String words) throws IOException {
        byte[] msg = new String(words).getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(msg);
        System.out.println("Client sending: " + words);
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        System.out.println("Client received: " + new String(buffer.array()).trim());

        channel.close();
    }

    public static void main(String[] args) throws IOException {
//        NIOClient client = new NIOClient();
//        client.initClient("localhost", 8080);
//        client.sendAndRecv("I am a client");

        List<Integer> list = new ArrayList<>();
        for (int row = 0; row < 10; row++) {
            list.add(row);
        }
        System.out.println(list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println( iterator.next());
            iterator.remove();
        }
        System.out.println(list);
    }
}