package interview.tencent;

import java.util.Scanner;

public class MaxValue {
    long[][] matrix;
    long[][][] dp;

    public static void main(String[] args) {
        MaxValue maxValue =new MaxValue();
        maxValue.fun();
    }
    public void fun() {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int n = Integer.valueOf(params[0]);
        int m = Integer.valueOf(params[1]);
        matrix = new long[n][m];
        for (int i = 0; i < n; i++) {
            String[] nums = scanner.nextLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.valueOf(nums[j]);
            }
        }
        dp = new long[n][m][2];
        getDp(0,0,matrix[0][0]);
        for (int i = 1; i < n; i++){
            getDp(0,i,dp[0][i - 1][0] * matrix[0][i]);
            getDp(0,i,dp[0][i - 1][1] * matrix[0][i]);
        }
        for (int i = 1; i < m; i++){
            getDp(i,0,dp[i - 1][0][0] * matrix[i][0]);
            getDp(i,0,dp[i - 1][0][1] * matrix[i][0]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][j]>0){
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i][j-1][1])*matrix[i][j]%1000000007;
                    dp[i][j][0]=0;
                }
                else{
                    dp[i][j][0]=Math.min(dp[i-1][j][0],dp[i][j-1][0])*matrix[i][j]%1000000007;
                    dp[i][j][1]=0;
                }
            }
        }
        if(Math.abs(dp[n-1][m-1][0])>dp[n-1][m-1][1])
            System.out.println(-1);
        else
            System.out.println(dp[n-1][m-1][1]);
    }
    void getDp(int i,int j,long value){
        int k=0;
        if(value>0)
            k=1;
        dp[i][j][k]=value;
    }


}
