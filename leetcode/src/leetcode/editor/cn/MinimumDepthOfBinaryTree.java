package leetcode.editor.cn;
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 398 👎 0

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

//    Definition for a binary tree node.
class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode() {}
   TreeNode(int val) { this.val = val; }
   TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
   }
}
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=1; //记录第一层

        while (!queue.isEmpty()){
            int sz= queue.size();//计算深度相同的节点的个数
            for(int i=0;i<sz;i++){
                //取出队列最前一个节点，取完深度相同的节点
                TreeNode cur = queue.poll();
                if(cur.left==null && cur.right==null)//到达了终点
                    return depth;
                if(cur.left!=null)
                    queue.offer(cur.left);
                if(cur.right!=null)
                    queue.offer(cur.right);
            }
            //这一层找完了，都没有到target，要找下一层
            depth++;
        }
        return depth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
