package leetcode.editor.cn;
//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 451 👎 0


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        solution.partition("aabbaa");

    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<String>> combinations;
    boolean[][] palindromeTable;
    public List<List<String>> partition(String s) {
        combinations = new ArrayList<>();
        List<String> list = new ArrayList<>();


        if(s.length()==0) return combinations;

        palindromeTable= new boolean[s.length()+1][s.length()+1];
        createPalindromeTable(s);

        backTracking(0,s,list);


        return combinations;

    }
    private void backTracking(int splitIndex, String s, List<String> list){
        if(splitIndex>=s.length()){
            List<String> temp = new ArrayList<>(list);
            combinations.add(temp);
            return;
        }
        //最后左开右闭式切割，最大索引值为字符串长度
        for(int i = splitIndex;i<s.length();i++){
            //如果不是回文子串，直接不要
            if(!palindromeTable[splitIndex][i]) continue;
            list.add(s.substring(splitIndex,i+1));
            backTracking(i+1,s,list);
            list.remove(list.size()-1);
        }
    }

    //这里利用动态规划记录是否是回文子串
    private boolean isPlindrome(int left,int right,String s){
        while(left<=right){
            if(s.charAt(left++)!=s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    //利用动态规划进行记录
    public void createPalindromeTable(String s){
        for(int i=0;i<s.length();i++){
            palindromeTable[i][i] =true;
        }
        for(int length=1;length<=s.length();length++){
            for(int start=0;start+length<s.length();start++){
                boolean temp=true;
                if(length==1){
                    temp=(s.charAt(start)==s.charAt(start+length));
                }
                else temp=((s.charAt(start)==s.charAt(start+length))&&palindromeTable[start+1][start+length-1]);
                palindromeTable[start][start+length]=temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
