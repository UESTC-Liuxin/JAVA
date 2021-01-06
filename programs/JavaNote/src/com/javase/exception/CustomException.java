package com.javase.exception;

public class CustomException extends Exception{//
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}


class CustomExceptionTest{
    public static void main(String[] args) {
        CustomException e =new CustomException("自定义异常");
        e.printStackTrace();

    }
}
