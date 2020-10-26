import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Palindromes {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int N=in.nextInt();
        Solution solution =new Solution();
        in.nextLine();
        for(int i=0;i<N;i++){
            String inputs=in.nextLine();
            solution.solve(inputs);
        }


    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 特判
        int len = s.length();
        if (len<1){
            return 0;
        }
        if (len < 2) {
            return len;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return maxLen;


    }
    public int solve(String inputs){
        int left=0;
        int right=0;
        int max=0;
        for(int i=0;i<inputs.length();i++){//必须至少留一个
            left=this.lengthOfLongestSubstring(inputs.substring(0,i+1));
            right=this.lengthOfLongestSubstring(inputs.substring(i+1,inputs.length()));
//            System.out.println("left:"+left);
//            System.out.println("right:"+right);
            if(left!=0&&right!=0) {
                max = Math.max(max, left + right);
            }

        }
        System.out.println(max);
        return 0;
    }
    public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


}
