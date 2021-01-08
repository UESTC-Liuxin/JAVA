# Leetcode笔记

## 数据结构

### 数组

**数组是存放在连续内存空间上的相同类型数据的集合。**正是**因为数组的在内存空间的地址是连续的，所以我们在删除或者增添元素的时候，就难免要移动其他元素的地址**

数组的增删操作至少都是O(n)的，所以，需要中间节点的增删操作时，尽量不选择数组

另外，二维数组的地址，数组与数组间不是连续的地址。

二位数组中其实是一个线性数组存放着 其他数组的首地址。

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201215215617274.png" alt="image-20201215215617274" style="zoom:80%;" />

[35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

一眼望去二分法，二分法模板代码：

```java
while (left <= right) {
    int mid = ((right - left) >> 1) + left;
    if (target <nums[mid]) {
    	//do something here
        right = mid - 1;
    } 
    else if(target>nums[mid]) {
        left = mid + 1;
    }
    else
        return mid;
}
```

程序的结束条件有两个，逐一分析：

插入条件：$nums[pos-1]<target<nums[pos]$，返回pos

找到目标返回：$nums[pos]==target$，返回pos

```java
    public int searchInsert(int[] nums, int target) {
        Arrays.sort(nums);//先行排序，用二分法
        int left=0;
        int right=nums.length-1;
        //要确定边界条件：当左右边界收缩到一点，这个时候还是需要判断是不是与目标值相等
        while (left <=right) {
            int mid = ((right - left) >> 1) + left;
            if (target <nums[mid]) {
                right = mid - 1;
            }
            else if(target>nums[mid]) {
                left = mid + 1;
            }
            else
                return mid;
        }
        //包含了4种情况：target比最左端值小，[left,right]=[0,-1],返回right+1
        //target比最右端值大，[left,right]=[N,N-1],返回right+1
        //target插入数组中的位置 ,如果当mid=left=right时，target>nums[mid],left>mid,这时插在righ+1
        //      反之，right<mid,这时插到left处，left=right+1
        return right+1;

    }
```

[27. 移除元素](https://leetcode-cn.com/problems/remove-element/)

![图片](https://mmbiz.qpic.cn/mmbiz_gif/ciaqDnJprwv4rN7Lc7cH7s9Xs5Gfgibnf7kT8a6hE8xVSviaeZHuIqUAx3HRef0dDvg5roSXCIulV2XcjOnxKwYVA/640?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1)

利用双指针，快指针寻找往后第一个不等于val的值，慢指针记录当前被删除的空位。

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int leftPoint=0,rightPoint=0;
        for(;rightPoint<nums.length;rightPoint++){
            if(val!=nums[rightPoint]){
                nums[leftPoint]=nums[rightPoint];
                leftPoint++;
            }
        }

        return leftPoint;

    }
}
```

[209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

这道题一看到就是用双重循环的滑窗法进行，先固定窗的宽度进行遍历，再改变窗的宽度，时间复杂度为O(n)

**双指针法**

但是这道题有个更加简单的方法，这道题实际上有个关键的点，就是窗口在不断增大的过程中，当突然sum满足了，这个时候就可以进行一个收缩。但是收缩的方向呢，不能向左收缩，因为向左收缩就回到上一步了，因此只有左边界向右收缩。如下：

![图片](https://mmbiz.qpic.cn/mmbiz_gif/ciaqDnJprwv6MwIsdYLFnPSXSJ3WgSPQRf3oaBEAYc57vWs1aSc4YMjmMSawj3QQxd4A81P4XYF6sibPK0lZ1ic4w/640?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1)

窗口内的和已经满足条件时，一直收缩直到不再满足条件。

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLength=nums.length+1;
        int sum=0;
        for(int fastPoint=0,slowPoint=0;fastPoint<nums.length;fastPoint++){
            sum+=nums[fastPoint];//fastPoint每向前移动一次就对sum加一次
            while(sum>=s){
                //slowPoint的移动
                int length=fastPoint-slowPoint+1;//记录窗口的宽度
                minLength=Math.min(length,minLength);
                sum-=nums[slowPoint++]; //slowpoint移动前减掉当前的值
            }
        }
        if(minLength>nums.length) return 0;
        return minLength;
    }
}
```

**二分查找法**

 我们申请一个临时数组 sums，其中 sums[i] 表示的是原数组 nums 前 i 个元素的和，题中说了 “给定一个含有 n 个 正整数 的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。我们只需要找到 sums[k]-sums[j]>=s，那么 k-j 就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止。怎么找呢，我们可以使用两个 for 循环来枚举，但这又和第一种暴力求解一样了，所以我们可以换种思路，求 sums[k]-sums[j]>=s 我们可以求 sums[j]+s<=sums[k]，那这样就好办了，因为数组sums中的元素是递增的，也就是排序的，我们只需要求出 sum[j]+s 的值，然后使用二分法查找即可找到这个 k。

Java

```java
public int minSubArrayLen(int s, int[] nums) {
    int length = nums.length;
    int min = Integer.MAX_VALUE;
    int[] sums = new int[length + 1];
    for (int i = 1; i <= length; i++) {
        sums[i] = sums[i - 1] + nums[i - 1];
    }
    for (int i = 0; i <= length; i++) {
        int target = s + sums[i];
        int index = Arrays.binarySearch(sums, target);
        if (index < 0)
            index = ~index;
        if (index <= length) {
            min = Math.min(min, index - i);
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```
注意这里的函数 int index = Arrays.binarySearch(sums, target);如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置
举个例子，比如排序数组 [2，5，7，10，15，18，20] 如果我们查找 18，因为有这个数会返回 18 的下标 5，如果我们查找 9，因为没这个数会返回 -4（至于这个是怎么得到的，大家可以看下源码，这里不再过多展开讨论），我们对他取反之后就是3，也就是说如果我们在数组中添加一个 9，他在数组的下标是 3，也就是第 4 个位置（也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标）

[54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

这道题最简单的就是模拟过程了，也就是让程序去模仿我们画圈的一个过程，但是模拟过程，必须要掌握一个恒定的定律，前开后闭，或者前闭后开。这句话的意思是什么呢？举个例子：

![图片](https://mmbiz.qpic.cn/mmbiz_png/ciaqDnJprwv75lvFebqBLu3d5uGMdAw1edpy4wAhTOucI7Bl63R2IQZwbpHlNzjXLhq4uVqA0jCzD8esO1KBOEA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

比如这个矩阵，第一次遍历的方向是从左到右，但是当我们走到右上角这个点的时候，我们需要选择这个点是属于从左到右的遍历还是属于从上到下的遍历。

如果我们选择前闭后开的方式：（存在问题）

假如我们原本是一个3\*3的矩阵，那么在循环中的起点应该是4\*矩阵的最左边开始也就是从3\*3中的（0，-1）开始，会造成数据索引越界，因此需要在最开头做一个判断。

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201217142538305.png" alt="image-20201217142538305" style="zoom:80%;" />

同时，在最后的一个像素点访问的时候，由于我们设置的是前开后闭，最后一个像素点为了让使后闭，造成了最后这个点无法进入遍历中。

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201217144030658.png" alt="image-20201217144030658" style="zoom:80%;" />

因此需要做很多判断。

但是选择前开后闭就没有那么多顾虑：

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201217144534100.png" alt="image-20201217144534100" style="zoom:80%;" />

这个3\*3的每一圈都形成一个完全闭合的内循环。不需要对开始和结束的条件进行任何判断。

前闭后开代码：

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int l = -1, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        List<Integer> list = new ArrayList<>();
        int total = matrix.length * matrix[0].length;
        int num = 0;

        while (num<total) {

            for (int i = l; i <r && num<total; i++) { //从左到右
                if(i==-1)
                    continue;
                list.add(matrix[t][i]);
                num++;
            }
            l++;
            for (int i = t; i <b && num<total; i++) { //从上到下
                list.add(matrix[i][r]);
                num++;
            }
            t++;
            for (int i = r; i > l && num<total; i--) { //从右到左
                list.add(matrix[b][i]);
                num++;
            }
            r--;
            for (int i = b; i >t  && num<total; i--) { //从下到上
                list.add(matrix[i][l]);
                num++;
            }
            b--;
        }
        return list;
/*
        if(nums1.length>nums2.length)//取短的数组开始
            return intersect(nums2,nums1);
        Map<Integer,Integer> map =new HashMap<>();//用于记录短数组每个数字出现的次数
        int[] result = new int[nums1.length];
        for(int i:nums1){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        int index=0;
        for(int i:nums2){
            int count=map.getOrDefault(i,0);
            if(count>0) {//当小于0，就不再添加
                result[index++]=i;
                map.put(i,map.get(i)-1);
            }
        }
        return Arrays.copyOfRange(result,0,index);*/
    }
}
```

[26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

这道题显然的快慢指针法，快指针检测与当前值与上一个只不相同的位置，慢指针保留有效值的位置，当检测到了不同值，将慢指针下移一位填补下一个有效值。

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int slowPoint=-1;
        int preVal=Integer.MAX_VALUE;
        for(int fastPoint=0;fastPoint<nums.length;fastPoint++){
            if(preVal!=nums[fastPoint]){//当发现不相等的数字
                slowPoint++;
                preVal=nums[fastPoint]=nums[slowPoint]=nums[fastPoint];
            }

        }
        return slowPoint+1;//返回长度

    }
}
```





```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        for (int i = nums.length - 1; i >= 1; i--) {
            //寻找相邻升序
            if (nums[i] > nums[i - 1]) {
                for (int j = nums.length - 1; j >=i; j--) {
                    //从后往前寻找第一个大于nums[i-1]的数字
                    //进行交换
                    if (nums[j] >nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        reverse(nums, i, nums.length - 1);
                        return;
                    }
                }//end of for
            }//end of if
        }//end of for
        //直接没找到，直接反转
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums,int start, int end){
        int temp;
        for(int i=start,j=end;i<j;i++,j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
```

[31. 下一个排列](https://leetcode-cn.com/problems/next-permutation/)

首先弄清楚什么叫做字典序，字典序指的就是对于一个序列，此序列自由排列，对各种自由排列的情况进行按照序列的元素的从左到右的正序排序：

比如[1,2,4,3]这个序列，按照从小到大的字典序就是：1234,1243,1324,1342,1423,1432,4123,...,4321。

按照我们人的思想：对于一个排列，我们是怎么找到下一个比它大的排列的呢？

比如：13542的下一个排列，我们知道，最后三位是一个降序，那么最后两位已经是这三个数字所能构成的最大的排列了。要想得到一个比这更大的数，只能考虑更往前一位，3542，看到这儿，我们发现35是一个升序，那么至少把5换到3的前面一定可以找到比3542更大的排列。

那么要怎么换才是比3542大又最靠近3542的排列呢？

可以发现，如果将3和5交换，是5342，将3和4交换，是4532，将3与2交换是2543。那么只有与4交换才可能是最合适的，注意：==现在542肯定是一个降序的，所以只需要从后往前找，在5和2之间，找到一个比3大的(这是从后往前找，找到的第一个就是4)，直接与4进行交换==

交换完了我们发现4532是以4开头最大的，因为532是降序（一定是降序，因为我们交换的标准就是一个差值），只需要翻转532，得到235，最后4235就是3542的下一个排列了。

代码：

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        for (int i = nums.length - 1; i >= 1; i--) {
            //寻找相邻升序
            if (nums[i] > nums[i - 1]) {
                for (int j = nums.length - 1; j >=i; j--) {
                    //从后往前寻找第一个大于nums[i-1]的数字
                    //进行交换
                    if (nums[j] >nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        reverse(nums, i, nums.length - 1);
                        return;
                    }
                }//end of for
            }//end of if
        }//end of for
        //直接没找到，直接反转
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums,int start, int end){
        int temp;
        for(int i=start,j=end;i<j;i++,j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
```

[283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

这道题跟那一道删除2的题是一样的，只是最后把后面的几位填上0就好了。没什么好说的。

[383. 赎金信](https://leetcode-cn.com/problems/ransom-note/)

这道题建立一个哈希表就可以了，直接用哈希表对magazine进行统计，在第二次循环中找一个删一个，完成判断。

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> hashMap = new HashMap<>();
        char[] m=magazine.toCharArray();
        char[] r=ransomNote.toCharArray();
        if(m.length<r.length)
            return false;
        //先往里填充
        for (char c:m){
            int count=1;
            if(hashMap.containsKey(c)){
                count=hashMap.get(c)+1;
            }
            hashMap.put(c,count);
        }

        //再挨个遍历查询
        for(char c:r){
            int count=0;
            if(hashMap.containsKey(c)&&(count=hashMap.get(c))!=0){
                hashMap.put(c,count-1);
            }
            else return false;
        }
        return true;

    }
}
```

[40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)

这道题用回溯法很简单，但是这道题有个关键的地方就是，他不能重复，也就是说，对于[1,1,2] target=3来说，常规的回溯法，我们回找到第一次会找到1,2（其中1是第一个1。）还会找到一个[1,2] (其中1是第二个1)。

所谓去重，其实就是使用过的元素不能重复选取。 这么一说好像很简单！

都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，一个维度是同一树枝上使用过，一个维度是同一树层上使用过。没有理解这两个层面上的“使用过” 是造成大家没有彻底理解去重的根本原因。

那么问题来了，我们是要同一树层上使用过，还是统一树枝上使用过呢？

回看一下题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。

所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重。

为了理解去重我们来举一个例子，candidates = [1, 1, 2], target = 3，（方便起见candidates已经排序了）

选择过程树形结构如图所示：

![40.组合总和II.png](https://pic.leetcode-cn.com/1604390529-inLFJH-40.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.png)

==所以这里只要保证同一层不取到以前取到过的值就好了。==

```java
class Solution {
    int[] candidates;
    List<List<Integer>> combines;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);    //先进行一个排序，方便后面我们排除重复
        this.candidates=candidates;
        this.combines= new LinkedList<List<Integer>>();
        backTrace(0,0,target,new LinkedList<Integer>());
        return this.combines;
    }

    public void backTrace(int startIndex,int sum,int target,List<Integer> combine){
        if (target<sum) return;
        if (target==sum){
            this.combines.add(new LinkedList<>(combine));
            return;
        }
        int pre=-1;   //记录上一个值是什么
        for(int i=startIndex;i<this.candidates.length;i++){
            if(pre==this.candidates[i])
                continue;
            pre=this.candidates[i];
            combine.add(pre);
            backTrace(i+1,sum+pre,target,combine);
            combine.remove(combine.size()-1);
        }
    }
}
```

### 链表

链表嘛，单向链表，双向链表，循环链表。

链表技巧：

- 设置虚拟头节点。
- 每次next之后都要记得把当前指向下移
- to be continue...

[203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //创建虚拟头结点
        ListNode dummyNode=new ListNode(0);
        //pre记录上一个不为val的节点，cur记录当前节点
        ListNode pre = dummyNode,cur=head;
        while(cur!=null){
            if(cur.val!=val){
                pre.next=cur;
                pre=pre.next;
            }
            cur=cur.next;
        }
        pre.next=null;
        return dummyNode.next;
    }
}
```

[206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

这道题最简单直观的方式就是递归，因为递归就是一个触底反弹的过程，而我们新的链表的结点添加就在反弹这个过程实现。

返回的条件就是结点的下一个结点为null，证明已经到了尾结点，这个时候就需要将尾结点当做新的链表的结点，并将新的链表的下一个结点的地址进行返回。

每次执行完递归函数了，还需要将这层的结点重新添加到新的链表结构上。

为了节省内存消耗，可尝试在原列表上面进行，具体过程如下：

```java
class Solution {
    ListNode dummyNode;
    public ListNode reverseList(ListNode head) {
        this.dummyNode =new ListNode(0);
        if(head==null) return null;
        recurAdd(head);
        head.next=null;
        return this.dummyNode.next;
    }

    public ListNode recurAdd(ListNode searchNode){
        if(searchNode.next==null){
            dummyNode.next=searchNode;
            return dummyNode.next;
        }
        ListNode cur=recurAdd(searchNode.next);
        cur.next=searchNode;
        return cur.next;
    }
}
```

链表的链接变化情况如下：

```java
原本的链表序
head--->node1--->node2--->node3--->node4--->null 
到了尾结点，把尾结点赋给dummyNode.next，并返回node4.next,这个时候node4还指向的是null
head--->node1--->node2--->node3--->node4(dummyNode.next)--->null   
返回上一层递归后，要将返回的node4.next重新指向node3,这个时候node3和node4相互连接
head--->node1--->node2--->node3<--->node4(dummyNode.next)
再返回上一层时，node3.next又改为指向node2...
head--->node1--->node2<--->node3<---node4(dummyNode.next)
.....到了最后返回的时候的指向情况
head<--->node1--->node2<--->node3<---node4(dummyNode.next)
这个时候还不能直接返回，需要把head.next指向null才行
```

除了上面的方法，还有一个就是交换法：

简单说来就是存下上一个结点，并改变当前结点指向上一节点。

```java
private ListNode reverseList(ListNode head){
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}
```

[707. 设计链表](https://leetcode-cn.com/problems/design-linked-list/)

这道题主要就是链表的增删查改操作。

- 使用虚拟节点，在遍历的时候，==cur指向的永远都是index的上一个节点。==
- 可以选择使用双向链表与单向列表，双向链表有两个好处，插头和插尾的时间复杂度都是O(1)，并且查找的复杂度最多为O(n/2).

单向链表的实现方式：

```java
class MyLinkedList {
        ListNode dummyNode;
        private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.dummyNode =new ListNode(0);
        this.size=0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>=size || index<0) return -1;
        ListNode cur=dummyNode;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.next.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        //添加头结点
        addAtIndex(0,val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size || index<0) return;
        ListNode temp=new ListNode(val);
        ListNode cur=dummyNode;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        temp.next=cur.next;
        cur.next =temp;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index>=size || index<0) return;
        ListNode cur=dummyNode;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.next=cur.next.next;//直接跳过这个点
        size--;
    }
}
```

[141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

这道题非常巧妙，判断一个链表有没有环，主要是依靠什么呢？

一种方案就是用哈希表记录所有节点被访问的次数，只要被访问了两次就证明其有环，但是其空间复杂度为O(n)。

还有一种就是如果有环，那么一定是同一个地方会走两遍，这个时候如果用两个指针，一个走得快，一个走得慢，当走得慢的指针进入环只有，一定会被在某一个时刻被走得快的人追上。基于此思想来设计程序。

```java
    public boolean hasCycle(ListNode head) {
        ListNode dummyNode=new ListNode(0);
        ListNode slowNode=dummyNode;
        ListNode fastNode=dummyNode;
        slowNode.next=head;
        while(fastNode.next!=null && fastNode.next.next!=null)
        {
            fastNode=fastNode.next.next;
            slowNode=slowNode.next;
            if(fastNode==slowNode) return true;
        }
        return false;
    }
