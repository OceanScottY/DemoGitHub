package LeetCode.easy.SecWeek;

/**
 * @文件描述：  83. Remove Duplicates from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * @创建者：
 * @创建日期：2019/3/15
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem83 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public static ListNode deleteDuplicates(ListNode head) {
        ListNode pre = head;
        if(head.next == null){
            return head;
        }
        ListNode current = head.next;
        while(current != null){
            if(pre.val == current.val){
                pre.next = current.next;
                current = current.next;
            }else {
                pre = pre.next;
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
