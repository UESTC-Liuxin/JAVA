
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 223 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensIi {
    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();
    }
   
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int sum=0;
    public int totalNQueens(int n) {
        List<Integer> visited=new ArrayList<>();
        backTracking(visited,0,n);
        return sum;
    }

     private void backTracking(List<Integer> visited,int depth,int n){
         if(depth==n){
             //到了最低点，开始组装结果
             sum+=1;
             return;
         }
         for(int i=0;i<n;i++){
             if(depth!=0){
                 int j=0;
                 for(j=0;j<depth;j++){
                     if(visited.get(j)==i)
                         break;
                     if(Math.abs(visited.get(j)-i)==depth-j)
                         break;
                 }
                 if(j<depth) continue;
             }
             //
             visited.add(i);
             backTracking(visited,depth+1,n);
             visited.remove(visited.size()-1);
         }
     }
}
//leetcode submit region end(Prohibit modification and deletion)

}