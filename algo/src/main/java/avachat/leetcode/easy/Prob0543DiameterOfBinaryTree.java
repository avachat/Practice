package avachat.leetcode.easy;


public class Prob543DiameterOfBinaryTree {

    /*
    Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        /*

        GOOD : Got the algo quickly, wrote code fast, runs fastest > 100%


         */

        if (root == null) {
            return 0;
        }

        diameterOfBinaryTreeInternal(root);
        return maxDiameter;
    }

    /**
     *
     * @param root
     * @return num of edges in the tree (or height)
     */
    private int diameterOfBinaryTreeInternal(TreeNode root) {

        int leftHeight = (root.left == null) ? 0 : diameterOfBinaryTreeInternal(root.left) + 1;
        int rightHeight = (root.right == null) ? 0 : diameterOfBinaryTreeInternal(root.right) + 1;

        int diameter = leftHeight + rightHeight;
        maxDiameter = Math.max(diameter, maxDiameter);

        return Math.max(leftHeight, rightHeight);
    }
}
