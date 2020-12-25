package leetcode.editor.cn;

//实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须原地修改，只允许使用额外常数空间。 
//
// 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 
//1,2,3 → 1,3,2 
//3,2,1 → 1,2,3 
//1,1,5 → 1,5,1 
// Related Topics 数组 
// 👍 809 👎 0

public class NextPermutation{
    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        solution.nextPermutation(new int[]{1,2,3});
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        for (int i = nums.length - 1; i >= 1; i--) {
            //寻找相邻升序
            if (nums[i] > nums[i - 1]) {
                for (int j = nums.length - 1; j >=i; j--) {
                    //从后往前寻找第一个大于nums[i-1]的数字
                    //进行交换
                    if (nums[j] >nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        reverse(nums, i, nums.length - 1);
                        return;
                    }
                }//end of for
            }//end of if
        }//end of for
        //直接没找到，直接反转
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums,int start, int end){
        int temp;
        for(int i=start,j=end;i<j;i++,j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}


//leetcode submit region end(Prohibit modification and deletion)

}