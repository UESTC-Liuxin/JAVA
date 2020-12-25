package indi.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] srcArr=new int[]{26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
        MergeSort.sort(srcArr,0,srcArr.length-1);
        MergeSort.printArr(srcArr);
    }


    public static void printArr(int[] srcArr){
        for(int i:srcArr){
            System.out.print(String.valueOf(i)+" ");
        }
        System.out.println();
    }
    /**
     * 递归函数：递归二分子数组，并进行子数组的合并
     * @param srcArr:需要排序的数组
     * @param left：左边界数组下标
     * @param right：右边界数组下标，[left,right]
     */
    public static void sort(int[] srcArr,int left,int right){
        if(left>=right) return;
        int mid = (left+right)/2;
        //二路归并排序里面有两个sort
        sort(srcArr,left,mid);
        sort(srcArr,mid+1,right);
        merge(srcArr,left,mid,right);

    }

    /**
     * 对两个子数组按照顺序进行合并
     * @param srcArr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] srcArr,int left,int mid,int right){
        int[] tmp = new int[srcArr.length];
        int r1 =mid + 1;//用于记录右子数组的下标
        int tIndex = left;//赋值时的下标
        int cIndex = left;

        //按照从小到大进行填充，填充完大概率某个数组还有剩
        while(left <=mid && r1 <=right){
            if(srcArr[left] <= srcArr[r1]){
                tmp[tIndex++]=srcArr[left++];
            }
            else
                tmp[tIndex++]=srcArr[r1++];
        }
        //处理剩下的数字
        while (r1<=right){
            tmp[tIndex++]=srcArr[r1++];
        }
        while (left<=mid){
            tmp[tIndex++] =srcArr[left++];
        }

        //将缓存数组拷贝到原数组
        while (cIndex<=right){
            srcArr[cIndex]=tmp[cIndex];
            cIndex++;
        }
    }
}
