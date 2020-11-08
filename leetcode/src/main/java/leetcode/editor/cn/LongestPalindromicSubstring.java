package leetcode.editor.cn;

//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2864 👎 0

public class LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        String res =solution.longestPalindrome("babad");
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
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
}
//leetcode submit region end(Prohibit modification and deletion)

}