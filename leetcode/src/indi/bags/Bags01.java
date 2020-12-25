package indi.bags;
//01 背包问题

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Bags01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] weights=new int[]{3,3,4,6};
        int[] values =new int[]{3,4,5,6};
//        System.out.println(solution.knapsack(weights,values,4,8));
        solution.findBestCombines(weights,17);
    }
}

class Solution{


//    public int knapsack(int[] weights,int[] values,int N,int C){
//        //F[i][j]表示在容量为j的前提下，前i个物品中能获得的最大的价值
//        int[][] f=new int[N+1][C+1];
//        for(int i=1;i<N+1;i++)
//            for (int j=1;j<(C+1);j++){
//                if(j<weights[i-1])
//                    f[i][j]=f[i-1][j];
//                else
//                    f[i][j]=Math.max(f[i][j-1],f[i-1][j-weights[i-1]]+values[i-1]);
//            }
//        return f[N][C];
//    }

    Stack<Integer> best_method;
    int weightsCount=0;
    int[] Weights;
    int minC=Integer.MAX_VALUE;

    public void findBestCombines(int[] weights,int C){
        weightsCount=weights.length;
        Weights=weights;
        backTrace(0, C, new Stack<Integer>());
    }
    //利用回溯算法进行所有集合的运算
    public void backTrace(int start,int C,Stack<Integer> method){
        if(C<0){//结束条件，容量越界，直接移除最后一个，并结束
            Stack<Integer> temp=(Stack<Integer>) method.clone();
            int last=temp.pop();
            if(!temp.isEmpty()&&(C+Weights[last])<minC) {
                best_method = temp;
                minC =C+Weights[last];
            }
            return;
        }
        for(int i=start;i<weightsCount;i++){
            method.push(i);
            backTrace(i+1,C-Weights[i],method);
            method.pop();//移除最后一个
        }

    }

}