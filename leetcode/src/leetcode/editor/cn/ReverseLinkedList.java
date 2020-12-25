package leetcode.editor.cn;
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1404 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode dummyNode;//声明拟头结点，方便函数内赋值
    public ListNode reverseList(ListNode head) {
        this.dummyNode =new ListNode(0);//建立新的虚拟头结点
        if(head==null) return null;
        recurAdd(head);
        head.next=null;
        return this.dummyNode.next;
    }

    /**递归思路
     * 原本的链表序
     * head--->node1--->node2--->node3--->node4--->null
     * 到了尾结点，把尾结点赋给dummyNode.next，并返回node4.next,这个时候node4还指向的是null
     * head--->node1--->node2--->node3--->node4(dummyNode.next)--->null
     * 返回上一层递归后，要将返回的node4.next重新指向node3,这个时候node3和node4相互连接
     * head--->node1--->node2--->node3<--->node4(dummyNode.next)
     * 再返回上一层时，node3.next又改为指向node2...
     * head--->node1--->node2<--->node3<---node4(dummyNode.next)
     * .....
     * 到了最后返回的时候的指向情况
     * head<--->node1--->node2<---node3<---node4(dummyNode.next)
     * 这个时候还不能直接返回，需要把head.next指向null才行
     * @param searchNode
     * @return
     */
    public ListNode recurAdd(ListNode searchNode){
        if(searchNode.next==null){//触底反弹
            dummyNode.next=searchNode;
            return dummyNode.next;
        }
        ListNode cur=recurAdd(searchNode.next);//返回的是新链表序的最后一个节点的地址
        cur.next=searchNode;//把此链表的下一个节点重新赋值为当前节点
        return cur.next; //返回下一个节点
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
