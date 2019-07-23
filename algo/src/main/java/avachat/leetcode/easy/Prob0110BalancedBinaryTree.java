package avachat.leetcode.easy;

public class Prob0110BalancedBinaryTree {


    /*
    Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.


     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {

        /*
        Good : Quickly done

        BAD : Many typos in copy paste and forgot to add 1 to the max height
         */
        return ( -1 != balancedHeight(root));
    }

    private int balancedHeight(TreeNode root) {

        if (null == root) {
            return 0;
        }

        int leftHeight =  balancedHeight(root.left);
        if ( leftHeight == -1) {
            return -1;
        }
        int rightHeight = balancedHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        int max = (leftHeight > rightHeight) ? leftHeight : rightHeight;
        int min = (max == leftHeight) ? rightHeight : leftHeight;

        if ( (max - min) > 1 ) {
            return -1;
        }
        return max + 1; // BAD : Forgot to add 1 :-(

    }

}
