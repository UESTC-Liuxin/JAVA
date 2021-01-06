package leetcode.editor.cn;
//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
//versal/ 
// Related Topics 树 广度优先搜索 
// 👍 64 👎 0


import leetcode.editor.cn.utils.treeNode.TreeNode;

import java.util.*;

public class CongShangDaoXiaDaYinErChaShuIiLcof {
    public static void main(String[] args) {
        Solution solution = new CongShangDaoXiaDaYinErChaShuIiLcof().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)


class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        ArrayList<Integer> array=new ArrayList<>();
        List<List<Integer>> results=new ArrayList<>();
        boolean isPositiveOrder=true;
        if(root==null)
            return  results;
        queue.offer(root);
        while(!queue.isEmpty()){
            int sz=queue.size();
            List<Integer> temp=new ArrayList<>();
            for (int i=0;i<sz;i++){
                TreeNode node =queue.poll();
                temp.add(node.val);
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            if(!isPositiveOrder){
                Collections.reverse(temp);
            }
            results.add(temp);
            isPositiveOrder=!isPositiveOrder;
        }
        return results;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
