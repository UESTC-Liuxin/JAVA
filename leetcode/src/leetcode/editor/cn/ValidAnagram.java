package leetcode.editor.cn;
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 321 👎 0


public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] charCountTable=new int[26];
        char[] sc=s.toCharArray();
        char[] tc=t.toCharArray();
        //先从长度上判断是否相等
        if(s.length()!=t.length()) return false;
        for(char c:sc){
            charCountTable[c-97]+=1;
        }
        //每遇到一次就在原table上减一
        for (char c:tc){
            charCountTable[c-97]-=1;
            if(charCountTable[c-97]<0) return false;
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
