package com.interview.yizhun;

import javax.swing.tree.TreeNode;



class DiDi1st {
    public static void main(String[] args) {
        Solution solution = new DiDi1st().new Solution();
        solution.fun(new int[]{3,1,4,2,7});
    }

    class TreeNode{
        public  int val;
        public TreeNode left=null;
        public TreeNode right=null;
        public TreeNode(int val) {
            this.val=val;
        }
    }
    class Solution {
        public TreeNode fun(int[] nums) {
            if(nums.length<1) return null;
            TreeNode head= new TreeNode(nums[0]);
            for(int num:nums)
                insertNode(head,num);
            recur(head);
            return head;
        }

        private TreeNode insertNode(TreeNode node,int val){
            if(node==null){
                TreeNode temp= new TreeNode(val);
                return temp;
            }
            if (val<node.val){
                node.left=insertNode(node.left,val);
                return node;
            }
            else{
                node.right=insertNode(node.right,val);
                return node;
            }
        }

        private void recur(TreeNode node){
            if(node==null) return;
            recur(node.left);
            System.out.println(node.val);
            recur(node.right);
        }
    }

}
