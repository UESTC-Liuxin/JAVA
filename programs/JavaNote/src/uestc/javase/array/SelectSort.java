package uestc.javase.array;


/**
 * 选择排序：
 *
 */
public class SelectSort {
    public static void sort(int[] nums){
        for (int i=0;i<nums.length-1;i++) {
            int min = nums[i];
            int minIndex=i;
            for (int j = i; j < nums.length; j++) {
                if(min>nums[j]) {
                    min = nums[j];
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                nums[minIndex]=nums[i];
                nums[i]=min;
            }
        }
    }

}
