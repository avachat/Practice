package avachat.leetcode.easy;


public class Prob563BinaryTreeTilt {

    /*

    Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.


     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int tilt = 0;

    public int findTilt(TreeNode root) {

        /*
        GOOD : Quickly implemented, correct, fast

         */

        findTiltReturnSum(root);
        return tilt;
    }

    private int findTiltReturnSum(TreeNode root) {

        if (null == root) {
            return 0;
        }

        int leftSum = findTiltReturnSum(root.left);
        int rightSum = findTiltReturnSum(root.right);

        tilt += Math.abs(leftSum - rightSum);

        return root.val + leftSum + rightSum;
    }
}
