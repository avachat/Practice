package avachat.leetcode.easy;

public class Prob111MinDepthBinaryTree {

    /*
    Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
     */


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {

        /*
         * BAD : Missed understanding the problem completely : The min depth must include leaf nodes
         */

        if (root == null) {
            return 0;
        }

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        if ( leftMinDepth == 0 ) {
            return rightMinDepth + 1;
        } else if (rightMinDepth == 0) {
            return leftMinDepth + 1;
        } else {
            int minChildDepth = (leftMinDepth < rightMinDepth) ? leftMinDepth : rightMinDepth;
            return minChildDepth + 1;
        }
    }
}
