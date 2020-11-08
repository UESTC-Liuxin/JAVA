# Leetcode笔记

## 算法与数据结构







## 回溯算法





## 动态规划

- 划分为子问题
- 子问题是总问题的一个模板，或者说最简单的总问题就是子问题
- 确定状态，也就是DP应该存什么东西
- 确定状态转移方程
- 确定DP的填充思路

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
                if (nums[j] <nums[i]) {
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

[198.打家劫舍](https://leetcode-cn.com/problems/house-robber/)

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

## 贪心算法

## KMP



## 字符串专题

### 回文子串问题

## 图



# 附录（刷题时间表）

| 题号-题目                                                    | 首刷时间      | 最后刷时间 | Tag      | 次数 |
| :----------------------------------------------------------- | :------------ | :--------- | -------- | :--- |
| [5.最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | 2020/10/1之前 | 2020/11/3  | 动态规划 | 2    |
| [53.最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | 2020/10/1之前 | 2020/11/3  | 动态规划 | 2    |
| [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/) | 2020/11/4     | 2020/11/4  | 动态规划 | 1    |
| [198.打家劫舍](https://leetcode-cn.com/problems/house-robber/) | 2020/11/8     | 2020/11/8  | 动态规划 | 1    |



