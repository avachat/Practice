package avachat.leetcode.easy;

public class Prob783MinDistanceBSTNodes {

    /*

    Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \
    1   3

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
Note:

The size of the BST will be between 2 and 100.
The BST is always valid, each node's value is an integer, and each node's value is different.


     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int minDiff = Integer.MAX_VALUE;
    private int prevNum;
    private boolean started = false;

    public int minDiffInBST(TreeNode root) {

        updateDiffAndPrevNumInOrder(root);
        return minDiff;
    }


    private void updateDiffAndPrevNumInOrder(TreeNode root) {

        /*

        GOOD : Much simpler code than previous

        BAD : Still took 2nd attempt to get it right

         */

        if (root.left != null) {
            updateDiffAndPrevNumInOrder(root.left);
        }

        if (!started) {
            minDiff = Integer.MAX_VALUE;
            started = true;
        } else {
            minDiff = Math.min(minDiff, root.val - prevNum);
        }

        prevNum = root.val;

        if (root.right != null) {
            updateDiffAndPrevNumInOrder(root.right);
        }

    }







    public int minDiffInBST_BAD_IMPL(TreeNode root) {

        /*

        GOOD : Faster and smaller than 100%

        BAD : Took a LONG time to get it right

         */
        // init min - max to the root val
        int [] myResult = new int[] {root.val, root.val};
        findMinMaxDiff(root, myResult);

        return this.minDiff;
    }


    private void findMinMaxDiff (TreeNode root, int[] resultForParent) {

        if (root.left != null) {
            int [] myResult = new int[] {root.val, root.val};
            findMinMaxDiff(root.left, myResult);
            // for the left child take difference only with the max
            int diff = Math.abs(root.val - myResult[1]);
            this.minDiff = Math.min(this.minDiff, diff);
            // update the new min for parent
            resultForParent[0] = myResult[0];
        } else {
            resultForParent[0] =  root.val;
        }

        if (root.right != null) {
            int [] myResult = new int[] {root.val, root.val};
            findMinMaxDiff(root.right, myResult);
            // for the right child take difference only with the min
            int diff = Math.abs(root.val - myResult[0]);
            this.minDiff = Math.min(this.minDiff, diff);
            // update the new min for parent
            resultForParent[1] =  myResult[1];
        } else {
            resultForParent[1] =  root.val;
        }

    }


}
