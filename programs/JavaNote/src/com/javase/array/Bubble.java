package com.javase.array;
class BubbleTest{
    public static void main(String[] args) {
        int[] nums={2,3,5,2,7,1,0};
//        Bubble.sort(nums);
        Bubble.printNums(nums);
        SelectSort.sort(nums);
        Bubble.printNums(nums);
    }
}

public class Bubble {

    public static void sort(int[] nums){
        for (int i=0;i<nums.length;i++)
            for(int j=1;j<nums.length-i;j++){
                if(nums[j-1]>nums[j]){
                    int temp=nums[j-1];
                    nums[j-1]=nums[j];
                    nums[j]=temp;
                }

            }
    }
    public static void printNums(int[] nums){
        for (int num:nums) {
            System.out.print(num);
        }
        System.out.println('\n');

    }

}
