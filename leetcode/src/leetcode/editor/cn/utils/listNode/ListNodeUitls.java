package leetcode.editor.cn.utils.listNode;

public class ListNodeUitls {
    public static void printListNode(ListNode head){
        ListNode cur=head;
        while (cur!=null){
            System.out.print(String.valueOf(cur.val)+' ');
            cur=cur.next;
        }
        System.out.println();
    }

    public static ListNode createSingleListNode(int[] arr){
        ListNode dummyHead=new ListNode(0);
        ListNode cur=dummyHead;
        for (int i=0;i<arr.length;i++){
            cur.next=new ListNode(arr[i]);
            cur=cur.next;
        }
        return dummyHead.next;
    }
}
