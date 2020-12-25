package leetcode.editor.cn;
//编写一个算法来判断一个数 n 是不是快乐数。 
//
// 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
//如果 可以变为 1，那么这个数就是快乐数。 
//
// 如果 n 是快乐数就返回 True ；不是，则返回 False 。 
//
// 
//
// 示例： 
//
// 输入：19
//输出：true
//解释：
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1
// 
// Related Topics 哈希表 数学 
// 👍 505 👎 0


import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public static void main(String[] args) {
        Solution solution = new HappyNumber().new Solution();
        solution.isHappy(19);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isHappy(int n) {
        //事实证明，在判断有环这个方面，快慢指针真的比Set要快得多
        int slow=n;
        int fast=n;
        do{
            slow=splitNum(slow);
            fast=splitNum(fast);
            if(fast==1) return true;
            fast=splitNum(fast);

        }while (fast!=slow);

        return false;

    }

    /**
     * 切分数字并计算平方和
     * @param n
     * @return
     */
    private int splitNum(int n){
        int sum=0;
        int remainder=0;
        while(n>0){
            remainder=n%10;
            n=n/10;
            sum+=remainder*remainder;
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
