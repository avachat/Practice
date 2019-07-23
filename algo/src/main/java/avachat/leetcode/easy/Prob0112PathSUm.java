package avachat.leetcode.easy;

public class Prob0112PathSUm {

    /*
    Given a binary tree and a sum,
    determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {


        /*
         BAD : Did not correctly identify the leaf node -- see incorrect method below

         And need to handle edge condition : null root node, and 0 as sum - should return false
         */


        if ( root == null ) {
            return false;
        } else {
            return hasPathSumInternal(root, sum);
        }

    }


    public boolean hasPathSumInternal(TreeNode root, int sum) {

        if ( (root.left == null) && (root.right == null) ) {
            return (sum == root.val);
        }

        int diffNeeded = sum - root.val;
        boolean hasPathSumLeft = (root.left != null) && (hasPathSumInternal(root.left, diffNeeded));
        return (hasPathSumLeft || ( (root.right != null) && (hasPathSumInternal(root.right, diffNeeded))));
    }



    public boolean hasPathSumInternal_WRONG_DOES_NOT_TERMINATE_AT_LEAF_ONLY(TreeNode root, int sum) {

        if ( root == null) {
            return (sum == 0);
        }

        int diffNeeded = sum - root.val;
        return ( hasPathSumInternal(root.left, diffNeeded) || hasPathSumInternal(root.right, diffNeeded));

    }

}
