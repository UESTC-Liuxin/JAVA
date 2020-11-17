package leetcode.editor.cn;
//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。 
//
// 注意: 
//
// 
// num 的长度小于 10002 且 ≥ k。 
// num 不会包含任何前导零。 
// 
//
// 示例 1 : 
//
// 
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
// 
//
// 示例 2 : 
//
// 
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
// 
//
// 示例 3 : 
//
// 
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
// 
// Related Topics 栈 贪心算法 
// 👍 409 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RemoveKDigits {
    public static void main(String[] args) {
        Solution solution = new RemoveKDigits().new Solution();
        System.out.println(solution.removeKdigits("10",1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length()<=k)
            return "0";
        //重新定义k的含义：k为取k个数字
        k=num.length()-k;
        //转成字符
        char[] nums=num.toCharArray();
        //定义队列
        Queue<String> queue=new LinkedList<String>();
        Queue<Integer> indexQueue=new LinkedList<Integer>();

        //定义visit
        Set<String> visited = new HashSet<String>();
        queue.offer("0");
        indexQueue.offer(-1);
        visited.add("0");

        //开始框架
        for(int i=0;i<k;i++){
            int sz=queue.size();
            int saveIndex=0;
            //定义最小值
            int min=10;

            String s=queue.poll();
            int index =indexQueue.poll();
            //规定只能往后面取，并且取到最小的放在队列里
            for(int j=index+1;j<=num.length()-k+i;j++){

                int value=nums[j]-48;
                //求得最小值
                if(min>value){
                    min=value;
                    saveIndex=j;
                }
            }
            //
            String temp=s+Character.toString(nums[saveIndex]);
            queue.offer(temp);
            indexQueue.offer(saveIndex);
        }
        //消除0
        String s=queue.poll();
        s = s.replaceAll("^(0+)", "");
        return (s.isEmpty())?"0":s;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
