package leetcode.editor.cn;
//给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。 
//
// 示例: 
//
// 输入: 3
//输出:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//] 
// Related Topics 数组 
// 👍 273 👎 0


public class SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();
        solution.generateMatrix(3);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        /**
         *
         */
        int left=0,right=n-1;//左闭右开
        int top=0,bottom=n-1;//上闭下开
        int[][] mat = new int[n][n];
        int num=1,tar=n*n;
        while(num<=tar){
            //从左往右
            for(int i=left;i<=right;i++) mat[top][i]=num++;
            top++;
            //从上往下
            for(int i=top;i<=bottom;i++) mat[i][right]=num++;
            right--;
            //从右往左
            for (int i=right;i>=left;i--) mat[bottom][i]=num++;
            bottom--;
            //从下到上
            for (int i=bottom;i>=top;i--) mat[i][left]=num++;
            left++;
        }
        return mat;



    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
