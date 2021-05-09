package summary.dp;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

//最大连续子串和,限制长度为m
public class MaxContinuSubSum2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//数组长度
        int m = scanner.nextInt();//限制长度
        int[] nums = new int[n];
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            if (queue.size() < m) {
                queue.add(nums[i]);
                sum += nums[i];
            }
        }

        int max = sum;
        for (int i = queue.size(); i < n; i++) {
            int pre = queue.poll();
            queue.offer(nums[i]);
            sum = sum - pre + nums[i];
            max = max > sum ? max : sum;
        }
        System.out.println(max);
    }
}
