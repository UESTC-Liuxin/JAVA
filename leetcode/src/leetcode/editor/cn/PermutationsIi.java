
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 557 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> allCombination;
    public List<List<Integer>> permuteUnique(int[] nums) {
        allCombination = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> combination = new ArrayList<>();
        //这里用数组的效率要远远高于用set
        int[] visited=new int[nums.length];
        backTracking(combination,visited,nums);
        return allCombination;
    }
        private void backTracking(List<Integer> combination,int[] visited,int[] nums) {
            if(combination.size()==nums.length) {
                allCombination.add(new ArrayList<>(combination));
            }
            int pre=Integer.MAX_VALUE;
            for(int i=0;i<nums.length;i++) {
                if(visited[i]==1||pre==nums[i]) continue;
                pre=nums[i];
                visited[i]=1;
                combination.add(nums[i]);
                backTracking(combination,visited,nums);
                visited[i]=0;
                combination.remove(combination.size()-1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}