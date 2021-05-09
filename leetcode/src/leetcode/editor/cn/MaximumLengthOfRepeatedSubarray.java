//给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。 
//
// 
//
// 示例： 
//
// 输入：
//A: [1,2,3,2,1]
//B: [3,2,1,4,7]
//输出：3
//解释：
//长度最长的公共子数组是 [3, 2, 1] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= len(A), len(B) <= 1000 
// 0 <= A[i], B[i] < 100 
// 
// Related Topics 数组 哈希表 二分查找 动态规划 
// 👍 441 👎 0

package leetcode.editor.cn;

public class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
        solution.findLength(
                new int[]{1,2,3,2,1},
                new int[]{3,2,1,4,7}
        );
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int maxLen = 0;
            for (int k = 0; k < nums1.length + nums2.length - 1; k++) {
                int start1 = Math.max(0, k - nums2.length + 1);
                int start2 = Math.max(0, nums2.length - 1 - k);
                int ans = 0;
                for (int i = 0; start1 + i < nums1.length && start2 + i < nums2.length; i++) {
                    if (nums1[start1 + i] == nums2[start2 + i])
                        ans++;
                    else
                        ans = 0;
                    maxLen = Math.max(maxLen,ans);
                }

            }
            return maxLen;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
