package avachat.leetcode.easy;

public class Prob0100SameTree {


    /*
    Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {


        /*

        GOOD : Got concise solution in 2 mins

         */

        if ( (null == p) || (null == q) ) {
            return (p==q);
        }

        return ( (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

}
