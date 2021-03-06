//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
//
// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示
//val 的绝对值。
//
// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
//
//
//
// 示例 1：
//
//
//
//
//输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//输出：20
//解释：
//
//我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
//注意到任意两个点之间只有唯一一条路径互相到达。
//
//
// 示例 2：
//
//
//输入：points = [[3,12],[-2,5],[-4,1]]
//输出：18
//
//
// 示例 3：
//
//
//输入：points = [[0,0],[1,1],[1,0],[-1,1]]
//输出：4
//
//
// 示例 4：
//
//
//输入：points = [[-1000000,-1000000],[1000000,1000000]]
//输出：4000000
//
//
// 示例 5：
//
//
//输入：points = [[0,0]]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= points.length <= 1000
// -106 <= xi, yi <= 106
// 所有点 (xi, yi) 两两不同。
//
// Related Topics 并查集
// 👍 139 👎 0

package leetcode.editor.cn;

import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {
    public static void main(String[] args) {
        Solution solution = new MinCostToConnectAllPoints().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Edge implements Comparable<Edge> {
            int weight;
            int start;
            int end;

            public Edge(int weight, int start, int end) {
                this.weight = weight;
                this.start = start;
                this.end = end;
            }

            @Override
            public int compareTo(Edge o) {
                return this.weight - o.weight;
            }
        }

        class DSU {
            int[] parents;
            int[] rank;

            public DSU(int length) {
                parents = new int[length];
                rank = new int[length];
                for (int i = 0; i < length; i++) {
                    parents[i] = i;
                }
            }

            private int find(int i) {
                if (parents[i] == i)
                    return i;
                return find(parents[i]);
            }

            public void merge(int i, int j) {
                int parentA = find(i);
                int parentB = find(j);
                if (parentA == parentB)
                    return;
                if(rank[parentA]>rank[parentB]){
                    parents[parentB] = parentA;
                }
                else {
                    parents[parentA] = parentB;
                }
            }

            public boolean isConnect(int i,int j){
                return find(i)==find(j);
            }
        }

        public int minCostConnectPoints(int[][] points) {
            int length = points.length;
            int sum = 0;
            //添加边
            PriorityQueue<Edge> minHeap = new PriorityQueue<>();
            for (int i = 0; i < length; i++)
                for (int j = i + 1; j < length; j++) {
                    minHeap.add(new Edge(distance(points,i,j), i, j));
                }
            int count = 0;
            DSU dsu = new DSU(length);
            //生成树
            while(count<length-1 && !minHeap.isEmpty()) {
                Edge edge = minHeap.poll();
                if (!dsu.isConnect(edge.start, edge.end)) {
                    dsu.merge(edge.start, edge.end);
                    sum += edge.weight;
                }
            }
            return sum;

        }

        private int distance(int[][] points, int i, int j) {
            return Math.abs(points[i][0] - points[j][0]) +
                    Math.abs(points[i][1] - points[j][1]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
