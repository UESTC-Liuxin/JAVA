package com.interview.network.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(65001);
        byte[] buff = new byte[100];
        //接受客户端发送过来的内容，并将内容封装进DatagramPacket
        DatagramPacket packet = new DatagramPacket(buff,buff.length);
        //接受客户端发过来的请求,这里应该是阻塞的
        socket.receive(packet);

        byte[] data = packet.getData();
        String content = new String(data,0,packet.getLength());
        System.out.println(content);
        //将要发送给客户端的数据转成二进制
        byte[] sendedContent =String.valueOf(content.length()).getBytes();
        //服务端给客户短发送数据报
        //从DatagramPacket对象中获取到数据的来源地址与端口号
        DatagramPacket packetToClient =new DatagramPacket(sendedContent,sendedContent.length,
                packet.getAddress(),packet.getPort());

        socket.send(packetToClient);


        //可以看出来这是一个无主从无连接的服务器，接收到一个数据包之后，就没有任何关系了，
        // 进行回复就是往对面地址发一个数据包
    }


}