```

[142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

这道题是上一道题的变形，如果我们知道有一个环，那么什么时候才是这个环的入口呢？

这个时候就需要建立一些状态的表达式：

<img src="https://assets.leetcode-cn.com/solution-static/142/142_fig1.png" alt="fig1" style="zoom: 33%;" />

**第一次相遇时：**

首先看这个环：用快慢指针，slow，fast，速度是1:2。那么它们相遇的时候，slow=k，fast一定走了2k步。

并且，fast一定比slow多走了n圈；fast-slow=k=(b+c)n;

**这个时候我们分析一个情况：**

那么如果从a出发，走a+(b+c)n步,是不是一定还在a点。

而且对于slow来说，slow=k=(b+c)n，那么slow是不是再走a步也可以达到a点。

**第二次相遇**

我们根据上述情况，从第一次相遇时开始，一个指针从头开始走，走a步，另一个指针从相遇点以同样的速度开始走，走a步。那么它们一定能在环的入口处相遇，相遇点即所求。

```java
    public ListNode detectCycle(ListNode head) {
        ListNode dummyNode=new ListNode(0);
        ListNode slowNode=dummyNode;
        ListNode fastNode=dummyNode;
        slowNode.next=head;
        while(fastNode.next!=null && fastNode.next.next!=null)
        {
            fastNode=fastNode.next.next;
            slowNode=slowNode.next;
            if(fastNode==slowNode){//当第一次相遇
                fastNode=head;
                slowNode=slowNode.next;//必须先走一步
                while (fastNode!=slowNode){
                    slowNode=slowNode.next;
                    fastNode=fastNode.next;
                }
                return fastNode;
            }
        }
        return null;
    }
