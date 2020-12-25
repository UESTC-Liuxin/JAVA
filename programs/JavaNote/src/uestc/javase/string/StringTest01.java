package uestc.javase.string;

public class StringTest01 {
    public static void main(String[] args) {

        String s=new String("abc");
        String s1="abc";
        String s2="abc";
        System.out.println(s==s1);
        System.out.println(s1==s2);
        System.out.println(s1.equals(s));
        String s3=s+"abc";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a");


        s3=s3+String.valueOf("abc");
    }

}
