
//n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。 
//
// 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。 
//
// 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。 
//
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//输出：5
//解释：一种移除 5 块石头的方法如下所示：
//1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
//2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
//3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
//4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
//5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
//石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。 
//
// 示例 2： 
//
// 
//输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//输出：3
//解释：一种移除 3 块石头的方法如下所示：
//1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
//2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
//3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
//石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。 
//
// 示例 3： 
//
// 
//输入：stones = [[0,0]]
//输出：0
//解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 1000 
// 0 <= xi, yi <= 104 
// 不会有两块石头放在同一个坐标点上 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 176 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        Solution solution = new MostStonesRemovedWithSameRowOrColumn().new Solution();
    }
   
 //leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    int[] parents;
    int[] rank;
    public int removeStones(int[][] stones) {
        parents=new int[20005];
        rank = new int[20005];


        //初始化并查集
        for(int[] stone:stones){
            parents[stone[0]]=stone[0];
            rank[stone[0]]=1;
            parents[stone[1]+10001]=stone[1]+10001;
            rank[stone[1]+10001]=1;
        }

        //先把所有的横纵作标合并
        for(int[] stone:stones){
            merge(stone[0],stone[1]+10001);
        }

        Set<Integer> set=new HashSet<>();
        for(int[] stone:stones){
            set.add(find(stone[0]));
            set.add(find(stone[1]+10001));
        }
        return stones.length-set.size();
    }

    //在查找过程中，顺带把路径上的点的父节点也设置为根节点
    int find(int x){
        if(parents[x]==x)
            return x;
        else{
            parents[x]=find(parents[x]);//时每一个节点都指向父节点
            return parents[x];
        }
    }

    //合并，直接父节点进行合并
    void merge(int i,int j){
        //先找到两个节点的父节点
        int x=find(i);
        int y=find(j);

        //先判断是否是同一个根节点，如果是就不用合并
        if(x==y)
            return;
        //将秩小的树合并到秩更大的树
        if(rank[x]>=rank[y]){
            parents[y]=x;
        }
        else{
            parents[x]=y;
        }
        //如果两个的秩相同，合并后，x的秩会加一
        if(rank[x]==rank[y]){
            rank[x]++;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}