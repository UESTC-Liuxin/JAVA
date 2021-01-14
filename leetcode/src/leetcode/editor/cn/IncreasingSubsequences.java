
//给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。 
//
// 示例: 
//
// 
//输入: [4, 6, 7, 7]
//输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7
//]] 
//
// 说明: 
//
// 
// 给定数组的长度不会超过15。 
// 数组中的整数范围是 [-100,100]。 
// 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。 
// 
// Related Topics 深度优先搜索 
// 👍 238 👎 0

package leetcode.editor.cn;

import java.util.*;

public class IncreasingSubsequences {
    public static void main(String[] args) {
        Solution solution = new IncreasingSubsequences().new Solution();
        solution.findSubsequences(new int[]{1,2,1,1});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> allSets;
    public List<List<Integer>> findSubsequences(int[] nums) {
        allSets = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
        if (nums.length < 2) return allSets;
        backTracking(subSet, 0,nums);
        return allSets;

    }

    private void backTracking(List<Integer> subSet, int startIndex,
                              int[] nums) {
        if (startIndex >= nums.length) return;
        //初始化一个不可能的前值
        Set used = new HashSet();
        int subMax = (subSet.size()==0)?
                Integer.MIN_VALUE:subSet.get(subSet.size()-1);
        for (int i = startIndex; i < nums.length; i++) {
            //重复值和降序列跳过
            if (used.contains(nums[i]) || nums[i]<subMax) continue;
            used.add(nums[i]);
            subSet.add(nums[i]);
            if(subSet.size()>=2)
                allSets.add(new ArrayList<>(subSet));
            backTracking(subSet, i + 1,nums);
            subSet.remove(subSet.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}