```

[143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

第一种用递归，我觉得不是很好写，因为递归就是一个从下返回上的过程。

第二种就是，先用快慢指针找到中结点，断开为前后两个链表，把后链表反转；再与前列表合并。

时间复杂度O(n)，空间复杂度O(1)

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode prevHead=head;
        if(head!=null&&head.next!=null&&head.next.next!=null){
            ListNode middleNode=findMiddleNode(head);
            ListNode reveredHead=reverseList(middleNode.next);
            middleNode.next=null;
            ListNode temp1;
            ListNode temp2;
            while (prevHead!=null&&reveredHead!=null){
                temp1=prevHead.next;
                temp2=reveredHead.next;

                prevHead.next=reveredHead;
                reveredHead.next=temp1;

                prevHead=temp1;
                reveredHead=temp2;
            }
        }
    }

    /**
     * 找出中结点,对于偶数个结点，返回中左点
     * @param head
     * @return
     */
    private ListNode findMiddleNode(ListNode head){
        ListNode fastNode=head;
        ListNode slowNode=head;
        while(fastNode.next!=null&&fastNode.next.next!=null){
            fastNode=fastNode.next.next;
            slowNode=slowNode.next;
        }
        return slowNode;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
```

[21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

原本的做法就是利用迭代，但是时间复杂度和空间复杂度都不算优秀。

利用递归，如果在同一条链表的大小在整个排列中连续的话，就直接next,每次向上层返回的是已经排好序的链表的头结点

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(null ==l1) return l2;
        if(null ==l2) return l1;

        if(l1.val<l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next =mergeTwoLists(l1,l2.next);
            return l2;
        }

    }
}
```

[148. 排序链表](https://leetcode-cn.com/problems/sort-list/)

纵观各排序算法，时间复杂度O(nlogn)和O(1)，根据时间复杂度自然想到二分法，先考虑归并排序。

但是归并在数组中的空间复杂度存在一个问题，就是空间复杂度O(n)。如果是递归的话，还有logn的递归空间。

所以一定只能用迭代，并且链表不用单独开辟空间所以空间复杂度就可以实现O(1)。

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/8c47e58b6247676f3ef14e617a4686bc258cc573e36fcf67c1b0712fa7ed1699-Picture2.png" alt="Picture2.png" style="zoom:50%;" />

迭代算法在进行归并的时候，自下而上，先是长度为1的两个子链进行比较，再是长度为2的....直到长度>n/2。

对于数组来说，只需要计算每次的偏移量，传入左边界，右边界，中点的下标就行了。但是对于链表来说则需要一直next找到特定节点，并进行切分，突然后再进行合并。

切分的代码：

```java
    /**
     *从头结点处开始，切一个长度为step的
     * @param head
     * @param step
     * @return
     */
    public ListNode split(ListNode head,int step){
        if(null==head) return null;
        ListNode cur=head;
        for(int i=1;i<step&&cur.next!=null;i++){
            cur=cur.next;
        }
        ListNode right=cur.next;
        cur.next=null;
        return right;
    }
```

合并的代码：

```java
    /**
     * 合并两个链表
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(null ==l1) return l2;
        if(null ==l2) return l1;

        if(l1.val<l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next =mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
```

获取链表长度:

```java
    /**
     * 获取链表长度
     * @param head
     * @return
     */
    private int getLength(ListNode head){
        int length=0;
        while (head!=null){
            length++;
            head=head.next;
        }
        return length;
    }
```

归并的代码：

```java
    public ListNode sortList(ListNode head) {
        ListNode dummyNode=new ListNode(0);
        dummyNode.next=head;
        int len=getLength(head);
        //外循环，按照每次单个子链的长度进行迭代
        for(int step=1;step<len;step*=2){
            //nextlefthead用于存储下一对需要排序拼接的子链对的最左节点
            ListNode nextleftHead=dummyNode.next;
            //prev利用记录上一次合并后的尾结点，用于连接尾链
            ListNode prev=dummyNode;
            while (null != nextleftHead){
                ListNode leftHead=nextleftHead;
                //先找到右子链的起始结点
                ListNode rightHead=split(leftHead,step);
                //再把左右子链断掉
                nextleftHead=split(rightHead,step);
                //再结合左右子链,并连接到上一个结点
                prev.next=mergeTwoLists(leftHead,rightHead);
                while (null!=prev.next)
                    prev=prev.next;
            }
        }
        return dummyNode.next;

    }
```

[24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

有一说一，我觉得这道题实在不配称为中等，虽然逻辑可能有点绕，但真的很好理解。

其实就是，记录前一个结点，然后把当前结点与下一个结点互换，注意互换时的一个next的连接好了

举个例子：

```bash
开局一条链表
1--->2--->3-->4
我们先建立一个虚拟结点，方便我们记录最后的头结点
-1--->1--->2--->3-->4
然后我们想要交换1和2
先把-1和2连接起来，让2变成头结点
-1--->2<--->1 同时2还连接后面    2--->3-->4
然后把1连接上3
-1--->2--->1--->3-->4
```



```java
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode=new ListNode(-1);
        ListNode prev=dummyNode;
        dummyNode.next=head;
        while(prev.next!=null&&prev.next.next!=null){
            ListNode temp=prev.next;
            prev.next = temp.next;
            temp.next = prev.next.next;
            prev.next.next=temp;
            prev=temp;

        }
        return dummyNode.next;
        
    }
