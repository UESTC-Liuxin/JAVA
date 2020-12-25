package uestc.javase.array;

import java.util.Arrays;

public class ArraysTest {
    public static void main(String[] args) {
        int[] nums={1,3,5,0,2};
        Arrays.sort(nums);
        ArraysTest arraysTest =new ArraysTest();
        arraysTest.printNums(nums);
    }

    public void printNums(int[] nums){
        for (int num:nums) {
            System.out.print(num);
        }
        System.out.println('\n');

    }
}
