package avachat.leetcode.easy;

public class Prob0876MiddleOfLinkedList {


    /*

    Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.



Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.


Note:

The number of nodes in the given list will be between 1 and 100.

     */

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public ListNode middleNode(ListNode head) {


        /*

         GOOD : Fastest, smallest - better than 100% for time and space

         BAD : Took a LOOOONG time to imagine the correct terminating condition and how to write the loop
            See the incorrect version

         */



        // move the slow pointer once, and the fast pointer twice

        ListNode slow = head;
        ListNode fast = head;

        // This is how the points move
        // While condition fails at length : new values of fast slow
        // -- : 0 0 OR 1 1 : init
        // 0,1 : { 3, 2 }
        // 2,3 : { 5, 3 }
        // 4,5 : { 7, 4 }
        // 6,7 : { 9, 5}
        // 8,9 : { 11, 6}
        // For a length value of slow at previous line is returned

        while ( (fast != null) && (fast.next != null)) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;


        /*


        WRONG : Following (first code) is WRONG :-( :-(


        // This is how the pointers move
        // fast slow
        // 1 1 - init
        // 2 2 - null check - 3 2
        // 4 3 - null check - 5 3
        // 6 4 - null check - 7 4
        // 8 5 - null check - 9 5
        // 10 - 6 null check - 11 6

        while (fast != null) {

            fast = fast.next;
            slow = slow.next;

            if (null == fast) {
                return slow;
            }

            fast = fast.next;
        }

        return slow;


        */

    }


}