```

[61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)

想到旋转就想到环，先构建一个环，只需要在必要的地方断开就实现了题目的要求。

当构建好了一个环，就要考虑在什么地方断掉环，取哪一个结点为头结点。

先考虑k<n的情况：

![image-20201225114758145](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201225114758145.png)



当n>=k，直接进行取余，就得到实际需要移动的次数。

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode cur=head;
        int n=0;
        if(head==null ||head.next ==null || k<1) return head;
        //先连接成环
        n=1;
        while (null !=cur.next) {
            cur = cur.next;
            n++;
        }
        cur.next=head;
        //再重新回到头结点
        cur=head;

        //确定实际需要移动的位置
        k=(k>=n)?k%n:k;
        //n-k是移动到实际的头结点位置，但是需要在上一个结点的地方断开
        for (int step=0;step<n-k-1;step++){
            cur=cur.next;
        }
        head=cur.next;
        cur.next=null;
        return head;

    }
}
```

### 树

#### 二叉树

先介绍二叉树的集中遍历方式：

![](https://i.loli.net/2020/11/16/5Syan7tOmf4LU9F.png)

- 前序遍历顺序是：根节点、左子树、右子树，每个子树的遍历顺序同样满足前序遍历顺序：6,2,0,4,3,5,8,7,9。
- 中序遍历顺序是：左子树、根节点、右子树，每个子树的遍历顺序同样满足中序遍历顺序: 0,2,3,4,5,6,7,8,9.
- 后序遍历顺序是:  左子树、右子树、根节点，每个子树的遍历顺序同样满足后序遍历顺序: 0,3,5,4,2,7,9,8,6
- 宽度优先遍历顺序是：第一层从左到右，第二层从左到右...：６,2,8,0,4,7,9,null,null,3,5,null,null...

[剑指 Offer 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

这道题的关键就是理清楚，每种遍历方式以及他们之间的联系。

而对于中序遍历来说，根节点的左边一定是左子树（如果有），右边一定是右子树。比如对于上面的例子而言，中序遍历顺序是0,2,3,4,5,6,7,8,9。在中序序列中，６的左边的数字都是左子树，６的右边都是右子树。

根据前序和中序，可以依次确定根节点->左子树->右子树。当然，这只是最简单的情况，对于根节点来说，我们很容易确定左子树和右子树的左右边界。

当我们确定了根节点和左右子树之后，就可以在前序当中，寻找左子树当中最靠近根节点的点为左子树的顶点，比如：有上述知0,2,3,4,5是左子树，这些数字在前序中，2是最靠近根节点的点，所以2就是左子树的顶点，在中序中寻找2，2的左边一定是左子树，2的右边到根节点6之前的节点都是右子树，其中6在中序的位置就是左子树的右边界。同理，在右子树7,8,9中，8又是最靠近根节点的，所以8是右子树的顶点。。。。按照此思路可以找出所有的点的位置。

在这次的遍历中，我们以数组边界和根节点来确定了左右子树的左右边界，所以在用递归思想的时候一定要确定每棵子树的边界。

**分治算法解析：**

递推参数： 根节点在前序遍历的索引 root 、子树在中序遍历的左边界 left 、子树在中序遍历的右边界 right ；

终止条件： 当 left > right ，代表已经越过叶节点，此时返回 nullnull ；

递推工作：

1. 建立根节点 node ： 节点值为 preorder[root] ；

2. 划分左右子树： 查找根节点在中序遍历 inorder 中的索引 i ；

3. 构建左右子树： 开启左右子树递归；

   为了提升效率，本文使用哈希表 dic 存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1)

|        | 根节点索引（这里的数字代表前序节点的索引） | 中序遍历左边界（中序索引） | 中序遍历右边界（中序索引） |
| ------ | ------------------------------------------ | -------------------------- | -------------------------- |
| 左子树 | root+1                                     | left(当前最左边的left)     | i-1                        |
| 右子树 | i-left+root+1                              | i+1                        | right                      |

`i - left + root + 1含义为 根节点索引 + 左子树长度 + 1`

返回值： 回溯返回 node ，作为上一层递归中根节点的左 / 右子节点；

```java
class Solution {
    //建立一个hash表，储存中序遍历序列与前序遍历序列的对应
    //key=前序遍历数字 ，value=此数字在中序遍历中的index
    Map<Integer,Integer> map =new HashMap<>();

    int[] preorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder=preorder;
        for(int i=0;i<preorder.length;i++){
            map.put(inorder[i],i);//记录每个数字在中序列表的索引，方便以根节点的值找到其中序的位置
        }
        return recur(0,0,inorder.length-1);
    }
    public TreeNode recur(int root,int left,int right){
        //说明已经没有了子节点
        if(left>right)
            return null;
        TreeNode node=new TreeNode(preorder[root]);
        //取根结点在中序遍历中的位置
        int i=map.get(preorder[root]);
        node.left=recur(root+1,left,i-1);
        node.right=recur(i-left+root+1,i+1,right);
        return node;
    }
}
```

[剑指 Offer 55 - I. 二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/)

有一个结论很重要：**一棵树的深度** 等于 **左子树的深度** 与 **右子树的深度** 中的 **最大值** +1。

这就是递归思路，递归的终止条件就是当前子树的根节点为null；

==其实仔细回想起来，这里面的核心思想就是后序遍历，在递归往回传的过程就是一个后序遍历过程。==

直接上代码：

```java
class Solution {
    public int maxDepth(TreeNode root) {
        return findDepth(root);

    }
    public int findDepth(TreeNode root){
        if(root==null) return 0;
        int left=findDepth(root.left);
        int right=findDepth(root.right);
        return Math.max(left,right)+1;
    }
}
```

[剑指 Offer 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

```
     1
   /   \
  2     2
 / \    / \
3  4 4   3
```

这道题，与之前的题有些不同，以前的题就是在一个节点的左右分支进行递归，比较，都是在同一子树上进行的，但是这道题就是在不同的两个子树上进行递归的，因此递归的参数传的是两个子树根节点。

**递归三部曲**

1. 确定递归函数的参数和返回值

   因为我们要比较的是根节点的两个子树是否是相互翻转的，进而判断这个树是不是对称树，所以要比较的是两个树，参数自然也是左子树节点和右子树节点。

2. 确定终止条件

   要比较两个节点数值相不相同，首先要把两个节点为空的情况弄清楚！否则后面比较数值的时候就会操作空指针了。

   节点为空的情况有：（注意我们比较的其实不是左孩子和右孩子，所以如下我称之为左节点右节点）

   - 左节点为空，右节点不为空，不对称，return false
   - 左不为空，右为空，不对称 return false
   - 左右都为空，对称，返回true
   
   此时已经排除掉了节点为空的情况，那么剩下的就是左右节点不为空：
   
   - 左右都不为空，比较节点数值，不相同就return false
     此时左右节点不为空，且数值也不相同的情况我们也处理了。

3. 确定单层递归的逻辑
   此时才进入单层递归的逻辑，单层递归的逻辑就是处理 右节点都不为空，且数值相同的情况
   - 比较二叉树外侧是否对称：传入的是左节点的左孩子，右节点的右孩子。
   - 比较内测是否对称，传入左节点的右孩子，右节点的左孩子。
   - 如果左右都对称就返回true ，有一侧不对称就返回false 。

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        return recur(root.left,root.right);

    }

    boolean recur(TreeNode leftRoot,TreeNode rightRoot){
        if(leftRoot==null&&rightRoot==null)
            return true;
        else if(leftRoot!=null&&rightRoot==null || leftRoot==null&&rightRoot!=null)
            return false;
        else if(leftRoot.val!=rightRoot.val)
            return false;
        else
            return recur(leftRoot.left,rightRoot.right)&&
                    recur(leftRoot.right,rightRoot.left);
    }
}
```

[226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

这道题，关键就是翻转二字的理解，虽然直观上就是左右子树进行调换，同时要沿着两子子树的方向进行递归，并进行调换，但是仔细一想，如果对每个小的二叉树单元，遍历方式，后序遍历：

比如：

```
    2						  2
   / \  ———>	   / \ 
 4   3					 3   4
