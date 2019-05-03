package avachat.leetcode.easy;

public class Prop101SymmetricTree {


    /*
    101. Symmetric Tree
Easy

2120

45

Favorite

Share
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3


Note:
Bonus points if you could solve it both recursively and iteratively.


     */



    /*


     GOOD : Implementation quickly done, and correct on first try.
     BAD : Took some time to understand the problem.


     */



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {

        return isSymmetricRecursive(root);
    }

    private boolean isSymmetricRecursive(TreeNode root) {

        if (root == null) {
            return true;
        }

        return isSymmetricRecursiveInternal(root.left, root.right);

    }

    private boolean isSymmetricRecursiveInternal(TreeNode leftward, TreeNode rightward) {

        if ( (leftward == null) || (rightward == null) ) {
            return (leftward == rightward);
        }

        return ( (leftward.val == rightward.val)
                && ( isSymmetricRecursiveInternal(leftward.left, rightward.right))
                && ( isSymmetricRecursiveInternal(leftward.right, rightward.left))
                );
    }


}
