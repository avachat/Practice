package avachat.leetcode.easy;

public class Prob0206ReverseLinkedList {

    /*

    Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

     */


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }



    public ListNode reverseList(ListNode head) {


        /*

        GOOD : Got the implementation quickly and passed the first time


        BAD : Could NOT solve reversing with recursion :-( :-( Had to look up the solution.


         */


        if (head == null) {
            return null;
        }
        return reverseListIterative(head);
        //return reverseListRecursive(head);

    }


    private ListNode reverseListRecursive(ListNode head) {

        if (head.next == null) {
            return head;
        }

        // reverse the remaining list
        // that's the head of the reversed list
        // and that would be returned result
        ListNode reversedNext = reverseListRecursive(head.next);

        // at this point head is still pointing to what was the original next
        // and that next is now the last element of the reversed list!!!
        // this is where head needs to be attached
        // before this assignment head.next.next would be null
        head.next.next = head; // !!! This the way to attach head to the last
        head.next = null; // since head is the last element it's next should be null - this is why it will null above

        return reversedNext;


    }


    private ListNode reverseListIterative(ListNode head) {

        ListNode reversed = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = reversed;
            reversed = head;
            head = temp;
        }

        return reversed;

    }



}
