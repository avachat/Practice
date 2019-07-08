package avachat.leetcode.medium;

public class Prob0002AddTwoNumbers {

    /*

    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.


     */


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        /*

        GOOD : Got it right without any errors on first try

        BAD :  Not much, but this is not the fastest impl. Don't know why.


         */



        // lists are guaranteed to be non-empty

        // find first digit
        int sum = l1.val + l2.val;
        int digit = sum % 10;
        int carryover = sum / 10;

        // we need to append the nodes to the list at the end
        // resultTail keeps track of the last node
        ListNode resultTail = new ListNode(digit);
        // But the return value is the start of list : the least significant digit
        // so we need to always know where the list starts
        ListNode resultListHead = resultTail;

        // start iterating from next digit
        l1 = l1.next;
        l2 = l2.next;

        while ( (l1 != null) && (l2 != null) ) {

            sum = l1.val + l2.val + carryover; // don't forget the carryover
            digit = sum % 10;
            carryover = sum / 10;

            // create new node
            ListNode result = new ListNode(digit);
            // add to the end of the list
            resultTail.next = result;
            resultTail = result;

            l1 = l1.next;
            l2 = l2.next;
        }

        // now at least one list is null
        // need to process the remaining items from the list
        ListNode list = (l1 == null) ? l2 : l1;

        while (list != null) {

            sum =  list.val + carryover; // don't forget the carryover
            digit = sum % 10;
            carryover = sum / 10;

            // create new node
            ListNode result = new ListNode(digit);
            // add to the end of the list
            resultTail.next = result;
            resultTail = result;

            list = list.next;
        }

        // there still may be carryover
        if (carryover > 0) {
            ListNode result = new ListNode(carryover);
            resultTail.next = result;
            resultTail = result;
        }

        return resultListHead;

    }

}
