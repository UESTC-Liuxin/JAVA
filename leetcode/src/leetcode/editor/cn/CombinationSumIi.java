package leetcode.editor.cn;
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 460 👎 0


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        solution.combinationSum2(new int[]{10,1,2,7,6,1,5},8);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] candidates;
    List<List<Integer>> combines;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates=candidates;
        this.combines= new LinkedList<List<Integer>>();
        backTrace(0,0,target,new LinkedList<Integer>());
        return this.combines;
    }

    public void backTrace(int startIndex,int sum,int target,List<Integer> combine){
        if (target<sum) return;
        if (target==sum){
            this.combines.add(new LinkedList<>(combine));
            return;
        }
        int pre=-1;
        for(int i=startIndex;i<this.candidates.length;i++){
            if(pre==this.candidates[i])
                continue;
            pre=this.candidates[i];
            combine.add(pre);
            backTrace(i+1,sum+pre,target,combine);
            combine.remove(combine.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
