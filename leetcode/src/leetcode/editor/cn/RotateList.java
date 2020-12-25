package leetcode.editor.cn;
//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针 
// 👍 381 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;

public class RotateList {
    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
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
//leetcode submit region end(Prohibit modification and deletion)

}
