package leetcode.editor.cn;
//给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回
// 0。 
//
// 
//
// 示例： 
//
// 输入：s = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。 
// 
// Related Topics 数组 双指针 二分查找 
// 👍 510 👎 0


public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
//         双指针法
        int minLength=nums.length+1;
        int sum=0;
        for(int fastPoint=0,slowPoint=0;fastPoint<nums.length;fastPoint++){
            sum+=nums[fastPoint];//fastPoint每向前移动一次就对sum加一次
            while(sum>=s){
                //slowPoint的移动
                int length=fastPoint-slowPoint+1;//记录窗口的宽度
                minLength=Math.min(length,minLength);
                sum-=nums[slowPoint++]; //slowpoint移动前减掉当前的值
            }
        }
        if(minLength>nums.length) return 0;
        return minLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
