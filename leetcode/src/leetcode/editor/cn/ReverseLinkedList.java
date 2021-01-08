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
    public ListNode reverseList(ListNode head) {
        if(head ==null || head.next ==null) return head;
        ListNode pre =null;//记录前一个节点
        ListNode cur = head;//记录当前节点
        ListNode next = head.next;//记录下一个节点

        //每次循环都将当前节点的下一个节点指向pre并且往后移
        while(next!=null){
            cur.next = pre;
            pre=cur;
            cur = next;
            next =next.next;
        }
        cur.next=pre;
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
