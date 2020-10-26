// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

public class MosaicDecorationII {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();

        //先只关注要用多少砖

        //这是两个方向剩余的砖的长度
        int wRremain=W%A;
        int hRremain=W%B;

        int x=(W-wRremain)/A;
        int y=(H-hRremain)/B;
        wRremain=wRremain==0?0:A-wRremain;
        hRremain=hRremain==0?0:B-wRremain;

        //切割的钱
        int seg=
        System.out.print(a + b);
}

