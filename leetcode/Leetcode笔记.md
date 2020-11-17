# Leetcode笔记

## 数据结构

### 树

#### 二叉树

先介绍二叉树的集中遍历方式：

![](https://i.loli.net/2020/11/16/5Syan7tOmf4LU9F.png)

- 前序遍历顺序是：根节点、左子树、右子树，每个子树的遍历顺序同样满足前序遍历顺序：6,2,0,4,3,5,8,7,9。
- 中序遍历顺序是：左子树、根节点、右子树，每个子树的遍历顺序同样满足中序遍历顺序: 0,2,3,4,5,6,7,8,9.
- 后序遍历顺序是:  左子树、右子树、根节点，每个子树的遍历顺序同样满足后序遍历顺序: 0,3,5,4,2,7,9,8,6
- 宽度优先遍历顺序是：第一层从左到右，第二层从左到右...：６,2,8,0,4,7,9,null,null,3,5,null,null...

[剑指 Offer 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

这道题的关键就是理清楚，每种遍历方式以及他们之间的联系。

对于前序遍历来说：第一个点一定是根节点，而左子树和右子树就暂时无法确定。

而对于中序遍历来说，根节点的左边一定是左子树（如果有），右边一定是右子树。比如对于上面的例子而言，中序遍历顺序是0,2,3,4,5,6,7,8,9。在中序序列中，６的左边的数字都是左子树，６的右边都是右子树。

根据前序和中序，可以依次确定根节点->左子树->右子树。当然，这只是最简单的情况，对于根节点来说，我们很容易确定左子树和右子树的左右边界。

当我们确定了根节点和左右子树之后，就可以在前序当中，寻找左子树当中最靠近根节点的点为左子树的顶点，比如：有上述知0,2,3,4,5是左子树，这些数字在前序中，2是最靠近根节点的点，所以2就是左子树的顶点，在中序中寻找2，2的左边一定是左子树，2的右边到根节点6之前的节点都是右子树，其中6在中序的位置就是左子树的右边界。同理，在右子树7,8,9中，8又是最靠近根节点的，所以8是右子树的顶点。。。。按照此思路可以找出所有的点的位置。

在这次的遍历中，我们以数组边界和根节点来确定了左右子树的左右边界，所以在用递归思想的时候一定要确定每棵子树的边界。

**分治算法解析：**

递推参数： 根节点在前序遍历的索引 root 、子树在中序遍历的左边界 left 、子树在中序遍历的右边界 right ；

终止条件： 当 left > right ，代表已经越过叶节点，此时返回 nullnull ；

递推工作：

1. 建立根节点 node ： 节点值为 preorder[root] ；

2. 划分左右子树： 查找根节点在中序遍历 inorder 中的索引 i ；

3. 构建左右子树： 开启左右子树递归；

   为了提升效率，本文使用哈希表 dic 存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1)

|        | 根节点索引（这里的数字代表前序节点的索引） | 中序遍历左边界（中序索引） | 中序遍历右边界（中序索引） |
| ------ | ------------------------------------------ | -------------------------- | -------------------------- |
| 左子树 | root+1                                     | left(当前最左边的left)     | i-1                        |
| 右子树 | i-left+root+1                              | i+1                        | right                      |

`i - left + root + 1含义为 根节点索引 + 左子树长度 + 1`

返回值： 回溯返回 node ，作为上一层递归中根节点的左 / 右子节点；

```java
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
```



#### 二叉搜索树

二叉搜索树：二叉搜索树的特点就是 `左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点`

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)S

 **[235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)**

这题让求二叉搜索树的最近公共祖先，而二叉搜索树的特点就是左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点，所以这题就好办了，从更节点开始遍历：

- 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找

- 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找

- 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点。

- 在第三种情况中，需要包含一种情况，就是两个节点本身就是根与叶之间的关系，这种情况，`(root.val-p.val)*(root.val-q.val)=0`。

  ```java
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          TreeNode cur=root;
  
          while(cur!=null){
              if((p.val-cur.val)*(q.val-cur.val)<=0){
                  break;
              }
              if((p.val>cur.val)&&(q.val>cur.val))
                  cur=cur.right;
              else
                  cur=cur.left;
          }
          return cur;
  }
  ```

[96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)  

