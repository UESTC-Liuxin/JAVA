package leetcode.editor.cn;
//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。 
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表 
// 👍 624 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;

public class ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next ==null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode g = dummyNode;
        ListNode p = dummyNode.next;

        int index =1;
        while (index<m){
            g=g.next;
            p=p.next;
            index++;
        }
        //现在p指向了需要反转的第一个节点
        for(int i=0;i<n-m;i++){
            ListNode removeNode = p.next;
            p.next = p.next.next;
            //
            removeNode.next =g.next;
            g.next = removeNode;
        }
        return dummyNode.next;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
