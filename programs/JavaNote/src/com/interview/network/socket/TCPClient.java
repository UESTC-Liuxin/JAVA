package com.interview.network.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws  Exception{
        Socket socket = new Socket("127.0.0.1",65000);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        //只能写入Byte数组
        os.write(new String("hello world").getBytes());
        //
        int ch=0;
        byte[] buff = new byte[1024];
        //从流中读入
        ch=is.read(buff);
        //
        String content = new String(buff,0,ch);
        System.out.println(content);
        is.close();
        os.close();
        socket.close();
    }
}