这是典型的二叉树问题，树的问题一般会想到递归，想到递归就应该想到动态规划。

 搜索树的条件：任意一个结点，左子树的值一定小于节点值，右子树的值一定大于节点，因此对于一个节点值i，左子树的取值范围[1~i-1],右子树[i+1,n]
  同时，取值范围为[1,k]的子树个数(长度为k)与\[k+1,2k\](长度为k)的子树的个数是一样的。 因此，可以用一个长度为n的数组G[i]储存长度为i的子树的个数。 同时,对于数的根节点来说，G[n]的个数可由F\[i,n\](以i=1,...,n为根，n为长度的子树个数相加得到)  数分为左子树和右子树，
$$
G[n]=F[1,n]+F[i,n]+...F[n] \\
F[i,n]=G[i-1]*G[n-i]
$$
直接递归

```java
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
```




## 回溯算法





## 动态规划

- 划分为子问题

- 子问题是总问题的一个模板，或者说最简单的总问题就是子问题

- 确定状态，也就是DP应该存什么东西

- 确定状态转移方程

- 确定DP的填充思路

首先，动态规划问题的一般形式就是求最值。动态规划其实是运筹学的一种最优化方法，只不过在计算机问题上应用比较多，比如说让你求最长递增子序列呀，最小编辑距离呀等等。

既然是要求最值，核心问题是什么呢？求解动态规划的核心问题是穷举。因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值呗。
动态规划这么简单，就是穷举就完事了？我看到的动态规划问题都很难啊！

首先，动态规划的穷举有点特别，因为这类问题存在「重叠子问题」，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。而且，动态规划问题一定会具备「最优子结构」，才能通过子问题的最值得到原问题的最值。

另外，虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出正确的「状态转移方程」才能正确地穷举。

以上提到的**重叠子问题、最优子结构、状态转移方程**就是动态规划三要素。具体什么意思等会会举例详解，但是在实际的算法问题中，写出状态转移方程是最困难的，这也就是为什么很多朋友觉得动态规划问题困难的原因，我来提供我研究出来的一个思维框架，辅助你思考状态转移方程：
明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义。

### 一维动态规划

[53.最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

拿到这道题，看到关键字，“连续”，一般的思路就是双指针，滑窗，动态规划。先看能不能进行子问题的划分，利用动态规划做，这里不固定的因素两个，一个是位置，一个是长度，那么通常是需要二维动态规划或者在需要确定某一个因素去做动态规划。

从最基本的情况，[-1],确定此序列的最大的连续子序列，那么只有-1,这是最简单的情况；然后看 [-1,2]，就是选出-1，2，（-1，2）最大的，如果按照迭代的方式分解子问题，就是，以-1，2为迭代点，选择一个最大的，以2为迭代点的时候，就是看之前得到的结果，-1对2为迭代点的自序列的作用如何，如果存在负作用，那么就不要，如果是正作用，那就要。最后求所有迭代中最大的。这就是确定位置改变长度的做法。

$dp[i]$表示以i为序列末尾的最大连续子序和。

$dp[i]=s[i]+max(dp[i-1],0)$

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int sum=nums[0];
        int max=sum;
        for(int i=1;i< nums.length;i++){
            sum=sum>0?sum+nums[i]:nums[i];
            max=Math.max(sum,max);
        }
        return max;
    }
}
```

 [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

这道题很明显可以用动态规划来做，分解子问题，最简单情况，[[1]]，最小路径和1，[[1],[2,3]]这个时候的最小路径和就是，看上一层的最小的路径和加上本层的值的最小值，就是这一层的最小路径和，注意边界条件，i=j=0,j=0,i==j 时候的情况。

总的状态转移方程：$dp[i][j]=s[i][j]+min(dp[i-1][j],dp[i-1][j-1])$

代码：

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int min=Integer.MAX_VALUE;
        int[][] dp=new int[maxH][maxH];
        for(int i=0;i<maxH;i++){
            for(int j=0;j<=i;j++){
                if(i==0 && j==0){
                    dp[i][j]=triangle.get(i).get(j);
                }
                else if(j==0){
                    dp[i][j]=triangle.get(i).get(j)+dp[i-1][j];
                }
                else if(i==j && i!=0){
                    dp[i][j]=triangle.get(i).get(j)+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=triangle.get(i).get(j)+
                            Math.min(dp[i-1][j],dp[i-1][j-1]);
                }
                if(i==maxH-1){
                    min=Math.min(min,dp[i][j]);
                }
            }

        }
        return min;
    }
}
```

乍一眼，通过了，逻辑上也没什么问题，但是但是但是。。。。我们其实可以发现，这个二维的dp压根就没有用完，只用了一半，因为定义了一个二维数组，但是第i行只有i个值有用。其余倒三角的空间全部浪费。

