package LeetCode.easy.SecWeek;

/**
 * @文件描述： 21 Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 *Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem21 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            if(l1 == null){
                return l2;
            }
            if(l2 == null){
                return l1;
            }
        }

        ListNode headList = new ListNode(0);
        ListNode temp = headList;

        while(l1 != null && l2 != null){
            ListNode newNode;
            if(l1.val <= l2.val){
                newNode = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                newNode = new ListNode(l2.val);
                l2 = l2.next;
            }
            temp.next = newNode;
            temp = temp.next;
        }
        if(l1 != null){
            temp.next = l1;
        }
        if(l2 != null){
            temp.next = l2;
        }
        return headList.next;
    }

    public static void main(String[] args) {

    }

}
