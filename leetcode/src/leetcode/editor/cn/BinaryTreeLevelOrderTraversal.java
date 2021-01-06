package leetcode.editor.cn;
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 732 👎 0


import leetcode.editor.cn.utils.treeNode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        TreeNode root =new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        solution.levelOrder(root);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<List<Integer>> levelList;
    public List<List<Integer>> levelOrder(TreeNode root) {
        levelList =new ArrayList<List<Integer>>();
        bfs(root);
        return levelList;
    }

    private void bfs(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        if(null == root) return;
        q.offer(root);//将起点加入队列

        while(!q.isEmpty()){
            List<Integer> temp =new ArrayList<>();
            int sz= q.size();
            for(int i=0;i<sz;i++) {
                TreeNode node = q.poll();
                temp.add(node.val);
                if (null != node.left) q.offer(node.left);
                if (null != node.right) q.offer(node.right);
            }
            levelList.add(temp);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
