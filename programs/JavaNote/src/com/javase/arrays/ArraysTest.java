package com.javase.arrays;

import java.util.Arrays;

public class ArraysTest {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,5,6};
        System.out.println(~Arrays.binarySearch(arr,4));
    }
}
