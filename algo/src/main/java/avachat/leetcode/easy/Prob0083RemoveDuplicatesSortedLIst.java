package avachat.leetcode.easy;

public class Prob0083RemoveDuplicatesSortedLIst {

    /*
    Given a sorted linked list, delete all duplicates such that each element appear only once.

    Example 1:

    Input: 1->1->2
    Output: 1->2
    Example 2:

    Input: 1->1->2->3->3
    Output: 1->2->3
    */


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {

        /*
        GOOD : In 5 mins, one time submission.
         */

        if (head == null) {
            return null;
        }

        ListNode current = head;

        while (current.next != null) {

            int currentVal = current.val;
            int nextVal = current.next.val;

            if ( currentVal != nextVal) {
                // advance to the next node
                current = current.next;
                continue;
            }

            // same value - delete the next node
            current.next = current.next.next;
        }

        return head;
    }

}
