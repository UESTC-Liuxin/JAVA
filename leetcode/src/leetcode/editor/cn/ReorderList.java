package leetcode.editor.cn;
//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 478 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;
import leetcode.editor.cn.utils.listNode.ListNodeUitls;

public class ReorderList {
    public static void main(String[] args) {
        Solution solution = new ReorderList().new Solution();
        ListNode head=ListNodeUitls.createSingleListNode(new int[]{1,2,3,4});
        solution.reorderList(head);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
//leetcode submit region end(Prohibit modification and deletion)

}
