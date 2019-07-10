package avachat.leetcode.medium;

public class Prob0024SwapNodesInPairs {

    /*

    Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.



Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.

     */



    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {

        /*

        GOOD : Got it quickly, and better space/time than 100%

        BAD : Missed a null pointer in the loop - had to change the while condition

         */

        if (head == null) {
            return null;
        }

        // create a dummy node to point to the head for convenience
        // this makes it easy to remember where the head should be
        // and takes care of edge cases for lists with 1, 2 nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode zero = dummy; // init zero to the node previous to first
        ListNode first = head; // init first to head : note that head is not null here

        // there must be at least 2 more nodes remaining for thw swap to happen
        while ( (first != null) && (first.next != null)) {

            // specifically create a new pointer for convenience - it's not null inside this loop
            ListNode second = first.next;

            zero.next = second; // make previous node point to second
            first.next = second.next; // make first point to third
            second.next = first; // make second point to first - so swap is complete
            zero = first; // the original first, after swap becomes the zero for the next pair

            first = zero.next; // advance first to
        }

        // the dummy is always the 0th node and it's next points to the real head of the list
        return dummy.next;
    }
}
