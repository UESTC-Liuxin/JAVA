package leetcode.editor.cn;

//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划 
// 👍 1129 👎 0

public class LongestIncreasingSubsequence{
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
        int[] nums=new int[]{1,3,6,7,9,4,10,5,6};
        solution.lengthOfLIS(nums);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int length=nums.length;
        int[] dp =new int[length];
        int result=0;
        for(int i=0;i<length;i++) {
            int max=0;
            for (int j = 0; j <=i; j++) {
                if (nums[j] <nums[i]) {
                    max = Math.max(max, dp[j]);
                    dp[i]=max;
                }
            }
            dp[i]+=1;
            result = Math.max(result,dp[i]);
        }
        System.out.println(result);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}