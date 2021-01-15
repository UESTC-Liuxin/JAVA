
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
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
// 👍 720 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        solution.solveNQueens(5);
    }
   
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<String>> combinations;

    public List<List<String>> solveNQueens(int n) {
        combinations=new ArrayList<>();
        List<Integer> visited=new ArrayList<>();
        backTracking(visited,0,n);
        System.out.println(combinations.size());
        return combinations;
    }

    private void backTracking(List<Integer> visited,int depth,int n){
        if(depth==n){
            //到了最低点，开始组装结果
            List<String> temp=new ArrayList<>();
            for(int i=0;i<n;i++){
                char[] chars=new char[n];
                Arrays.fill(chars,'.');
                chars[visited.get(i)]='Q';
                temp.add(new String(chars));
            }
            combinations.add(temp);
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
