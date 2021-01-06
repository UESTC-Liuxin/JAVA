package leetcode.editor.cn;
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 464 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> combinations;
    public List<List<Integer>> combine(int n, int k) {
        combinations = new ArrayList<>();
        if(n<k) return null;
        List<Integer> list = new ArrayList<>();
        backTracking(1,k,n,list);
        return combinations;
    }

    /**
     *
     * @param start
     * @param k
     * @param list
     */
    private void backTracking(int start,int k,int n,List<Integer> list){
        if(k==0){
            List<Integer> temp = new ArrayList<>(list);
            combinations.add(temp);
            return;
        }
        for(int i=start;i<=n-k+1;i++){
            list.add(i);
            backTracking(i+1,k-1,n,list);
            list.remove(list.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
