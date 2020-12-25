package uestc.javase.Random;

import java.util.Arrays;
import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random =new Random();
        //建立数组储存下来
        int[] nums=new int[]{-1,-1,-1,-1,-1};

        int index=0;
        //嵌套循环生成随机数
        while (index<5){
            int randomInt=random.nextInt(100);
            if(!RandomTest.isContains(nums,randomInt)){
                nums[index++]=randomInt;
            }
        }
        RandomTest.printArray(nums);

    }

    /**
     *无序数组的查找
     * @param nums 被查找数组
     * @param target 查找目标
     * @return
     */
    static boolean isContains(int[] nums,int target){
        for (int num:nums) {
            if(target==num)
                return true;
        }
        return false;
    }

    /**
     * 打印整数数组
     * @param array
     */
    static void printArray(int[] array){
        for(int i:array){
            System.out.println(i);
        }
    }
}

