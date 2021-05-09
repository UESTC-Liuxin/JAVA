package leetcode.editor.cn;

//给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。 
//
// 
//
// 示例： 
//
// 输入：S = "ADOBECODEBANC", T = "ABC"
//输出："BANC" 
//
// 
//
// 提示： 
//
// 
// 如果 S 中不存这样的子串，则返回空字符串 ""。 
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 781 👎 0

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        String result = solution.minWindow("a", "aa");
        System.out.println(result);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            //建立一个表，储存t中每个字符的频次
            HashMap<Character, Integer> destMap = new HashMap<>();
            HashMap<Character, Integer> curMap = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                destMap.put(t.charAt(i), destMap.getOrDefault(t.charAt(i), 0) + 1);
            }

            int left = 0; //
            int cur = 0;
            int ansLen = Integer.MAX_VALUE;
            int ansL = 0;
            while (cur < s.length()) {
                curMap.put(s.charAt(cur), curMap.getOrDefault(s.charAt(cur), 0) + 1);

                while (check(destMap, curMap) && left <= cur) {//如果已经满足条件，就要收缩窗口
                    if (ansLen > cur - left + 1){
                        ansL = left;
                        ansLen = cur - left + 1;
                    }
                    curMap.put(s.charAt(left), curMap.get(s.charAt(left)) - 1);
                    left++;
                }
                cur++;
            }
            if(ansLen == Integer.MAX_VALUE)
                return "";
            return s.substring(ansL,ansL+ansLen);

        }

        private boolean check(HashMap<Character, Integer> destMap, HashMap<Character, Integer> curMap) {
            for (Character c : destMap.keySet()) {
                if (destMap.get(c) > curMap.getOrDefault(c, 0))
                    return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}