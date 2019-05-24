package avachat.leetcode.easy;

public class Prob226InvertBinaryTree {

    /*
    Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
     */


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode invertTree(TreeNode root) {

        /*
        Good : got it quickly, almost in one shot
        Bad : Typing errors
         */

        if (root == null) {
            return null;
        }

        TreeNode newRight = invertTree(root.left);
        TreeNode newLeft = invertTree(root.right);

        root.right = newRight;
        root.left = newLeft;

        return root;
    }


}
