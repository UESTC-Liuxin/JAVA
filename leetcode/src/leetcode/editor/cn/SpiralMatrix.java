package leetcode.editor.cn;
//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。 
//
// 示例 1: 
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
//输出: [1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2: 
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
// 
// Related Topics 数组 
// 👍 563 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();
        solution.spiralOrder(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        });
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int l = -1, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        List<Integer> list = new ArrayList<>();
        int total = matrix.length * matrix[0].length;
        int num = 0;

        while (num<total) {

            for (int i = l; i <r && num<total; i++) { //从左到右
                if(i==-1)
                    continue;
                list.add(matrix[t][i]);
                num++;
            }
            l++;
            for (int i = t; i <b && num<total; i++) { //从上到下
                list.add(matrix[i][r]);
                num++;
            }
            t++;
            for (int i = r; i > l && num<total; i--) { //从右到左
                list.add(matrix[b][i]);
                num++;
            }
            r--;
            for (int i = b; i >t  && num<total; i--) { //从下到上
                list.add(matrix[i][l]);
                num++;
            }
            b--;
        }
        return list;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
