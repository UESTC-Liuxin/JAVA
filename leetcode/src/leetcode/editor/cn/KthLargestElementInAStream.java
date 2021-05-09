//设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。 
//
// 请实现 KthLargest 类： 
//
// 
// KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。 
// int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["KthLargest", "add", "add", "add", "add", "add"]
//[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//输出：
//[null, 4, 5, 5, 8, 8]
//
//解释：
//KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//kthLargest.add(3);   // return 4
//kthLargest.add(5);   // return 5
//kthLargest.add(10);  // return 5
//kthLargest.add(9);   // return 8
//kthLargest.add(4);   // return 8
// 
//
// 
//提示：
//
// 
// 1 <= k <= 104 
// 0 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// -104 <= val <= 104 
// 最多调用 add 方法 104 次 
// 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素 
// 
// Related Topics 堆 设计 
// 👍 258 👎 0

package leetcode.editor.cn;

import editor.cn.utils.heap.Heap;

import java.util.Arrays;

public class KthLargestElementInAStream {
    public static void main(String[] args) {
        KthLargest solution = new KthLargestElementInAStream().new KthLargest(3,
                new int[]{4,5,8,2,9});
        solution.add(3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {
        MyHeap minHeap;
        int K;

        public KthLargest(int k, int[] nums) {
            minHeap = new MyHeap(nums);
            K = k;
        }

        public int add(int val) {
            minHeap.insert(val);
            while (minHeap.size > K) {
                minHeap.pop();
            }
            return minHeap.heap[0];
        }

        class MyHeap {
            int[] heap;
            int size;

            MyHeap(int[] srcArr) {
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
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
