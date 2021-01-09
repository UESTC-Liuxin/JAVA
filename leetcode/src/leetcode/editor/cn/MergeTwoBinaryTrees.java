
//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树 
// 👍 596 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.utils.treeNode.TreeNode;

public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        Solution solution = new MergeTwoBinaryTrees().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            TreeNode dummyNode = new TreeNode(-1);
            if (t1 == null) return t2;
            if (t2 == null) return t1;
            recur(t1, t2);
            return t1;
        }

        //以t1为主，t2为辅
        //进入递归函数的保证是两个节点都不为null
        private void recur(TreeNode t1, TreeNode t2) {
            t1.val += t2.val;
            if (t1.left == null && t2.left != null) {
                t1.left = t2.left;
            }
            else if (t1.left != null && t2.left != null) {
                recur(t1.left, t2.left);
            }

            if (t1.right == null && t2.right != null) {
                t1.right = t2.right;
            }
            else if (t1.right != null && t2.right != null) {
                recur(t1.right, t2.right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}