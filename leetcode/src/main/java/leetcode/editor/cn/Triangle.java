package leetcode.editor.cn;

//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划 
// 👍 633 👎 0

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Triangle{
    public static void main(String[] args) {
        Solution solution = new Triangle().new Solution();
        List<List<Integer>> triangle=new LinkedList<List<Integer>>();
        int[] array = {2};
        List<Integer> list1 = Arrays.stream(array).boxed().collect(Collectors.toList());
        triangle.add(list1);
        int[] array1 = {3,4};
        List<Integer> list2 = Arrays.stream(array1).boxed().collect(Collectors.toList());
        triangle.add(list2);
        int[] array2 = {4,1,8};
        List<Integer> list3 = Arrays.stream(array2).boxed().collect(Collectors.toList());
        triangle.add(list3);
        solution.minimumTotal(triangle);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int[] dp=new int[maxH+1];
        for(int i=maxH-1;i>=0;i--)
            for(int j=0;j<=i;j++){
                dp[j]=Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        System.out.println(dp[0]);
        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}