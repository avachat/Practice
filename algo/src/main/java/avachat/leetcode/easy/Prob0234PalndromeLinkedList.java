package avachat.leetcode.easy;

public class Prob0234PalndromeLinkedList {

    /*


    Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?



     */




    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public boolean isPalindrome(ListNode head) {


        /*

        GOOD : Once the algorithm to find the half way point was found - implemented in one shot

        BAD : Original idea was to traverse the list and find the length first.
                The idea of slow and fast pointers, had to be researched.

         */


        if (null == head) {
            return true;
        }

        if (head.next == null) {
            return true;
        }

        // traverse and stop at half
        // list has at least 2 nodes

        ListNode slow = head;
        ListNode fast = head;

        while ( (null != fast) && (null != fast.next) ) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // for even sized list, fast will become null
            // at that time slow will point to the node exactly where half the list is
            // and that's where reversal should begin
        // for odd sized lists, slow will be at the midpoint node
            // then the reversal should begin with the next node

        if (null != fast) {
            slow = slow.next;
        }

        // now reverse the list starting from slow, such that the last node of that list has next == null
        ListNode reversed = null;
        ListNode current = slow;
        while (current != null) {
            ListNode temp = current.next;
            current.next = reversed;
            reversed = current;
            current = temp;
        }

        // now the reversed list is a standalone list, with it's last node pointing to null
        // For original even sized list, reversed will have exactly the half the size of the original list
        // For original odd sized lists, reversed will have one less than (original lst minus the nodes of reversed)
        // So ----
        // In both the cases, start with the reversed list, anc compare with head
        // Stop when reversed list is done
        // Original list may still have an extra node that's not compared - and that's fine for palindromes
        while (reversed != null) {
            if (reversed.val != head.val) {
                return false;
            }
            reversed = reversed.next;
            head = head.next;
        }

        return true;

    }

}
