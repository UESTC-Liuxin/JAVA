package uestc.javase.exception;

public class ExceptionTest04 {
    public static void main(String[] args) {
        NullPointerException e =new NullPointerException("空指针异常");
        String msg= e.getMessage();
        System.out.println(msg);

        //打印异常堆栈信息
        e.printStackTrace();

    }
}
