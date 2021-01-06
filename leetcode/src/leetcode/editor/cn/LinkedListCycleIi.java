package leetcode.editor.cn;
//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 链表 双指针 
// 👍 795 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;
import leetcode.editor.cn.utils.listNode.ListNodeUitls;

public class LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();
        ListNode head=ListNodeUitls.createSingleListNode(new  int[]{3,2,0,-4});
        head.next.next.next.next=head.next;
        solution.detectCycle(head);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
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
                fastNode=dummyNode;//记住，第一次从哪儿开始的，就要从哪儿结束，不然永远会有一个位置的偏差。
                while (fastNode!=slowNode){
                    slowNode=slowNode.next;
                    fastNode=fastNode.next;
                }
                return fastNode;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}