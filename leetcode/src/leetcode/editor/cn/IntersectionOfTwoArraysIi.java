package leetcode.editor.cn;
//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 427 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoArraysIi {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoArraysIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        /**哈希表法
        if(nums1.length>nums2.length)//取短的数组开始
            return intersect(nums2,nums1);
        Map<Integer,Integer> map =new HashMap<>();//用于记录短数组每个数字出现的次数
        int[] result = new int[nums1.length];
        for(int i:nums1){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        int index=0;
        for(int i:nums2){
            int count=map.getOrDefault(i,0);
            if(count>0) {//当小于0，就不再添加
                result[index++]=i;
                map.put(i,map.get(i)-1);
            }
        }
        return Arrays.copyOfRange(result,0,index);
        */

        //双指针法
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int pointer1=0;
        int pointer2=0;
        int len=Math.min(nums1.length,nums2.length);
        int[] result=new int[len];
        int index=0;

        while(pointer1<nums1.length && pointer2<nums2.length){
            if(nums1[pointer1]==nums2[pointer2]){
                result[index++]=nums1[pointer1];
                pointer1++;
                pointer2++;
            }
            else if(nums1[pointer1]<nums2[pointer2]) pointer1++;
            else pointer2++;
        }

        return Arrays.copyOfRange(result,0,index);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
