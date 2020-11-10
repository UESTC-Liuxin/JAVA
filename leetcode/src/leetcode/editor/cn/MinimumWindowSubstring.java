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

public class MinimumWindowSubstring{
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        String result=solution.minWindow("ADOBECODEBANC","ABC");
        System.out.println(result);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        //建立一个表，储存t中每个字符的频次
        Map<Character,Integer> tMap=new HashMap<Character, Integer>();
        Map<Character,Integer> slideWinMap=new HashMap<Character, Integer>();
        //统计滑窗中满足了t中的频次的字符个数
        int count=0;
        for(int i=0;i<t.length();i++){//填充表
            int temp=0;
            if(tMap.containsKey(t.charAt(i))){
                temp=tMap.get(t.charAt(i));//查看滑窗中t中的某个字符的个数
            }
            tMap.put(t.charAt(i),temp+1);
            slideWinMap.put(t.charAt(i),0);
        }

        String minString="";
        int left=0;//记录滑窗的左端点
        int minLength=s.length();
        for(int right=0;right<s.length();right++){
            if(slideWinMap.containsKey(s.charAt(right))){//发现t中字符
                int temp=slideWinMap.get(s.charAt(right));//查看滑窗中t中的某个字符的个数
                slideWinMap.put(s.charAt(right), temp+1);//将计数值加1
                if(temp+1==tMap.get(s.charAt(right))){//如果出现此字符的频次满足了t中频次,如果继续增加了，就不再计数
                    count++;
                }
            }
            while(count==tMap.size()) {//记录最小并且left向右收缩
                if(minLength>=right-left+1){
                    minString = s.substring(left, right + 1);
                    minLength=right-left+1;
                }
                if (slideWinMap.containsKey(s.charAt(left))) {//发现t中字符
                    int temp = slideWinMap.get(s.charAt(left));//查看滑窗中t中的某个字符的个数
                    slideWinMap.put(s.charAt(left), temp - 1);//将计数值减1
                    if (temp-1<tMap.get(s.charAt(left))) {//经过删除后，已经不满足最低频次
                        count--;//计数减1
                    }
                }
                left++;
            }


        }
        return minString;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}