因此可以尝试一下进行空间的优化：尝试从底层往上面计算。==技巧：可以定义dp的时候多定义一维而减少对边界的判断。==

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int min=Integer.MAX_VALUE;
        int[][] dp=new int[maxH+1][maxH+1];
        for(int i=maxH-1;i>=0;i--)
            for(int j=0;j<=i;j++){
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        return dp[0][0];
    }
}
```

按照这个思路，其实可以发现，每次计算了一次的dp的时候，上层有个数据就会被抛弃掉了，可以利用这个数据的位置来存新的最小路径和$dp[i][j]$。那么只需要存下dp[j]就可以了。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int[] dp=new int[maxH+1];
        for(int i=maxH-1;i>=0;i--)
            for(int j=0;j<=i;j++){
                dp[j]=Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        System.out.println(dp[0]);
        return dp[0];
    }
}
```

[300.最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/)

这道题跟最大子序和有点相似，都是寻找以每个点为结尾的最长上升子序列。

对于$dp[i]$来说，要求得它的最长上升子序列的长度，要找到满足$nums[i]>nums[j],j=0,...,i-1$中$dp[j]$最大的，$dp[i]$等于$dp[j]+1$。

代码：

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int length=nums.length;
        int[] dp =new int[length];
        int result=0;
        for(int i=0;i<length;i++) {
            int max=0;
            for (int j = 0; j <=i; j++) {
                if (nums[j] <nums[i]) {zhiqian
                    max = Math.max(max, dp[j]);
                    dp[i]=max;
                }
            }
            dp[i]+=1;
            result = Math.max(result,dp[i]);
        }
        System.out.println(result);
        return result;
    }
}
```

[198.打家劫舍动态规划](https://leetcode-cn.com/problems/house-robber/)

这道最开始的想法是用一个二叉树进行遍历求最大，然后用DP存储。

这道题的最关键的问题是：选择一个房间偷不偷，要看前一个房间偷没有，前一个房间没偷这个房间才能偷。

问题分解，对于n>2个房间时，到第k间房时，有两个选项：

- 偷窃第 k间房屋，那么就不能偷窃第k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 k 间房屋的金额之和。

- 不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。

利用dp[i]存储第i间房时，能偷到的最大价格：$dp[i]=max(dp[i−2]+nums[i],dp[i−1])$

代码：

```java
class Solution {
    public int rob(int[] nums) {
        int length=nums.length;
        int dp[]=new int[length];
        if(length==0){
            return 0;
        }
        else if (length==1){
            return nums[0];
        }
        else if(length==2){
            return Math.max(nums[0],nums[1]);
        }
        dp[0]=nums[0];
        dp[1]=Math.max(dp[0],nums[1]);
        for(int i=2;i<length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];

    }

}
```

[322.零钱兑换](https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/)

更加详细的讲解;https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/bfs-kuang-jia

拿到这个问题。我最先想到的是，像切钢条问题那样，对总额从0开始到二分之一总额，进行左右切分，并且在寻找的过程中，随时查看当前的总额是否能够用一个硬币来表示。

DP[i]表示，总额为i时，最少的硬币数，其中，DP[i]可能为0

状态转移方程为：
$$
DP[s]=min(DP[i]+DP[s-i]),if(DP[i]!=-1  \&\& DP[s-i]!=-1)
$$
代码挺复杂，最终结果不对，不知道为啥。。。。。

看了题解，新的思路：对于总额s来说，寻找一个面值为$c_i $的硬币，$DP[s-c_i]$最小，那么$DP[s]=DP[s-c_i ] +1$，当然，如果没有找到，那就利用递归，再往下寻找，如果最后都没找到，那就证明无法组合。

```java
public class CoinChange {
    public static void main(String[] args) {

        Solution solution = new CoinChange().new Solution();
        System.out.println(solution.coinChange(new int[]{1,2,5},3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount < 1) {
                return 0;
            }
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) {
                return -1;
            }
            if (rem == 0) {
                return 0;
            }
            if (count[rem - 1] != 0) {
                return count[rem - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min) {
                    min = 1 + res;
                }
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }

}
//leetcode submit region end(Prohibit modification and deletion)

}
```



### 二维动态规划

[5.最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

回文子串有一个非常简单的状态转移方程，当前子串是否是回文子串，首先要看去掉首末字符后是否是回文，然后看首末字符是否相等，那么就对每个子串是否是回文进行记录，$dp[i][j]$表示从i到j的子串是否是回文。

$dp[i][j]=dp[i+1][j-1]$&&$s[i]==s[j]$

用备忘录和递归的方法当然很简单，但是利用迭代的方式，要进行一个合理的遍历就是一个需要思考的问题：

这里由于状态转移的条件是一个字符串长度的收缩，应该从短长度扩展到长度，因此利用子串长度进行最外层循环，然后利用子串起始点做为第二层循环：

```java
class Solution {
    public String longestPalindrome(String s) {
        int length=s.length();
        String longestString="";
        //记录从i到j的子串是否是回文子串
        boolean[][] dp =new boolean[length][length];
        if(length<2)//如果长度小于2，直接返回原字符串
            return s;
        for(int l=0;l<length;l++)
            for(int i=0;i<length-l;i++){
                int j=i+l;
                if(l==0){
                    dp[i][j]=true;
                }
                else if(l==1){
                    dp[i][j]=(s.charAt(i)==s.charAt(j));
                }
                else{
                    dp[i][j]=(s.charAt(i)==s.charAt(j)) && dp[i+1][j-1];
                }
                if(dp[i][j] && i+j+1>longestString.length()){
                    longestString=s.substring(i,j+1);
                }
            }
        return longestString;
    }
```

时间复杂度为$n^2$

64. [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

这道题，从明面上看就是一个递归搜索问题，那么基本可以利用动态规划问题了，同时，由于是二维矩阵，最优值也需要用二维矩阵来记录，所以就想到二维动态规划问题。

对于(0,0)到(m,n)，需要考虑向下和向右的方向，按照递归的思路，需要比较向下或者向右的两个方向的最小dp值，那么利用迭代，就应该从最小的dp值的(m,n)向起点(0,0)生长。

转换公式：
$$
dp[i][j]=grid[i][j]+min(dp[i][j+1],dp[i+1][j]),i+1,j+1不得越界，越界时，证明到了最右边或者底端，这时候只有一个方向可以走。
$$
代码：
```java
public class MinimumPathSum{
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        int[][] grid=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid));

    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {

        int m=grid.length;//记录高
        int n=grid[0].length;//记录宽
        int[][] dp =new int[m][n];
        for(int i=m-1;i>=0;i--)
            for(int j=n-1;j>=0;j--) {
                if (j >= n - 1 && i >= m - 1) {
                    dp[i][j] = grid[i][j];
                } else if (j >= n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i >= m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        return dp[0][0];
    }
}
```

## BFS

问题的本质就是让你在一幅「图」中找到从起点start到终点target的最近距离。

[BFS](https://blog.csdn.net/qq_37482202/article/details/89513877)叫做广度优先搜索，DFS叫做深度优先搜索。

BFS按照横向关系进行搜索，DFS按照纵向关系搜索，具体来说，就是一棵树，BFS从头节点开始，按照节点的级别进行搜索，比如头结点下面连接了两个子节点，那么把头节点搜索完了之后，再看第一个子节点和第二个子节点，不管这两个子节点是否还有子节点。DFS就是把一条脉络关系全部走通，再走另一条脉络。

BFS的关键就是要保证级相同的节点是在一起的，且离头节点最近的节点应该放在最前面。因此，BFS常常与队列一起使用，因为队列就是先进先出，永远在最后添加。==利用BFS有个好处就是，根本不用遍历所有的节点，就能找到最短的。==举个例子：从家走到医院有10条公交路线可以到，找换乘次数最小的方案，根据换乘一次，换乘二次...的方案找，看换乘一次，这10条线路分别到了什么地方，如果到了医院就结束，没有到，就看换乘两次能不能到医院。最后，有一个线路，换乘两次就到了，其余换乘3次的情况就不用搜索了。

BFS与DFS相比，就是空间换时间，要存下走过的路和将要走的路。

这里给出利用队列进行BFS进行搜索的模板代码：

```JAVA
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路

    q.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数

    while (q not empty) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            /* 划重点：这里判断是否到达终点 */
            if (cur is target)
                return step;
            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj())
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
        }
        /* 划重点：更新步数在这里 */
        step++;
    }
}
```

**[111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)**

首先明确一下起点 `start` 和终点 `target` 是什么，怎么判断到达了终点？

**显然起点就是** **`root`** **根节点，终点就是最靠近根节点的那个「叶子节点」嘛**，叶子节点就是两个子节点都是 `null` 的节点。

具体思路也是一层一层的搜索，如果发现有一个节点是target，也就是有一个节点是叶节点，那证明当前深度就是最小深度。

```java
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
```

 **[752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)**

这道题，一拿到感觉很复杂的情况，有四个转盘，每个转盘转一次有2种情况。但实际上，我们只需要分析转盘拨动一次会产生的情况，比如对于0000来说，转动一次，只能产生8种情况：1000,9000,0100,0900,0010,00900,0001,0009。

如果不考虑死亡数字的情况，直接套用模板：

```java
//向上拨动
public String upDila(String s,int i){
    char[] chars = s.toCharArray();
    if(chars[i]=='9')
        chars[i]='0';
    else
        //这里不用转成数字再加一，因为ascii码加一是一样的
        chars[i]+=1;
    return new String(chars);
}

