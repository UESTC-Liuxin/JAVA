package summary.dp;
import java.util.Scanner;

//最大连续子串和,限制长度<=m
//按照思路，我们以num[i]作为结尾
//思维还是动态规划，两个条件，就dp为两维，dp[N][M+1],其中M表示长度
//原本搜索的过程是dp[i][j]=dp[i-1][j-1]+num[i]
//但是碰到j>m和num[i][j]<0时就会向上，整个搜索过程就是一个之字形过程（之字形，双指针好搭档）
//然后发现dp[i-1][j-1]可以用sum来代替

public class MaxContinuSubSum3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//数组长度
        int m = scanner.nextInt();//限制长度
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        //题解开始
        int max = Integer.MIN_VALUE;
        int sum = nums[0];  //sum为以nums[i]结尾的最大连续子串和
        int length = 1;//初始长度为1
        for (int i = 1; i < n; i++) {
            if (length >= m) { //检查长度
                sum -= nums[i - length]; //减掉最开始的那个数
                length--;
            }
            if (sum < 0) {  //sum < 0,就抛弃之前的，从num[i]开始
                length = 0;
                sum = 0;
            }
            sum = sum + nums[i];
            max = Math.max(sum, max);
            length++;
        }
        System.out.println(max);
    }
}
