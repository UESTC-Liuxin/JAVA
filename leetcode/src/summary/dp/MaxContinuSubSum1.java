package summary.dp;


import java.util.Scanner;

//最大连续子串和,不限长度
//
public class MaxContinuSubSum1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = nums[i] + (sum > 0 ? sum : 0); //sum指的只是以nums[i]结尾的sum，所以本质上是一个动态规划问题
            max = sum>max?sum:max;
        }
        System.out.println(max);
    }
}
