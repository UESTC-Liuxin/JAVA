package leetcode.editor.cn;
//给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 936 👎 0


import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> allSets;
    public List<List<Integer>> subsets(int[] nums) {
        allSets= new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
        //先添加一个空子集，任何一个集合的子集都有空集
        allSets.add(subSet);
        if(nums.length<1) return allSets;
        backTracking(subSet,0,nums);
        return allSets;
    }

    private void backTracking(List<Integer> subSet,int startIndex,int[] nums){
        if(startIndex>=nums.length) return;
        for(int i=startIndex;i<nums.length;i++){
            subSet.add(nums[i]);
            //添加每一个结点
            allSets.add(new ArrayList<>(subSet));
            backTracking(subSet,i+1,nums);
            subSet.remove(subSet.size()-1);
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
