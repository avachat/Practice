package avachat.leetcode.medium;

public class Prob0061RotateList {

    /*

    Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

     */


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {

        /*

        GOOD : Better space time than 100%
            Passed in second attempt

        BAD : Got the k>size check wrong :-(

         */

        // if the list is empty, no rotation needed
        // if the list has size 1, no rotation needed
        // if K == 0, no rotation needed
        if ( (null == head) || (head.next == null) || (k == 0)) {
            return head;
        }

        // strategy
        // have two pointers
        // fast will be K ahead of slow

        // set fast to be K steps ahead
        // if we hit the end of the list
        // then K is bigger than the size, and then we need adjust k to k modulo size
        ListNode fast = head;
        int size = 0;
        int steps = k;
        while ( (steps > 0) && (fast != null)) {
            fast = fast.next;
            size++;
            steps--;
        }

        // If size is less than K, we need to readjust
        if ( fast == null) {
            steps = k % size;
            if (steps == 0) {
                return head; // no rotation needed
            }
            fast = head;
            while (steps > 0) {
                fast = fast.next;
                steps--;
            }
        }

        ListNode slow = head;

        // Now fast is K steps ahead of slow
        // Keep moving till fast.next != null
        // Example : size 9, K = 2
        // Start 1 2 3 4 5 6 7 8 9
        // fast points to 3, slow point to 1
        // Progresses as : 4, 2  5,3  6,  7,5  8,6  9,7
        // At this point slow->7 and fast->9
        // 7 should be the last node
        // 7 (slow) should point to null
        // head should be whatever 7 is pointing to
        // and whatever fast is pointing to should point to whatever is currently head
        //
        //
        // Another exmaple
        // size = 5, K = 4
        // Start 1 2 3 4 5
        // fast points to 5
        // slow points to 1
        // fast.next is null, so we count of the loop
        // slow-> 1  fast->5

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Now do the swaps
        //ListNode tmp = head;
        fast.next = head; // circle back
        head = slow.next; // head points to the node based on rotation
        slow.next = null; // break the circular link

        return head;
    }
}


