package avachat.leetcode.easy;

public class Prob237DeleteNodeInALinkedList {

    /*

    Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:





Example 1:

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.


Note:

The linked list will have at least two elements.
All of the nodes' values will be unique.
The given node will not be the tail and it will always be a valid node of the linked list.
Do not return anything from your function.


     */



    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public void deleteNode(ListNode node) {


        /*
          This is kind of a trick question. I do not like it, even when I solved it in few minutes.

          The node is NOT getting deleted. The value is getting removed.
          We are just shifting the contents and deleting the last node.
         */


        // ASSUMPTION : There are at least 2 nodes in the list
        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }

        // the above loop stops at the place where last 2 nodes are left
        node.val = node.next.val;
        node.next = null;

    }


}
