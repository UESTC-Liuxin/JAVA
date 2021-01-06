package com.interview.network.socket;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket();
        byte[] buff = "Hello World".getBytes();

        //将ip地址封装成InetAddress对象
        InetAddress address = InetAddress.getByName("127.0.0.1");
        //将数据封装成包
        DatagramPacket packet = new DatagramPacket(buff,buff.length,address,65001);
        //发送到服务器
        socket.send(packet);

        //客户端接受服务端发送过来的数据报
        byte[] data = new byte[100];
        DatagramPacket receivedPacket = new DatagramPacket(data,data.length);
        //将接收到的数据存储到DatagramPacket对象中
        socket.receive(receivedPacket);
        //将服务器端发送过来的数据取出来并打印到控制台
        String content = new String(receivedPacket.getData(),0,receivedPacket.getLength());
        System.out.println(content);

    }
}
