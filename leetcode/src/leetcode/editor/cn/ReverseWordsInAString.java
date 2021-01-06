package leetcode.editor.cn;
//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 说明： 
//
// 
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 示例 1： 
//
// 输入："the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 251 👎 0


import java.util.*;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAString().new Solution();
        solution.reverseWords("the sky is blue");

    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        Stack<String> q=new Stack<>();
        char[] srcChars= s.toCharArray();

        for(int slow=0,fast=0;fast<srcChars.length;fast++){
            //找到非空格点
            while(fast<srcChars.length){
                if(srcChars[fast]!=' '){
                    slow=fast;
                    break;
                }
                fast++;
            }
            //找到空格点
            while(fast<srcChars.length){
                if(srcChars[fast]==' '){
                    q.push(String.copyValueOf(srcChars,slow,fast-slow));
                    break;
                }
                fast++;
            }
            //如果已经到了最末尾
            if(fast==srcChars.length && srcChars[fast-1]!=' '){
                q.push(String.copyValueOf(srcChars,slow,srcChars.length-slow));
            }
        }
        if(q.size()<1) return "";
        String total = "";
        while(!q.isEmpty()){
            total+=q.pop();
            total+=" ";
        }
        total=total.substring(0,total.length()-1);
        return total;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
