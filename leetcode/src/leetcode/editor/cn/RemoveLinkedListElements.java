package leetcode.editor.cn;

//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 501 👎 0

import leetcode.editor.cn.utils.listNode.ListNode;
import leetcode.editor.cn.utils.listNode.ListNodeUitls;

public class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();

        int[] arr=new int[]{1,2,6,3,4,5,6};
        ListNode pre= ListNodeUitls.createSingleListNode(arr);
        ListNode head=solution.removeElements(pre,6);
        ListNodeUitls.printListNode(head);
    }
    //leetcode submit region begin(Prohibit modification and deletion)




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
//leetcode submit region end(Prohibit modification and deletion)

}