```

对每个二叉树单位都进行调换（注意：不是换val，是直接调换TreeNode），就能完成整个二叉树的翻转：

1. 确定递归函数的参数和返回值

   我们只需要对一个节点的左右子树进行翻转，因此，参数就是一个子树就好了，返回的应该也只是一个节点。

2. 确定返回条件： 

   - 如果root为空，直接就返回null，不再进行递归；

   - 如果root不为空，就进行递归调换左右子树；

3. 确定单层递归的逻辑：

   直接对左右子树进行调换，然后返回左右子树调换过后的根节点。

   代码：

   ```java
   class Solution {
       public TreeNode invertTree(TreeNode root) {
           return recur(root);
   
       }
   
       private TreeNode recur(TreeNode root){
           if(root==null)
               return null;
           TreeNode node=root.left;
           root.left=recur(root.right);
           root.right=recur(node);
           return root;
       }
   }
   ```

   [129. 求根到叶子节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)
   
   dfs或者回溯法
   
   ```java
   class Solution {
       int allSum=0;
       public int sumNumbers(TreeNode root) {
           List<Integer> temp= new ArrayList<>();
           if(null == root) return 0;
           backTrack(root,root.val);
           return allSum;
   
       }
   
       /**
        * 回溯
        * @param node
        * @param sum
        */
       private void backTrack(TreeNode node,int sum){
           if(null == node.left && null ==node.right){
               allSum+=sum;
           }
           if(null !=node.left){
               backTrack(node.left, sum*10+node.left.val);
           }
           if(null !=node.right){
               backTrack(node.right,sum*10+node.right.val);
           }
   
       }
   
   }
   ```
   
   

#### 二叉平衡树

[剑指 Offer 55 - II. 平衡二叉树](https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/)

这道题与[剑指 Offer 55 - I. 二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/)是连在一起的，是其的进一步升华，所谓平衡二叉树，就是任意一个节点的左右子树的深度相差不会大于1，因此，当`|left-right|>1`,就可以判断这棵树不是平衡二叉树。不用再继续遍历。

修改一下上面的代码：

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return recur(root)==-1?false:true;
    }

    private int recur(TreeNode root){
        if(root==null) return 0;
        int left=recur(root.left);
        if(left==-1) return -1;
        int right=recur(root.right);
        if(right==-1) return -1;

        if(Math.abs(left-right)>1)
            return -1;

        return Math.max(left,right)+1;

    }
}
```

于是我们的思想就是

#### 二叉搜索树

二叉搜索树：二叉搜索树的特点就是 `左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点`

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

 **[235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)**

这题让求二叉搜索树的最近公共祖先，而二叉搜索树的特点就是左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点，所以这题就好办了，从更节点开始遍历：

- 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找

- 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找

- 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点。

- 在第三种情况中，需要包含一种情况，就是两个节点本身就是根与叶之间的关系，这种情况，`(root.val-p.val)*(root.val-q.val)=0`。

  ```java
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          TreeNode cur=root;
  
          while(cur!=null){
              if((p.val-cur.val)*(q.val-cur.val)<=0){
                  break;
              }
              if((p.val>cur.val)&&(q.val>cur.val))
                  cur=cur.right;
              else
                  cur=cur.left;
          }
          return cur;
  }
  ```

[96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)  

这是典型的二叉树问题，树的问题一般会想到递归，想到递归就应该想到动态规划。

 搜索树的条件：任意一个结点，左子树的值一定小于节点值，右子树的值一定大于节点，因此对于一个节点值i，左子树的取值范围[1~i-1],右子树[i+1,n]
  同时，取值范围为[1,k]的子树个数(长度为k)与\[k+1,2k\](长度为k)的子树的个数是一样的。 因此，可以用一个长度为n的数组G[i]储存长度为i的子树的个数。 同时,对于数的根节点来说，G[n]的个数可由F\[i,n\](以i=1,...,n为根，n为长度的子树个数相加得到)  数分为左子树和右子树，
$$
G[n]=F[1,n]+F[i,n]+...F[n] \\
F[i,n]=G[i-1]*G[n-i]
$$
直接递归

```java
class Solution {                                                      
                                                                      
                                                                      
    public int numTrees(int n) {                                      
        int[] G=new int[n+1];//为什么要n+1个，因为n+1个的最大索引才是n                
        return countG(n,G);                                           
    }                                                                 
    public int countG(int n,int[] G){                                 
        if(n<2) return 1;                                             
        if(G[n]!=0)                                                   
            return G[n];                                              
        for(int i=1;i<=n;i++){                                        
            G[n]+=countG(i-1,G)*countG(n-i,G);                        
        }                                                             
        return G[n];                                                  
    }                                                                                                                                    
}                                                                     
//leetcode submit region end(Prohibit modification and deletion)      
                                                                      
}                                                                                                                                                       
```




## 回溯算法

所谓回溯，实际上就是在穷举的情况上，加了一些剪枝条件，没有剪枝条件的回溯就是一个完整的穷举。回溯并不是一个高效的算法，但是由于很多问题没有其余的方法，所以回溯用得也很多，具体问题包括：

- 组合问题：N个数里面按一定规则找出k个数的集合
- 排列问题：N个数按一定规则全排列，有几种排列方式
- 切割问题：一个字符串按一定规则有img几种切割方式
- 子集问题：一个N个数的集合里有多少符合条件的子集
- 棋盘问题：N皇后，解数独等等

所有的回溯问题，都是树型结构，利用递归进行树的节点的排列或者组合的遍历。递归是其特性。

**回溯模板**

- 回溯函数模板返回值以及参数

  回溯函数的返回值一般都是void值。回溯的入口参数与具体的任务高度相关，一般是约束变量，终止条件，结果等。

- 终止条件

  终止条件一般是搜索到了叶子节点，也就是找到了满足条件的答案，此时，既需要保存答案，同时返回，终止递归。

- 回溯搜索的遍历过程

  for循环就是遍历集合区间，可以理解一个节点有多少个孩子，这个for循环就执行多少次。

  backtracking这里自己调用自己，实现递归。

  ![图片](https://mmbiz.qpic.cn/mmbiz_png/ciaqDnJprwv47tDrlOU9kYmYunXSDicd4XkQdQUp2YZSHbxkbktQdgRUZIfZiabbTP2WGjHDBJlvAzsTfF4aNDS0w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

  大家可以从图中看出**「for循环可以理解是横向遍历，backtracking（递归）就是纵向遍历」**，这样就把这棵树全遍历完了，一般来说，搜索叶子节点就是找的其中一个结果了。

  分析完过程，回溯算法模板框架如下：

  ```java
  void backtracking(参数) {
      if (终止条件) {
          存放结果;
          return;
      }
  
      for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
          处理节点;
          backtracking(路径，选择列表); // 递归
          回溯，撤销处理结果
      }
  }
  ```

  **「这份模板很重要，后面做回溯法的题目都靠它了！」**

  如果从来没有学过回溯算法的录友们，看到这里会有点懵，后面开始讲解具体题目的时候就会好一些了，已经做过回溯法题目的录友，看到这里应该会感同身受了。

  

## 动态规划

- 划分为子问题

- 子问题是总问题的一个模板，或者说最简单的总问题就是子问题

- 确定状态，也就是DP应该存什么东西

- 确定状态转移方程

- 确定DP的填充思路

首先，动态规划问题的一般形式就是求最值。动态规划其实是运筹学的一种最优化方法，只不过在计算机问题上应用比较多，比如说让你求最长递增子序列呀，最小编辑距离呀等等。/*
        if(nums1.length>nums2.length)//取短的数组开始
            return intersect(nums2,nums1);
        Map<Integer,Integer> map =new HashMap<>();//用于记录短数组每个数字出现的次数
        int[] result = new int[nums1.length];
        for(int i:nums1){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        int index=0;
        for(int i:nums2){
            int count=map.getOrDefault(i,0);
            if(count>0) {//当小于0，就不再添加
                result[index++]=i;
                map.put(i,map.get(i)-1);
            }
        }
        return Arrays.copyOfRange(result,0,index);*/
既然是要求最值，核心问题是什么呢？求解动态规划的核心问题是穷举。因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值呗。
动态规划这么简单，就是穷举就完事了？我看到的动态规划问题都很难啊！

首先，动态规划的穷举有点特别，因为这类问题存在「重叠子问题」，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。而且，动态规划问题一定会具备「最优子结构」，才能通过子问题的最值得到原问题的最值。

另外，虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出正确的「状态转移方程」才能正确地穷举。

以上提到的**重叠子问题、最优子结构、状态转移方程**就是动态规划三要素。具体什么意思等会会举例详解，但是在实际的算法问题中，写出状态转移方程是最困难的，这也就是为什么很多朋友觉得动态规划问题困难的原因，我来提供我研究出来的一个思维框架，辅助你思考状态转移方程：
明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义。

### 一维动态规划

[53.最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

拿到这道题，看到关键字，“连续”，一般的思路就是双指针，滑窗，动态规划。先看能不能进行子问题的划分，利用动态规划做，这里不固定的因素两个，一个是位置，一个是长度，那么通常是需要二维动态规划或者在需要确定某一个因素去做动态规划。

从最基本的情况，[-1],确定此序列的最大的连续子序列，那么只有-1,这是最简单的情况；然后看 [-1,2]，就是选出-1，2，（-1，2）最大的，如果按照迭代的方式分解子问题，就是，以-1，2为迭代点，选择一个最大的，以2为迭代点的时候，就是看之前得到的结果，-1对2为迭代点的自序列的作用如何，如果存在负作用，那么就不要，如果是正作用，那就要。最后求所有迭代中最大的。这就是确定位置改变长度的做法。

$dp[i]$表示以i为序列末尾的最大连续子序和。

$dp[i]=s[i]+max(dp[i-1],0)$

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int sum=nums[0];
        int max=sum;
        for(int i=1;i< nums.length;i++){
            sum=sum>0?sum+nums[i]:nums[i];
            max=Math.max(sum,max);
        }
        return max;
    }
}
```

 [120. 三角形最小路径和img](https://leetcode-cn.com/problems/triangle/)

这道题很明显可以用动态规划来做，分解子问题，最简单情况，[[1]]，最小路径和1，[[1],[2,3]]这个时候的最小路径和就是，看上一层的最小的路径和加上本层的值的最小值，就是这一层的最小路径和，注意边界条件，i=j=0,j=0,i==j 时候的情况。

总的状态转移方程：$dp[i][j]=s[i][j]+min(dp[i-1][j],dp[i-1][j-1])$

代码：

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int min=Integer.MAX_VALUE;
        int[][] dp=new int[maxH][maxH];
        for(int i=0;i<maxH;i++){
            for(int j=0;j<=i;j++){
                if(i==0 && j==0){
                    dp[i][j]=triangle.get(i).get(j);
                }
                else if(j==0){
                    dp[i][j]=triangle.get(i).get(j)+dp[i-1][j];
                }
                else if(i==j && i!=0){
                    dp[i][j]=triangle.get(i).get(j)+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=triangle.get(i).get(j)+
                            Math.min(dp[i-1][j],dp[i-1][j-1]);
                }
                if(i==maxH-1){
                    min=Math.min(min,dp[i][j]);
                }
            }

        }
        return min;
    }
}
```

