package leetcode.editor.cn;
//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// 👍 55 👎 0


public class TiHuanKongGeLcof {
    public static void main(String[] args) {
        Solution solution = new TiHuanKongGeLcof().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {
        char[] chars = new char[s.length()*3];
        char[] copyiedChars=s.toCharArray();
        int size=0;
        for(char c:copyiedChars){
            if(c==' '){
                chars[size++]='%';
                chars[size++]='2';
                chars[size++]='0';
            }
            else chars[size++]=c;
        }
        return new String(chars,0,size);

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
