
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1068 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> allCombination;
    public List<List<Integer>> permute(int[] nums) {
        allCombination = new ArrayList<>();
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
        for(int i=0;i<nums.length;i++) {
            if(visited[i]==1) continue;
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