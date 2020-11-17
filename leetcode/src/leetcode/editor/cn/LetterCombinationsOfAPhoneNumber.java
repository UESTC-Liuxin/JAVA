package leetcode.editor.cn;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 988 👎 0


import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        solution.letterCombinations("2");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        private String letterMap[] = {
                "",    //0
                "",     //1
                "abc",  //2
                "def",  //3
                "ghi",  //4
                "jkl",  //5
                "mno",  //6
                "pqrs", //7
                "tuv",  //8
                "wxyz"  //9
        };

        public List<String> letterCombinations(String digits) {

            char[] chars = digits.toCharArray();

            List<String> results = new ArrayList<String>();
            if(digits.length()==0) {
                return results;
            }

                        //定义一个队列
            Queue<String> queue = new LinkedList<String>();

            char[] letter=letterMap[char2num(chars[0])].toCharArray();
            //将起点压入队列
            for(char c:letter) {
                queue.offer(Character.toString(c));
                if(digits.length()==1) {
                    results.add(Character.toString(c));
                }
            }


            for (int i = 1; i < digits.length(); i++) {
                int sz = queue.size();
                letter = letterMap[char2num(chars[i])].toCharArray();
                for (int j = 0; j < sz; j++) {
                    String s = queue.poll();

                    for (char c:letter) {
                        queue.offer(s + Character.toString(c));
                        if (i == digits.length() - 1) {
                            results.add(s + Character.toString(c));
                        }
                    }
                }

            }
            return results;
        }
        public int char2num(char c){
            return c-48;
        }
    }



//leetcode submit region end(Prohibit modification and deletion)

}
