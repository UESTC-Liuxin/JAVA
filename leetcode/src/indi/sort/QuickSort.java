package sort;

import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }
        quickSort(nums,0,N-1);
        for (int num : nums) {
            System.out.println(num + "");
        }
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int i = l;
            int j = r;
            int temp = nums[i];
            while (i < j) {
                while (i < j && nums[j] >= temp) { //从右边往左边找，直到比temp小的
                    j--;
                }
                if (i < j)
                    nums[i++] = nums[j];
                while (i < j && nums[i] <= temp) { //从左边找
                    i++;
                }
                if (i < j)
                    nums[j--] = nums[i];
            }
            nums[i] = temp;
            quickSort(nums, 0, i - 1);
            quickSort(nums, i + 1, r);
        }
    }
}
