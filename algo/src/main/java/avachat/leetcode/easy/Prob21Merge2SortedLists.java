package avachat.leetcode.easy;


/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */

public class Prob21Merge2SortedLists {

    /*
     * Good ran without test cases.
     * Bad : For the first submission, forgot to write the second loop.
     */


    /**
     * Definition for singly-linked list.
     * */
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode result = null;
        ListNode last = null;

        while ( (null!=list1) && (null!= list2)) {
            ListNode listNode = null;
            if (list1.val < list2.val) {
                listNode = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                listNode = new ListNode(list2.val);
                list2 = list2.next;
            }
            if ( result == null ) {
                result = listNode;
                last = listNode;
            } else  {
                last.next = listNode;
                last = listNode;
            }
        }

        ListNode remaining = (list1 == null) ? list2 : list1;

        while (remaining != null) {
            ListNode listNode = new ListNode(remaining.val);
            if ( result == null ) {
                result = listNode;
                last = listNode;
            } else  {
                last.next = listNode;
                last = listNode;
            }
            remaining = remaining.next;

        }

        return result;
    }

}
