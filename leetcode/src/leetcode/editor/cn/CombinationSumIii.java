package leetcode.editor.cn;
//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法 
// 👍 245 👎 0


import java.util.ArrayList;
import java.util.List;

public class CombinationSumIii {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIii().new Solution();
        solution.combinationSum3(3,7);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> combinations;
    public List<List<Integer>> combinationSum3(int k, int n) {
        combinations = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(k>n) return combinations;
        backTracking(1,k,n,list);
        return combinations;
    }

    private void backTracking(int start, int k, int n, List<Integer> list){
        if(k==0 && n==0){
            List<Integer> temp = new ArrayList(list);
            combinations.add(temp);
            return;
        }
        if(k==0 || n<0) return;
        for (int i=start;i<=9;i++){
            list.add(i);
            backTracking(i+1,k-1,n-i,list);
            list.remove(list.size()-1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
