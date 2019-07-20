package avachat.leetcode.easy;

public class Prob671SecondMinimumBinaryTree {

    /*

    Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.


Example 2:

Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int findSecondMinimumValue(TreeNode root) {

        /*
        GOOD : Eventually got the code to beat 100% on both time and space

        BAD :
            Could not figure out the tree structure without the whiteboard
                - that the second minimum can exist deep inside the tree\
                - see the examples
                - when both children are equal - lower levels of the tree must be inspected
         */


        /*
        NOTE : This is a weird tree structure. The parent val is a copy of one of the children.

        So the minimum is definitely the root.
        The second minimum can be either the higher child, or it can exist on the side of the lower child.
        See examples :

                   1
                /    \
              1       100
            /  \     /   \
          1   50    100  200
         / \
        1  25


                   1
                /     \
              1        100
            /  \     /    \
          1    1    100   200
         / \       /   \
        1   1     100  150


                     1
                /         \
                1            100
            /    \         /    \
          1      1        100   200
         / \    / \        /   \
        1   1   5  1       100  150





        This means there is no need to look for the second minimum deeper on the side of the higher child.
         */

        if ( root.left == null) {
            // both children are null
            return -1;
        }

        if ( root.left.val == root.right.val ) {
            int secondMinLeft = findSecondMinimumValue(root.left);
            if (secondMinLeft == -1) {
                return findSecondMinimumValue(root.right);
            }
            int secondMinRight = findSecondMinimumValue(root.right);
            if (secondMinRight == -1) {
                return secondMinLeft;
            }
            return Math.min(secondMinLeft, secondMinRight);
        }


        // Now we know the root has both children and their values are different
        // There are two candidates to consider
        // The second minimum of the side of the tree that has lower value child
            // and the higher value child

        TreeNode lower = null;
        TreeNode higher = null;
        if (root.left.val < root.right.val) {
            lower = root.left;
            higher = root.right;
        } else {
            lower = root.right;
            higher = root.left;
        }

        int secondMinOnLower = findSecondMinimumValue(lower);

        if (secondMinOnLower == -1) {
            // no second min exists on the lower side
            // should try on the right side
            return higher.val;
        } else {
            return Math.min(secondMinOnLower, higher.val);
        }

    }

}
