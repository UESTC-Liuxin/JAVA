package leetcode.editor.cn;

//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？ 
//
// 示例: 
//
//输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3 
// Related Topics 树 动态规划 
// 👍 855 👎 0

public class UniqueBinarySearchTrees{
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();
        System.out.println(solution.numTrees(3));
    }


/*
* 这是典型的二叉树问题，
* 树的问题一般会想到递归，想到递归就应该想到动态规划
* 搜索树的条件：任意一个结点，左子树的值一定小于节点值，右子树的值一定大于节点
* 因此对于一个节点值i，左子树的取值范围[1~i-1],右子树[i+1,n]
* 同时，取值范围为[1,k]的子树个数(长度为k)与[k+1,2k](长度为k)的子树的个数是一样的
* 因此，可以用一个长度为n的数组G[i]储存长度为i的子树的个数。
* 同时,对于数的根节点来说，G[n]的个数可由F[i,n](以i=1,...,n为根，n为长度的子树个数相加得到)
* 数分为左子树和右子树，G[n]=F[1,n]+F[i,n]+...F[n]
* F[i,n]=G[i-1]*G[n-i]
*
* */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public int numTrees(int n) {
        int[] G=new int[n+1];//为什么要n+1个，因为n+1个的最大索引才是n
        return countG(n,G);
    }
    public int countG(int n,int[] G){
        if(n<2) return 1;
        if(G[n]!=0)
            return G[n];
        for(int i=1;i<=n;i++){
            G[n]+=countG(i-1,G)*countG(n-i,G);
        }
        return G[n];
    }



}
//leetcode submit region end(Prohibit modification and deletion)

}