乍一眼，通过了，逻辑上也没什么问题，但是但是但是。。。。我们其实可以发现，这个二维的dp压根就没有用完，只用了一半，因为定义了一个二维数组，但是第i行只有i个值有用。其余倒三角的空间全部浪费。

因此可以尝试一下进行空间的优化：尝试从底层往上面计算。==技巧：可以定义dp的时候多定义一维而减少对边界的判断。==

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int min=Integer.MAX_VALUE;
        int[][] dp=new int[maxH+1][maxH+1];
        for(int i=maxH-1;i>=0;i--)
            for(int j=0;j<=i;j++){
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        return dp[0][0];
    }
}
```

按照这个思路，其实可以发现，每次计算了一次的dp的时候，上层有个数据就会被抛弃掉了，可以利用这个数据的位置来存新的最小路径和$dp[i][j]$。那么只需要存下dp[j]就可以了。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxH=triangle.size();
        int[] dp=new int[maxH+1];
        for(int i=maxH-1;i>=0;i--)
            for(int j=0;j<=i;j++){
                dp[j]=Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        System.out.println(dp[0]);
        return dp[0];
    }
}
```

[300.最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/)

这道题跟最大子序和有点相似，都是寻找以每个点为结尾的最长上升子序列。

对于$dp[i]$来说，要求得它的最长上升子序列的长度，要找到满足$nums[i]>nums[j],j=0,...,i-1$中$dp[j]$最大的，$dp[i]$等于$dp[j]+1$。

代码：

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int length=nums.length;
        int[] dp =new int[length];
        int result=0;
        for(int i=0;i<length;i++) {
            int max=0;
            for (int j = 0; j <=i; j++) {
                if (nums[j] <nums[i]) {zhiqian
                    max = Math.max(max, dp[j]);
                    dp[i]=max;
                }
            }
            dp[i]+=1;
            result = Math.max(result,dp[i]);
        }
        System.out.println(result);
        return result;
    }
}
```

[198.打家劫舍动态规划](https://leetcode-cn.com/problems/house-robber/)

这道最开始的想法是用一个二叉树进行遍历求最大，然后用DP存储。

这道题的最关键的问题是：选择一个房间偷不偷，要看前一个房间偷没有，前一个房间没偷这个房间才能偷。

问题分解，对于n>2个房间时，到第k间房时，有两个选项：

- 偷窃第 k间房屋，那么就不能偷窃第k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 k 间房屋的金额之和。

- 不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。

利用dp[i]存储第i间房时，能偷到的最大价格：$dp[i]=max(dp[i−2]+nums[i],dp[i−1])$

代码：

```java
class Solution {
    public int rob(int[] nums) {
        int length=nums.length;
        int dp[]=new int[length];
        if(length==0){
            return 0;
        }
        else if (length==1){
            return nums[0];
        }
        else if(length==2){
            return Math.max(nums[0],nums[1]);
        }
        dp[0]=nums[0];
        dp[1]=Math.max(dp[0],nums[1]);
        for(int i=2;i<length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];

    }

}
```

[322.零钱兑换](https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/)

更加详细的讲解;https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/bfs-kuang-jia

拿到这个问题。我最先想到的是，像切钢条问题那样，对总额从0开始到二分之一总额，进行左右切分，并且在寻找的过程中，随时查看当前的总额是否能够用一个硬币来表示。

DP[i]表示，总额为i时，最少的硬币数，其中，DP[i]可能为0

状态转移方程为：
$$
DP[s]=min(DP[i]+DP[s-i]),if(DP[i]!=-1  \&\& DP[s-i]!=-1)
$$
代码挺复杂，最终结果不对，不知道为啥。。。。。

看了题解，新的思路：对于总额s来说，寻找一个面值为$c_i $的硬币，$DP[s-c_i]$最小，那么$DP[s]=DP[s-c_i ] +1$，当然，如果没有找到，那就利用递归，再往下寻找，如果最后都没找到，那就证明无法组合。

```java
public class CoinChange {
    public static void main(String[] args) {

        Solution solution = new CoinChange().new Solution();
        System.out.println(solution.coinChange(new int[]{1,2,5},3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount < 1) {
                return 0;
            }
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) {
                return -1;
            }
            if (rem == 0) {
                return 0;
            }
            if (count[rem - 1] != 0) {
                return count[rem - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min) {
                    min = 1 + res;
                }
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }

}
//leetcode submit region end(Prohibit modification and deletion)

}
```



### 二维动态规划

[5.最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

回文子串有一个非常简单的状态转移方程，当前子串是否是回文子串，首先要看去掉首末字符后是否是回文，然后看首末字符是否相等，那么就对每个子串是否是回文进行记录，$dp[i][j]$表示从i到j的子串是否是回文。

$dp[i][j]=dp[i+1][j-1]$&&$s[i]==s[j]$

用备忘录和递归的方法当然很简单，但是利用迭代的方式，要进行一个合理的遍历就是一个需要思考的问题：

这里由于状态转移的条件是一个字符串长度的收缩，应该从短长度扩展到长度，因此利用子串长度进行最外层循环，然后利用子串起始点做为第二层循环：

```java
class Solution {
    public String longestPalindrome(String s) {
        int length=s.length();
        String longestString="";
        //记录从i到j的子串是否是回文子串
        boolean[][] dp =new boolean[length][length];
        if(length<2)//如果长度小于2，直接返回原字符串
            return s;
        for(int l=0;l<length;l++)
            for(int i=0;i<length-l;i++){
                int j=i+l;
                if(l==0){
                    dp[i][j]=true;
                }
                else if(l==1){
                    dp[i][j]=(s.charAt(i)==s.charAt(j));
                }
                else{
                    dp[i][j]=(s.charAt(i)==s.charAt(j)) && dp[i+1][j-1];
                }
                if(dp[i][j] && i+j+1>longestString.length()){
                    longestString=s.substring(i,j+1);
                }
            }
        return longestString;
    }
```

时间复杂度为$n^2$

64. [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

这道题，从明面上看就是一个递归搜索问题，那么基本可以利用动态规划问题了，同时，由于是二维矩阵，最优值也需要用二维矩阵来记录，所以就想到二维动态规划问题。

对于(0,0)到(m,n)，需要考虑向下和向右的方向，按照递归的思路，需要比较向下或者向右的两个方向的最小dp值，那么利用迭代，就应该从最小的dp值的(m,n)向起点(0,0)生长。

转换公式：
$$
dp[i][j]=grid[i][j]+min(dp[i][j+1],dp[i+1][j]),i+1,j+1不得越界，越界时，证明到了最右边或者底端，这时候只有一个方向可以走。
$$
代码：
```java
public class MinimumPathSum{
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        int[][] grid=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid));

    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {

        int m=grid.length;//记录高
        int n=grid[0].length;//记录宽
        int[][] dp =new int[m][n];
        for(int i=m-1;i>=0;i--)
            for(int j=n-1;j>=0;j--) {
                if (j >= n - 1 && i >= m - 1) {
                    dp[i][j] = grid[i][j];
                } else if (j >= n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i >= m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        return dp[0][0];
    }
}
```

## BFS

问题的本质就是让你在一幅「图」中找到从起点start到终点target的最近距离。

[BFS](https://blog.csdn.net/qq_37482202/article/details/89513877)叫做广度优先搜索，DFS叫做深度优先搜索。

BFS按照横向关系进行搜索，DFS按照纵向关系搜索，具体来说，就是一棵树，BFS从头节点开始，按照节点的级别进行搜索，比如头结点下面连接了两个子节点，那么把头节点搜索完了之后，再看第一个子节点和第二个子节点，不管这两个子节点是否还有子节点。DFS就是把一条脉络关系全部走通，再走另一条脉络。

BFS的关键就是要保证级相同的节点是在一起的，且离头节点最近的节点应该放在最前面。因此，BFS常常与队列一起使用，因为队列就是先进先出，永远在最后添加。==利用BFS有个好处就是，根本不用遍历所有的节点，就能找到最短的。==举个例子：从家走到医院有10条公交路线可以到，找换乘次数最小的方案，根据换乘一次，换乘二次...的方案找，看换乘一次，这10条线路分别到了什么地方，如果到了医院就结束，没有到，就看换乘两次能不能到医院。最后，有一个线路，换乘两次就到了，其余换乘3次的情况就不用搜索了。

BFS与DFS相比，就是空间换时间，要存下走过的路和将要走的路。

这里给出利用队列进行BFS进行搜索的模板代码：

```JAVA
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路

    q.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数

    while (q not empty) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            /* 划重点：这里判断是否到达终点 */
            if (cur is target)
                return step;
            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj())
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
        }
        /* 划重点：更新步数在这里 */
        step++;
    }
}
```

**[111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)**

首先明确一下起点 `start` 和终点 `target` 是什么，怎么判断到达了终点？

**显然起点就是** **`root`** **根节点，终点就是最靠近根节点的那个「叶子节点」嘛**，叶子节点就是两个子节点都是 `null` 的节点。

具体思路也是一层一层的搜索，如果发现有一个节点是target，也就是有一个节点是叶节点，那证明当前深度就是最小深度。

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=1; //记录第一层

        while (!queue.isEmpty()){
            int sz= queue.size();//计算深度相同的节点的个数
            for(int i=0;i<sz;i++){
                //取出队列最前一个节点，取完深度相同的节点
                TreeNode cur = queue.poll();
                if(cur.left==null && cur.right==null)//到达了终点
                    return depth;
                if(cur.left!=null)
                    queue.offer(cur.left);
                if(cur.right!=null)
                    queue.offer(cur.right);
            }
            //这一层找完了，都没有到target，要找下一层
            depth++;
        }
        return depth;
    }
}
```

 **[752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)**

