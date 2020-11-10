package leetcode.editor.cn;

//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2519 👎 0


/*
这道题用动态规划，常规思路，将dp[n]设置为最佳结果，但是这样很难实现，无法兼顾连续数组的条件
于是换个思路：dp[n]记录以nums[n]为结尾的连续数组的最大值。只要比较从1---n的dp就能得到结果
关键点：dp[i]=dp[i-1]>0?dp[i-1]+nums[i]:nums[i]
*/

public class MaximumSubarray{
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        int[] nums={-2,1,-3,4,-1,2,1-5,4};
        System.out.println(solution.maxSubArray(nums));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        int sum=nums[0];
        int max=sum;
        for(int i=1;i< nums.length;i++){
            sum=sum>0?sum+nums[i]:nums[i];
            max=Math.max(sum,max);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}