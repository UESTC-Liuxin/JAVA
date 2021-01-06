package com.javase.io;

import java.io.*;

public class copyDirTest {
    public static void main(String[] args) {
        File src = new File("/media/Program/JAVA/programs/copied");
        File dest = new File("/media/Program/JAVA/programs/copyTo");
        copyFilesDirs(src,dest);

    }

    /**
     * 将指定文件拷贝至指定文件夹下
     * @param src
     * @param dest
     */
    static void copyFilesDirs(File src,File dest){
        if(!src.exists()) return;
        //如果File是文件，直接拷贝
        if(src.isFile()){
            copy(src,dest);
        }
        else{
            File[] files = src.listFiles();
            for(File file: files) {
                String fileDir = file.getAbsolutePath();
                String[] fileDirSplits = fileDir.split("/");
                String lastSrcDir = fileDirSplits[fileDirSplits.length - 1];
                String destDir = dest.getAbsolutePath().endsWith("/") ? dest.getAbsolutePath() : dest.getAbsolutePath() + "/"
                        + lastSrcDir;

                File destFile = new File(destDir);
                //创建相关目录
                if(file.isDirectory()) {
                    if (!destFile.exists()) {
                        destFile.mkdirs();
                    }
                }
                copyFilesDirs(file,destFile);
            }
        }
    }

    static void copy(File src,File dest){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(src.getAbsoluteFile());
            fos = new FileOutputStream(dest.getAbsoluteFile());

            //最核心的：一边读，一边写
            byte[] bytes = new byte[20];

            int readCount=0;
            while ((readCount=fis.read(bytes))!=-1){
                fos.write(bytes,0,readCount);
            }
            //刷新，输出流最后要刷新
            fos.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            //分开 try，不要一起try
            //一起try的时候，可能会造成另一个流无法正常关闭
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
