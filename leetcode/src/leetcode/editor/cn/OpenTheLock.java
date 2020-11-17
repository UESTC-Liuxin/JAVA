package leetcode.editor.cn;
//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
// 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。 
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。 
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。 
//
// 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。 
//
// 
//
// 示例 1: 
//
// 
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
// 
//
// 示例 2: 
//
// 
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：
//把最后一位反向旋转一次即可 "0000" -> "0009"。
// 
//
// 示例 3: 
//
// 
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
//et = "8888"
//输出：-1
//解释：
//无法旋转到目标数字且不被锁定。
// 
//
// 示例 4: 
//
// 
//输入: deadends = ["0000"], target = "8888"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 死亡列表 deadends 的长度范围为 [1, 500]。 
// 目标数字 target 不会在 deadends 之中。 
// 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。 
// 
// Related Topics 广度优先搜索 
// 👍 189 👎 0


import java.util.*;

public class OpenTheLock {
    public static void main(String[] args) {
        Solution solution = new OpenTheLock().new Solution();
        System.out.println(solution.openLock(new String[]{"3030","1010"},"2020"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int openLock(String[] deadends, String target) {

        //利用一个队列来记录将要搜索的元素
        Queue<String> queue=new LinkedList<String>();
        //利用一个集合来记录已经搜索的元素
        Set<String>   visited  =new HashSet<String>();

        Set<String>  deadendsSet =new HashSet<String>(Arrays.asList(deadends));
        //
        //将起点加入队列
        queue.offer("0000");
        visited.add("0000");

        int step=0;

        while(!queue.isEmpty()){
            int sz = queue.size();
            //扩散节点
            for(int i=0;i<sz;i++){
                String s=queue.poll();
                //判断是否在死亡数字
                if(deadendsSet.contains(s))
                    continue;
                //判断是否到终点
                if(s.equals(target))
                    return step;
                for(int j=0;j<4;j++){
                    String up=upDila(s,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down=downDila(s,j);
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }//end while
        return -1;
    }

    //向上拨动
    public String upDila(String s,int i){
       char[] chars = s.toCharArray();
       if(chars[i]=='9')
           chars[i]='0';
       else
           //这里不用转成数字再加一，因为ascii码加一是一样的
           chars[i]+=1;
       return new String(chars);
    }

    //向下拨动
    public String downDila(String s,int i){
        char[] chars = s.toCharArray();
        if(chars[i]=='0')
            chars[i]='9';
        else
            chars[i]-=1;
        return new String(chars);

    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
