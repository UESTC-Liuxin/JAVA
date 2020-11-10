package leetcode.editor.cn;

//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 705 👎 0

public class MinimumPathSum{
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        int[][] grid=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid));

    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {

        int m=grid.length;//记录高
        int n=grid[0].length;//记录宽
        int[][] dp =new int[m][n];
        for(int i=m-1;i>=0;i--)
            for(int j=n-1;j>=0;j--) {
                if (j >= n - 1 && i >= m - 1) {
                    dp[i][j] = grid[i][j];
                } else if (j >= n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i >= m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        return dp[0][0];
    }

//
//    public
}
//leetcode submit region end(Prohibit modification and deletion)

}