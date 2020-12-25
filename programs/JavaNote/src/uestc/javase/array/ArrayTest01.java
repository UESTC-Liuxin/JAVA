package uestc.javase.array;


/*

 */
public class ArrayTest01 {
    public static void main(String[] args) {
        int[] a =new int[]{1,2,3};
        ArrayTest01 arrayTest01=new ArrayTest01();
        int[] b=a.clone();
        System.out.println(a[0]);//输出为0
        arrayTest01.changeArray(a);
        System.out.println(a[0]);
        System.out.println(b[0]);


    }
    public void changeArray(int[] array){
        array[0]=0;
    }

}
