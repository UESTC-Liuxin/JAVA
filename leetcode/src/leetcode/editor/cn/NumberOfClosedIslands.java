////有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
////
//// 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
////
//// 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
////
//// 请返回封闭岛屿的数目。
////
////
////
//// 示例 1：
////
////
////
//// 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1
////,0,1],[1,1,1,1,1,1,1,0]]
////输出：2
////解释：
////灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
////
//// 示例 2：
////
////
////
//// 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
////输出：1
////
////
//// 示例 3：
////
//// 输入：grid = [[1,1,1,1,1,1,1],
////             [1,0,0,0,0,0,1],
////             [1,0,1,1,1,0,1],
////             [1,0,1,0,1,0,1],
////             [1,0,1,1,1,0,1],
////             [1,0,0,0,0,0,1],
////             [1,1,1,1,1,1,1]]
////输出：2
////
////
////
////
//// 提示：
////
////
//// 1 <= grid.length, grid[0].length <= 100
//// 0 <= grid[i][j] <=1
////
//// Related Topics 深度优先搜索
//// 👍 75 👎 0
//
//package leetcode.editor.cn;
//
//public class NumberOfClosedIslands {
//    public static void main(String[] args) {
//        Solution solution = new NumberOfClosedIslands().new Solution();
//    }
//
//    //leetcode submit region begin(Prohibit modification and deletion)
//    class Solution {
//        public int closedIsland(int[][] grid) {
//            int N = grid.length;
//            int M = grid[0].length;
//
//            BST bst = new BST(N * M + 1); //多的那个作为边界或者水域
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    //向右向下合并
//                    if()
//                    if (i == 0 || i == N-1 || j == 0 || j == M-1){
//                        count --;  //对于水域和边界点，直接减
//                    }
//                    else if()
//
//                }
//            }
//        }
//
//        class BST {
//            int parents[];
//            int rank[];
//
//            public BST(int N) {
//                parents = new int[N];
//                rank = new int[N];
//                for (int i = 0; i < N; i++) {
//                    parents[i] = i;
//                }
//            }
//
//            public boolean isConnect(int a, int b) {
//                return find(a) == find(b);
//            }
//
//            public int find(int a) {
//                if (parents[a] == a)
//                    return a;
//                else
//                    return find(parents[a]);
//            }
//
//            public boolean merge(int a, int b) {
//                int pa = find(a);
//                int pb = find(b);
//                if (pa == pb)
//                    return false;  //未进行合并操作
//                if (rank[pa] < rank[pb]) {
//                    parents[pb] = pa;
//                } else if (rank[pa] > rank[pb])
//                    parents[pa] = pb;
//                else {
//                    parents[pb] = pa;
//                    rank[pb] += 1;
//                }
//                return true;
//            }
//        }
//
//    }
////leetcode submit region end(Prohibit modification and deletion)
//
//}
