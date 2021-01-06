package leetcode.editor.cn;

//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 题目数据保证答案肯定是一个 32 位的整数。 
//
// 
//
// 示例 1： 
//
// 输入："12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 输入："226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 输入：s = "0"
//输出：0
// 
//
// 示例 4： 
//
// 输入：s = "1"
//输出：1
// 
//
// 示例 5： 
//
// 输入：s = "2"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可以包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 534 👎 0

public class DecodeWays{
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
        System.out.println(solution.numDecodings("2101"));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        int[] dp= new int[s.length()];
        char[] chars=s.toCharArray();
        //首字母为0直接错误返回
        if(chars[0]=='0') return 0;
        if(chars.length<2) return 1;
        dp[0]=1;

        if(chars[1]=='0' && chars[0]<='2') dp[1]=1;
        else if(chars[1]=='0' && chars[0]>'2') return 0;
        else if(chars[0]=='1') dp[1]=2;
        else if(chars[0]=='2' && chars[1]>='1' && chars[1]<='6')
            dp[1]=2;
        else dp[1] = 1;

        for(int i=2;i<chars.length;i++){
            if(chars[i]=='0') {
                if (chars[i - 1] == '1' || chars[i - 1] == '2')
                    dp[i] = dp[i - 2];
                else return 0;
            }
            else if(chars[i-1]=='1') dp[i]=dp[i-1]+dp[i-2];
            else if(chars[i-1]=='2' && chars[i]>='1'&& chars[i]<='6')
                dp[i]=dp[i-1]+dp[i-2];
            else
                dp[i]=dp[i-1];
        }
        return dp[s.length()-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}