//向下拨动
public String downDila(String s,int i){
    char[] chars = s.toCharArray();
    if(chars[i]=='0')
        chars[i]='9';
    else
        chars[i]-=1;
    return new String(chars);

}

//先写出BFS框架
public int bfsFramework(String start,String target){
    //利用一个队列来记录将要搜索的元素
    Queue<String> queue=new LinkedList<String>();
    //利用一个集合来记录已经搜索的元素
    Set<String>   visited  =new HashSet<String>();

    //将起点加入队列
    queue.offer(start);
    visited.add(start);

    int step=0;

    while(!queue.isEmpty()){
        int sz = queue.size();
        //扩散节点
        for(int i=0;i<sz;i++){
            String s=queue.poll();
           // visited.add(s);不能在这儿加，必须要在加queue的时候加，因为在这儿加，queue中有会有重复的
            //判断是否到终点
            if(s.equals(target))
                return step;
            for(int j=0;j<4;j++){
                String up=upDila(s,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);  
                    }
                    String down=downDila(s,j);
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
            }
        }
        step++;
    }//end while
    return -1;
}
```

上述代码，基本完成了寻找0000到target的最短距离的寻找。现在加入死亡数字，死亡数字就是搜索到的这个数字的时候，这个数字被放弃。不去看它的有下一步转移。

```java
    public int openLock(String[] deadends, String target) {

        //利用一个队列来记录将要搜索的元素
        Queue<String> queue=new LinkedList<String>();
        //利用一个集合来记录已经搜索的元素
        Set<String>   visited  =new HashSet<String>();

        Set<String>  deadendsSet =new HashSet<String>(Arrays.asList(deadends));
        //
        //将起点加入队列
        queue.offer("0000");
        visited.add("0000");

        int step=0;

        while(!queue.isEmpty()){
            int sz = queue.size();
            //扩散节点
            for(int i=0;i<sz;i++){
                String s=queue.poll();
                //判断是否在死亡数字
                if(deadendsSet.contains(s))
                    continue;
                //判断是否到终点
                if(s.equals(target))
                    return step;
                for(int j=0;j<4;j++){
                    String up=upDila(s,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down=downDila(s,j); 
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }//end while
        return -1;
    }
```



## 贪心算法

## KMP



## 字符串专题

### 回文子串问题

## 图



# 附录（刷题时间表）

| 题号-题目                                                    | 首刷时间        | 最后刷时间 | Tag        | 次数 |
| :----------------------------------------------------------- | :-------------- | :--------- | ---------- | :--- |
| [5.最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | 2020/10/1之前   | 2020/11/3  | 动态规划   | 2    |
| [53.最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | 2020/10/1之前   | 2020/11/3  | 动态规划   | 2    |
| [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/) | 2020/11/4       | 2020/11/4  | 动态规划   | 1    |
| [198.打家劫舍](https://leetcode-cn.com/problems/house-robber/) | 2020/11/8       | 2020/11/8  | 动态规划   | 1    |
| [322.零钱兑换](https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/) | 2020/11/10      | 2020/11/10 | 动态规划   | 1    |
| [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | 2020/11/11      | 2020/11/11 | BFS        | 1    |
| [752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/) | 2020/11/12      | 2020/11/12 | BFS        | 1    |
| [235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | 2020/11/12      | 2020/11/12 | 二叉搜索树 | 1    |
| [17.电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | 2020/10/1之前   | 2020/11/13 | BFS        | 2    |
| [402. 移掉K位数字](https://leetcode-cn.com/problems/remove-k-digits/) | 2020/10/15      | 2020/10/15 | BFS        | 1    |
| [剑指 Offer 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/) | 2020/11/17      | 2020/11/17 | 树         | 1    |
| [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/) | 2020/10//17之前 | 2020/11/17 | 二叉搜索树 | 1    |

