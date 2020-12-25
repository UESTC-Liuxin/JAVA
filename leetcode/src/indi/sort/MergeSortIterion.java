package indi.sort;

public class MergeSortIterion {
    public static void main(String[] args) {
        int[] srcArr=new int[]{26, 5, 98, 108, 28, 99, 100, 56, 34};
        MergeSortIterion.MergeSort(srcArr);
        MergeSort.printArr(srcArr);
    }

    /**
     *
     * @param srcArr
     */
    public static void MergeSort(int[] srcArr){
        for(int i=1;i<srcArr.length;i*=2){
            //外层循环，i代表长度每一次合并前子数组的长度
            int right;
            for (int left=0;left<srcArr.length-i;left=right+1){
                //内循环，不断步进子数组
                int mid=left+i-1;
                right=mid+i;
                if(right>=srcArr.length) right=srcArr.length-1;
                merge(srcArr,left,mid,right);
            }

        }

    }

    /**
     * 对子数组进行合并
     * @param srcArr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] srcArr,int left,int mid,int right){
        int[] backupArr=new int[srcArr.length];
        int tIndex=left;
        int l=left,r=mid+1;
        while (l<=mid&&r<=right){
            if(srcArr[l]>srcArr[r])
                backupArr[tIndex++]=srcArr[r++];
            else
                backupArr[tIndex++]=srcArr[l++];
        }
        while(l<=mid) backupArr[tIndex++]=srcArr[l++];
        while (r<=right) backupArr[tIndex++]=srcArr[r++];
        for(int i=left;i<=right;i++)
            srcArr[i]=backupArr[i];
    }
}
