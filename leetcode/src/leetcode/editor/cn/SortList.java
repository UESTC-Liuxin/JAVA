package leetcode.editor.cn;
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 930 👎 0


import leetcode.editor.cn.utils.listNode.ListNode;
import leetcode.editor.cn.utils.listNode.ListNodeUitls;

public class SortList {
    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
        ListNode head=ListNodeUitls.createSingleListNode(new int[]{-1,5,3,4,0});
        solution.sortList(head);
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
    public ListNode sortList(ListNode head) {
        ListNode dummyNode=new ListNode(0);
        dummyNode.next=head;
        int len=getLength(head);
        for(int step=1;step<len;step*=2){
            //nextlefthead用于存储下一对需要排序拼接的子链对的最左节点
            ListNode nextleftHead=dummyNode.next;
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
}
//leetcode submit region end(Prohibit modification and deletion)

}
