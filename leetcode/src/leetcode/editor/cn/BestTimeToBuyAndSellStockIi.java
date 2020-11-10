package leetcode.editor.cn;

//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 886 👎 0

public class BestTimeToBuyAndSellStockIi{
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();
        int[] a={1,2,3,4,5};
        System.out.println(solution.maxProfit(a));
    }
/*
//leetcode submit region begin(Prohibit modification and deletion)
    //谷底买入，谷峰卖出
    //每天都结算，只要今天比昨天涨了，就买了，如果降了，就当昨天没有买，不计入结果
class Solution {
    public int maxProfit(int[] prices) {
        int length=prices.length;
        int profit=0,preProfit=0;
        for(int i=1;i<length;i++)
        {
            preProfit=prices[i]-prices[i-1];
            profit+=Math.max(0,preProfit);
        }
        return profit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}*/

/*动态规划思路：
    当前的状态分为两个情况，一个是持有现金，一个是持有股票。
    对于持有现金的情况：当前的盈亏情况有两种，一种是昨天晚上的状态就是没得股票，还有种就是昨天有，今天卖了
    所以今天的盈亏情况为：1) dp[i-1][0];2)dp[i-1][1]+prices[i]
    对于持有股票的情况：当前的盈亏有两种，一种是昨天晚上没得，今天买了；另一个相反
    1）dp[i-1][0]-price[i] ;2)dp[i-1][1]
    今天的状况，最大营收就是求最大*/
class Solution {
    public int maxProfit(int[] prices) {
        int dp[][] =new int[prices.length][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for(int i=1;i<prices.length;i++)
        {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
        }
        return dp[prices.length-1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}