这道题，一拿到感觉很复杂的情况，有四个转盘，每个转盘转一次有2种情况。但实际上，我们只需要分析转盘拨动一次会产生的情况，比如对于0000来说，转动一次，只能产生8种情况：1000,9000,0100,0900,0010,00900,0001,0009。

https://github.com/UESTC-Liuxin/SkmtSeg.git如果不考虑死亡数字的情况，直接套用模板：

```java
//向上拨动
public String upDila(String s,int i){
    char[] chars = s.toCharArray();
    if(chars[i]=='9')
        chars[i]='0';
    else
        //这里不用转成数字再加一，因为ascii码加一是一样的
        chars[i]+=1;
    return new String(chars);
}

//向下拨动
public String downDila(String s,int i){
    char[] chars = s.toCharArray();
    if(chars[i]=='0')
        chars[i]='9';
    else
        chars[i]-=1;
    return new String(chars);

}

//先写出BFS框架
public int bfsFramework(String start,String target){
    //利用一个队列来记录将要搜索的元素
    Queue<String> queue=new LinkedList<String>();
    //利用一个集合来记录已经搜索的元素
    Set<String>   visited  =new HashSet<String>();

    //将起点加入队列
    queue.offer(start);
    visited.add(start);

    int step=0;

    while(!queue.isEmpty()){
        int sz = queue.size();
        //扩散节点
        for(int i=0;i<sz;i++){
            String s=queue.poll();
           // visited.add(s);不能在这儿加，必须要在加queue的时候加，因为在这儿加，queue中有会有重复的
            //判断是否到终点
            if(s.equals(target))
                return step;
            for(int j=0;j<4;j++){
                String up=upDila(s,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);  
                    }
                    String down=downDila(s,j);
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
            }
        }
        step++;
    }//end while
    return -1;
}
```

上述代码，基本完成了寻找0000到target的最短距离的寻找。现在加入死亡数字，死亡数字就是搜索到的这个数字的时候，这个数字被放弃。不去看它的有下一步转移。

```java
    public int openLock(String[] deadends, String target) {

        //利用一个队列来记录将要搜索的元素
        Queue<String> queue=new LinkedList<String>();
        //利用一个集合来记录已经搜索的元素
        Set<String>   visited  =new HashSet<String>();

        Set<String>  deadendsSet =new HashSet<String>(Arrays.asList(deadends));
        //
        //将起点加入队列
        queue.offer("0000");
        visited.add("0000");

        int step=0;

        while(!queue.isEmpty()){
            int sz = queue.size();
            //扩散节点
            for(int i=0;i<sz;i++){
                String s=queue.poll();
                //判断是否在死亡数字
                if(deadendsSet.contains(s))
                    continue;
                //判断是否到终点
                if(s.equals(target))
                    return step;
                for(int j=0;j<4;j++){
                    String up=upDila(s,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down=downDila(s,j); 
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }//end while
        return -1;
    }
```



## 贪心算法

## KMP



## 字符串专题

### 回文子串问题

## 图



# 背包问题特辑

## 0-1背包问题

题目描述：一个小偷面前有一堆（n个）财宝，每个财宝有重量`w`和价值`v`两种属性，而他的背包只能携带一定重量的财宝（Capacity），在已知所有财宝的重量和价值的情况下，如何选取财宝，可以最大限度的利用当前的背包容量，取得最大价值的财宝（或求出能够获取财宝价值的最大值）。

在解决问题之前，为描述方便，首先定义一些变量：**Vi表示第 i 个物品的价值，Wi表示第 i 个物品的体积，定义V(i,j)：当前背包容量 j，前 i 个物品最佳组合对应的价值**，同时背包问题抽象化`（X1，X2，…，Xn，其中 Xi 取0或1，表示第 i 个物品选或不选）`。

1. 建立模型，即求`max(V1X1+V2X2+…+VnXn)`；

2. 寻找约束条件，`W1X1+W2X2+…+WnXn<capacity`；

3. 寻找递推关系式，面对当前商品有两种可能性：

   - **包的容量比该商品体积小，装不下，此时的价值与前i-1个的价值是一样的，即`V(i,j)=V(i-1,j)`；**

   - **还有足够的容量可以装该商品，但装了也不一定达到当前最优价值，所以在装与不装之间选择最优的一个，即`V(i,j)=max｛V(i-1,j)，V(i-1,j-w(i))+v(i)｝`。**

利用迭代法实现的过程就是对V(i,j)填表的过程，首先确定边界条件：首先，j=0的时候就是表明背包的容量为0，那么最大价值直接为0就好了；另外，当选择0个商品的时候，也是直接为0。

状态转移条件：

```java
if(j<weights[i-1])
    f[i][j]=f[i-1][j];
else
    f[i][j]=Math.max(f[i][j-1],f[i-1][j-weights[i-1]]+values[i-1]);
```

完整代码：

```java
class Solution{
    public int knapsack(int[] weights,int[] values,int N,int C){
        //F[i][j]表示在容量为j的前提下，前i个物品中能获得的最大的价值
        int[][] f=new int[N+1][C+1];
        for(int i=1;i<N+1;i++)
            for (int j=1;j<(C+1);j++){
                if(j<weights[i-1])
                    f[i][j]=f[i-1][j];
                else
                    f[i][j]=Math.max(f[i][j-1],f[i-1][j-weights[i-1]]+values[i-1]);
            }
        return f[N][C];
    }
}
```

## 0-1背包的变种

上述的方案有一个缺陷，虽然知道了怎么获得最大的价值，但是组合的具体形式是还是无法得到。如果要得到具体的组合形式，就需要利用回溯法。



