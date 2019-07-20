package avachat.leetcode.easy;

public class Prob203RemoveLinkedListElements {

    /*

Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5

     */




    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeElements(ListNode head, int val) {

        /*
        GOOD : First try right
        Bad : Copy paste error :-(
         */

        while ( (null != head) && (head.val == val) ) {
            head = head.next;
        }

        if (null == head) {
            return null;
        }

        // now head val is not to be removed
        ListNode current = head;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;

    }


}
