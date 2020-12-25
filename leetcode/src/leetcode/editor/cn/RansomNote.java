package leetcode.editor.cn;
//给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面
//的字符构成。如果可以构成，返回 true ；否则返回 false。 
//
// (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。) 
//
// 
//
// 注意： 
//
// 你可以假设两个字符串均只含有小写字母。 
//
// canConstruct("a", "b") -> false
//canConstruct("aa", "ab") -> false
//canConstruct("aa", "aab") -> true
// 
// Related Topics 字符串 
// 👍 124 👎 0


import java.util.HashMap;

public class RansomNote {
    public static void main(String[] args) {
        Solution solution = new RansomNote().new Solution();
        solution.canConstruct("aa","ab");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> hashMap = new HashMap<>();
        char[] m=magazine.toCharArray();
        char[] r=ransomNote.toCharArray();
        if(m.length<r.length)
            return false;
        //先往里填充
        for (char c:m){
            int count=1;
            if(hashMap.containsKey(c)){
                count=hashMap.get(c)+1;
            }
            hashMap.put(c,count);
        }

        //再挨个遍历查询
        for(char c:r){
            int count=0;
            if(hashMap.containsKey(c)&&(count=hashMap.get(c))!=0){
                hashMap.put(c,count-1);
            }
            else return false;
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
