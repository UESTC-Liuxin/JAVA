// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int priceB = in.nextInt();
        int priceP = in.nextInt();
        int sumB=0;
        int sumP=0;
        for(int i=0;i<N;i++){
            if(in.hasNextInt()){
                sumB+=in.nextInt();
                sumP+=in.nextInt();
            }
        }
        sumB=(int)Math.ceil(sumB/10.0);
        sumP=(int)Math.ceil(sumP/10.0);

        System.out.print(sumB*priceB+sumP*priceP);
    }
}