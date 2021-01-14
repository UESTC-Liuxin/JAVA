
//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 363 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        List<List<Integer>> allSets;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            allSets = new ArrayList<>();
            List<Integer> subSet = new ArrayList<>();
            allSets.add(subSet);
            if (nums.length < 1) return allSets;
            //先对数组进行排序
            Arrays.sort(nums);
            backTracking(subSet, 0, nums);
            return allSets;

        }

        private void backTracking(List<Integer> subSet, int startIndex, int[] nums) {
            if (startIndex >= nums.length) return;
            //初始化一个不可能的前值
            int pre = Integer.MAX_VALUE;
            for (int i = startIndex; i < nums.length; i++) {
                if (pre == nums[i]) continue;
                pre = nums[i];
                subSet.add(pre);
                allSets.add(new ArrayList<>(subSet));
                backTracking(subSet, i + 1, nums);
                subSet.remove(subSet.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}