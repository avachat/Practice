package avachat.leetcode.easy;

public class Prob0530MinDiffBST {

    /*

    530. Minimum Absolute Difference in BST
Easy

525

35

Favorite

Share
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


Note: There are at least two nodes in this BST.


     */



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean started = false;
    private int prev; // initialization doesn't matter
    private int maxDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {

        /*

        GOOD : Was able to remember to use inorder traversal as sorted array
                Got the algo quickly (only need to compare with prev value)
                Faster than 95%

        BAD : Made a stupid mistake in the if condition
            NOTE : Even if started is false, we need to run inorder traversal on the right side, cannot just return
            See the test case. There is NO left tree at all

         */

        getMinimumDifferenceInternal(root);
        return maxDiff;
    }

    public void getMinimumDifferenceInternal(TreeNode root) {

        if (root == null) {
            return;
        }

        getMinimumDifferenceInternal(root.left);

        if (!started) {
            started = true;
        } else {
            int diff = root.val - prev;
            if (diff < maxDiff) {
                maxDiff = diff;
            }
        }

        prev = root.val;

        getMinimumDifferenceInternal(root.right);
    }

}