# 排序算法特辑

![img](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/041642093368896.jpg)



## 直接插入

## 希尔排序

## 直接选择

## 堆排序

## 交换排序

## 归并排序

归并排序的思想就是，把一个数组不停的左右二分，直到只有两个元素的时候，归并成一个有序列表，再两两归并起来。

具体的示意如下：

![这里写图片描述](https://img-blog.csdn.net/20160718104152455)

![这里写图片描述](https://img-blog.csdn.net/20160718134747571)

归并排序有两种做法，一种是递归，一种是迭代法。

- 递归法

  递归法主要有两个函数构成：

  二分排序函数：

  ```java
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
  
  ```

  合并排序：

  ```java
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
  ```

  总的代码：
  
  ```java
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
  ```
  
- 迭代法，消除递归带来的logn的递归空间

  递归的思路是自上而下（从整个数组出发，进行二分），那么迭代的思路就是自下而上（从相邻的两个数字先排序，再修改排序长度进行排序），也就是直接进行递归反弹的过程。

  具体代码：

  ```java
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
  ```

  

## 基数排序



# 附录（刷题时间表）

| 题号-题目                                                    | 首刷时间       | 最后刷时间  | Tag                      | 次数 |
| :----------------------------------------------------------- | :------------- | :---------- | ------------------------ | :--- |
| [5.最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/) | 2020/10/1之前  | 2020/11/3   | 动态规划                 | 2    |
| [53.最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | 2020/10/1之前  | 2020/11/3   | 动态规划                 | 2    |
| [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/) | 2020/11/4      | 2020/11/4   | 动态规划                 | 1    |
| [198.打家劫舍](https://leetcode-cn.com/problems/house-robber/) | 2020/11/8      | 2020/11/8   | 动态规划                 | 1    |
| [322.零钱兑换](https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/) | 2020/11/10     | 2020/11/10  | 动态规划                 | 1    |
| [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | 2020/11/11     | 2021/1/5    | BFS                      | 1    |
| [752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/) | 2020/11/12     | 2020/11/12  | BFS                      | 1    |
| [235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | 2020/11/12     | 2020/11/12  | 二叉搜索树               | 1    |
| [17.电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | 2020/10/1之前  | 2020/11/13  | BFS                      | 2    |
| [402. 移掉K位数字](https://leetcode-cn.com/problems/remove-k-digits/) | 2020/10/15     | 2020/10/15  | BFS                      | 1    |
| [剑指 Offer 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/) | 2020/11/17     | 2020/11/17  | 树                       | 1    |
| [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/) | 2020/10/17之前 | 2020/11/17  | 二叉搜索树               | 1    |
| [剑指 Offer 55 - I. 二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/) | 2020/11/18     | 2020/11/18  | 树，递归，DFS            | 1    |
| [剑指 Offer 55 - II. 平衡二叉树](https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/) | 2020/11/18     | 2020/11/18  | 树，递归，DFS            | 1    |
| [剑指 Offer 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/) | 2020/11/19     | 2020/11/19  | 树，递归                 | 1    |
| [剑指 Offer 32 - III. 从上到下打印二叉树 III](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/) | 2020/11/20     | 2020/11/20  | BFS                      | 1    |
| [剑指 Offer 32 - II. 从上到下打印二叉树 II](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/) | 2020/11/20     | 2020/11/20  | BFS                      | 1    |
| [剑指 Offer 04. 二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/) | 2020/11/22     | 2020/11/22  | 数组                     | 1    |
| [剑指 Offer 03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/) | 2020/11/22     | 2020/11/22  | 数组                     | 1    |
| 01背包                                                       | 2020/11/25     | 2020/11/25  | 动态规划                 | 1    |
| [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | 2020/12/15     | 2020/12/15  | 数组，二分法             | 1    |
| [27. 移除元素](https://leetcode-cn.com/problems/remove-element/) | 2020/12/15     | 2020/12/15  | 数组，双指针法           | 1    |
| [209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | 2020/12/16     | 2020/12/16  | 数组，双指针法，二分法   | 1    |
| [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/) | 2020/12/16     | 2020/12/16  | 数组，循环，模拟         | 1    |
| [59. 螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/) | 2020/12/16     | 2020/12/16  | 数组，循环，模拟         | 1    |
| [26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | 2020/12/17     | 2020/12/17  | 数组，快慢指针           | 1    |
| [31. 下一个排列](https://leetcode-cn.com/problems/next-permutation/) | 2020/12/17     | 2020/12/17  | 数组，模拟               | 1    |
| [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/) | 2020/12/18     | 2020/12/18  | 数组，快慢指针           | 1    |
| [383. 赎金信](https://leetcode-cn.com/problems/ransom-note/) | 2020/12/18     | 2020/12/18  | 数组，哈希计数           | 1    |
| [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/) | 2020/12/18     | 2020/12/18  | 数组，回溯法             | 1    |
| [203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) | 2020/12/19     | 2020/12/19  | 链表                     | 1    |
| [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) | 2020/12/19     | 2020/12/19  | 链表                     | 1    |
| [707. 设计链表](https://leetcode-cn.com/problems/design-linked-list/) | 2020/12/20     | 2020/12/20  | 链表                     | 1    |
| [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | 2020/12/20     | 12020/12/20 | 链表，快慢指针           | 1    |
| [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | 2020/12/20     | 2020/12/20  | 链表，快慢指针           | 1    |
| [143. 重排链表](https://leetcode-cn.com/problems/reorder-list/) | 2020/12/21     | 2020/12/21  | 链表，快慢指针，翻转列表 | 1    |
| 归并排序（递归）                                             | 2020/12/21     | 2020/12/21  | 数组，排序               | 1    |
| 递归排序（迭代）                                             | 2020/12/22     | 2020/12/22  | 数组，排序               | 1    |
| [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | 2020/12/23     | 2020/12/23  | 链表，递归               | 1    |
| [148. 排序链表](https://leetcode-cn.com/problems/sort-list/) | 2020/12/23     | 2020/12/23  | 链表，归并排序，快慢指针 | 1    |
| [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) | 2020/12/24     | 2020/12/24  | 链表                     | 1    |
| [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/) | 2020/12/24     | 2020/12/24  | 链表                     | 1    |
| [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) | 2020/12/24     | 2020/12/24  | 链表                     | 1    |
| [202. 快乐数](https://leetcode-cn.com/problems/happy-number/) | 2020/12/25     | 2020/12/25  | 数组，快慢指针（哈希表） | 1    |
| [349. 两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/) | 2020/12/25     | 2020/12/25  | 数组，哈希表             | 1    |
| [350. 两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/) | 2020/12/25     | 2020/12/25  | 数组，哈希表             | 1    |
| [129. 求根到叶子节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/) | 2020/12/27     | 2020/12/27  | 二叉树，dfs              | 1    |
| [110. 平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/) | 2020/12/27     | 2020/12/27  | 平衡二叉树               | 1    |
| [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/) | 2020/12/27     | 2020/12/27  | 二叉树的前序遍历         | 1    |
| [145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/) | 2020/12/27     | 2020/12/27  | 二叉树的后序遍历         | 1    |
| [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | 2020/12/27     | 2020/12/27  | bfs                      | 1    |
| [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/) | 2020/12/27     | 2020/12/27  | 动态规划                 | 1    |
| [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/) | 2020/12/28     | 2020/12/28  | 双指针                   | 1    |
| [541. 反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/) | 2020/12/28     | 2020/12/28  | 双指针                   | 1    |
| 剑指offer05                                                  | 2020/12/28     | 2020/12/28  | 太简单  啥都没用         | 1    |
| 斐波那契数列                                                 | 2021/1/4       | 2021/1/4    | 斐波那契数列             | 1    |
| [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | 2021/1/4       | 2021/1/4    | 中序遍历                 | 1    |
| [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/) | 2021/1/4       | 2021/1/4    | 利用二叉搜索树的中序遍历 | 1    |
| [830 较大分组的位置](https://leetcode-cn.com/problems/positions-of-large-groups/) | 2021/1/5       | 2021/1/5    | 双指针                   | 1    |
| [ 77 组合](https://leetcode-cn.com/problems/combinations/)   | 2021/1/5       | 2021/1/5    | 回溯                     | 1    |
| [216 组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/) | 2021/1/5       | 2021/1/5    | 回溯                     | 1    |
| [111 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | 2021/1/6       | 2021/1/6    | 树，递归                 | 1    |
| [131 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/) | 2021/1/6       | 2021/1/6    | 回溯，动态规划           | 1    |
| [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/) | 2021/1/7       | 2021/1/7    | 头插法，双指针           | 1    |

