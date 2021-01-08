package leetcode.editor.cn;
//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
// 和 "192.168@1.1" 是 无效的 IP 地址。 
//
// 
//
// 示例 1： 
//
// 输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法 
// 👍 479 👎 0


import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();
        solution.restoreIpAddresses("101023");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> list= null;
    public List<String> restoreIpAddresses(String s) {
        list = new ArrayList<>();
        String ip="";
        backTracking(0,4,s,ip);
        return list;

    }

    private void backTracking(int splitIndex,int remainPos,String s,String ip) {
        if(remainPos==0 && splitIndex==s.length()){
            String temp= new String(ip);
            list.add(temp.substring(0,temp.length()-1));
            return;
        }
        if(remainPos==0) return;
        //每个ip的网段长度不能大于3或者小于1
        for(int i=splitIndex;i<Math.min(splitIndex+3,s.length());i++){
            String subIp = s.substring(splitIndex,i+1);
            System.out.println(subIp);
            //保证ip合法
            if(!isValid(subIp)) break;
            String appenedIp=ip+subIp+".";
            backTracking(i+1,remainPos-1,s,appenedIp);
        }
    }

    private boolean isValid(String subIp){
        try{
            int temp=Integer.parseInt(subIp);
            if(temp > 255) return false;
            if(subIp.length()>1&&subIp.charAt(0)=='0') return false;
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
