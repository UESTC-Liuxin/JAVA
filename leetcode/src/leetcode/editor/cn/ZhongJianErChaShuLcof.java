package leetcode.editor.cn;
//输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 递归 
// 👍 253 👎 0


import java.util.HashMap;
import java.util.Map;

public class ZhongJianErChaShuLcof {
    public static void main(String[] args) {
        Solution solution = new ZhongJianErChaShuLcof().new Solution();

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */

public class TreeNode {
    int val;
   TreeNode left;
   TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    //建立一个hash表，储存中序遍历序列与前序遍历序列的对应
    //key=前序遍历数字 ，value=此数字在中序遍历中的index
    Map<Integer,Integer> map =new HashMap<>();

    int[] preorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder=preorder;
        for(int i=0;i<preorder.length;i++){
            map.put(inorder[i],i);//记录每个数字在中序列表的索引，方便以根节点的值找到其中序的位置
        }
        return recur(0,0,inorder.length-1);

    }

    public TreeNode recur(int root,int left,int right){
        //说明已经没有了子节点
        if(left>right)
            return null;
        TreeNode node=new TreeNode(preorder[root]);
        //取根结点在中序遍历中的位置
        int i=map.get(preorder[root]);
        node.left=recur(root+1,left,i-1);
        node.right=recur(i-left+root+1,i+1,right);
        return node;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
