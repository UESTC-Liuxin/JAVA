package com.javase.io;

import java.io.*;

public class DataOutputStreamTest {
    public static void main(String[] args) throws Exception{
        //创建数据专属的字节输出
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data"));

        //写数据
        byte b = 100;
        short t = 200;
        float f = 1.62F;
        long l = 1522;
        double d = 0.36;
        boolean sex = true;
        char c = 'a';

        dos.writeByte(b);
        dos.writeShort(t);
        dos.writeFloat(f);
        dos.writeLong(l);
        dos.writeDouble(d);
        dos.writeBoolean(sex);
        dos.writeChar(c);

        //刷新
        dos.flush();
        //关闭最外层
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream("data"));
        //写数据
        b = dis.readByte();
        t = dis.readShort();
        f = dis.readFloat();
        l = dis.readLong();
        d = dis.readDouble();
        sex = dis.readBoolean();
        c = dis.readChar();
    }

}
