package com.interview.network.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        //创建socket，使得socket绑定到6500端口
        ServerSocket ss = new ServerSocket(65000);

        while(true){
            Socket socket = ss.accept();
            new LengthCaculator(socket).start();
        }

    }
}


class LengthCaculator extends Thread{
    private Socket socket;

    public LengthCaculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取socket的输出流
            OutputStream os = socket.getOutputStream();
            //获取socket的输入流
            InputStream is = socket.getInputStream();
            int ch = 0;
            byte[] buff = new byte[1024];
            //buff 主要用来读取输入的内容，存成byte数组，ch主要用来获取读取数组的
            ch=is.read(buff);
            //将字节流的byte数组转换成字符串，这里获取的内容是客户端
            String content = new String(buff,0,ch);
            System.out.println(content);
            //往输出流里写入获得的字符串的长度，会发给客户端
            os.write(String.valueOf(content.length()).getBytes());

            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
