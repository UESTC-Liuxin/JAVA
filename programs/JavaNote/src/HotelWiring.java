import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HotelWiring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();//记录点
        for(int i=0;i<N;i++){
            countLights(in);
        }


    }

    public static void countLights(Scanner in){
        int M=in.nextInt();
        int N=in.nextInt();
        int K=in.nextInt();

        int[] trueLights=new int[M];
        for(int i=0;i<M;i++){
            trueLights[i]=in.nextInt();
        }
        //对正确的灯进行排序
        long sum=0;
        Arrays.sort(trueLights);
        for(int i=0;i<M;i++){
            if(i<K){
                sum+=N-trueLights[i];
            }
            else{
                sum+=trueLights[i];
            }
        }
        System.out.println(sum);

    }
}

