package interview.Ali;

import java.util.Arrays;
import java.util.Scanner;

public class Ali_1st {
    public static void main(String[] args) {
        Ali_1st solution = new Ali_1st();
        solution.input_array();
    }

    //输入一个数组
    private void input_array() {
        Scanner reader = new Scanner(System.in) ;
        while(reader.hasNext())
        {
            int m = reader.nextInt() ;
            int [] numbers = new int[m] ;
            for(int index=0;index<m;index++)
            {
                numbers[index] = reader.nextInt();
            }

            //输出
            System.out.println(Arrays.toString(numbers));
        }
        reader.close() ;
    }

    //输入一个矩阵
    private void input_matrix() {
        Scanner reader = new Scanner(System.in);
        int m = reader.nextInt();
        int n = reader.nextInt();
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                array[i][j] = reader.nextInt();
            }
        reader.close();
        printMatrix(array);
    }

    private void printMatrix(int[][] matrix) {
        for (int[] line : matrix) {
            for (int num : line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }
}
