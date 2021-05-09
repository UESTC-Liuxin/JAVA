package editor.cn.utils.heap;

import java.util.Arrays;

public class Heap {
    int[] heap;
    int size;

    Heap(int[] srcArr) {
        heap = srcArr;
        size = heap.length;
        if(size>0) {
            //先构建一个最小堆
            int currentPos = (size - 2) / 2;
            heapfy(currentPos, size - 1);
        }
        else {
            heap = new int[10];
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    //小根堆的构建，就是从最后一个父节点依次往前下滑，保证下滑后
    //此父节点的子女都满足最小堆
    private void heapfy(int i, int length) {
        int currentPos = i;
        while (currentPos >= 0) {
            shiftDown(currentPos, length);
            currentPos--;
        }

    }

    //下滑操作从start开始调整，到最后一个结点为止。
    // 结束条件为什么要加上temp<heap[j],因为默认下面的都已经变成小根堆了
    private void shiftDown(int start, int m) {
        int i = start;
        int j = 2 * start + 1; //左子女
        int temp = heap[i];
        while (j <= m) {
            if (j < m && heap[j] > heap[j + 1]) //让j指向两子女中的小者
                j += 1;
            if (temp <= heap[j]) break;  //小则不做调整
            else {                       //否则将小的那个变成当前的根
                heap[i] = heap[j];
                i = j;
                j = 2 * i + 1;    //重新得到左子女
            }
        }
        heap[i] = temp;

    }

    private void shiftUp(int start) {
        //往上滑就完事儿了
        int j = start;
        int i = (j - 1) / 2;//找到父节点
        int temp = heap[j]; //保留子节点
        while (j > 0) {
            if (heap[i] < temp) break;//如果父节点小于子节点，结束
            else {
                heap[j] = heap[i];
                j = i;
                i = (i - 1) / 2;
            }
        }
        heap[j] = temp;
    }

    public void insert(int x) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length << 2);
        }
        heap[size] = x;
        shiftUp(size);
        size++;

    }

    public int pop() {
        int x = heap[0];
        heap[0] = heap[size - 1];
        size--;
        shiftDown(0, size - 1);
        return x;
    }


}

