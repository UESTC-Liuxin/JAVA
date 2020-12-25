package leetcode.editor.cn;
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 765 👎 0


import java.util.Arrays;

public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        System.out.println(solution.searchInsert(new int[]{1,3},1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        Arrays.sort(nums);//先行排序，用二分法
        int left=0;
        int right=nums.length-1;
        //要确定边界条件：当左右边界收缩到一点，这个时候还是需要判断是不是与目标值相等
        while (left <=right) {
            int mid = ((right - left) >> 1) + left;
            if (target <nums[mid]) {
                right = mid - 1;
            }
            else if(target>nums[mid]) {
                left = mid + 1;
            }
            else
                return mid;
        }
        //包含了4种情况：target比最左端值小，[left,right]=[0,-1],返回right+1
        //target比最右端值大，[left,right]=[N,N-1],返回right+1
        //target插入数组中的位置 ,如果当mid=left=right时，target>nums[mid],left>mid,这时插在righ+1
        //      反之，right<mid,这时插到left处，left=right+1
        return right+1;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
