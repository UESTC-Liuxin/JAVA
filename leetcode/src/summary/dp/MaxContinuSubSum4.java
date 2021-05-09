package summary.dp;

import java.util.Scanner;



// 在限制每个子串长度为m的条件下,求连个不重叠的连续子串的最大和
// 咋一看，3个条件，至少要用3为dp。
// 先把问题拆解为，求m以内长度的连续子串的最大和 以及 如何快速找到两个不重叠区间的最大和
// 从左到右建立一次dp,再从右往左建立dp
public class MaxContinuSubSum4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//数组长度
        int m = scanner.nextInt();//限制长度
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        MaxContinuSubSum4 solution = new MaxContinuSubSum4();
        int[] dp1 = solution.findLeftMax(nums, m); //dp1表示以[0,i]范围最大的连续子串和
        int[] dp2 = solution.findRightMax(nums, m);//dp2表示以[i,N-1]范围内最大连续子串和
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n-1; i++) {
            max = Math.max(max,(dp1[i]+dp2[i+1]));
        }
        System.out.println(max);
    }

    //从前往后
    //整个算法的核心就在这里，把O(mn)的复杂度降低到O(n)
    public int[] findLeftMax(int[] nums, int m) {
        int N = nums.length;
        int[] dp = new int[N]; //dp表示[0,i]范围内最大连续子串和
        int sum = nums[0];  //sum为以nums[i]结尾的最大连续子串和

        dp[0] = sum;
        int length = 1;//初始长度为1
        for (int i = 1; i < N; i++) {
            if (length >= m) { //检查长度
                sum -= nums[i - length]; //减掉最开始的那个数
                length--;
            }
            if (sum < 0) {  //sum < 0,就抛弃之前的，从num[i]开始
                length = 0;
                sum = 0;
            }
            sum = sum + nums[i];
            dp[i] = Math.max(sum, dp[i-1]);
            length++;
        }
        return dp;
    }

    //从后往前
    public int[] findRightMax(int[] nums, int m) {
        int N = nums.length;
        int[] dp = new int[N]; //dp表示[i,N-1]范围内最大连续子串和
        int sum = nums[N-1];  //sum为以nums[i]开头的最大连续子串和

        dp[N-1] = sum;
        int length = 1;//初始长度为1
        for (int i = N-2; i >= 0; i--) {
            if (length >= m) { //检查长度
                sum -= nums[i + length]; //减掉最开始的那个数
                length--;
            }
            if (sum < 0) {  //sum < 0,就抛弃之前的，从num[i]开始
                length = 0;
                sum = 0;
            }
            sum = sum + nums[i];
            dp[i] = Math.max(sum, dp[i+1]);
            length++;
        }
        return dp;
    }

}
