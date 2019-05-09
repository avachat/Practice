package avachat.leetcode.easy;

public class Prob108SortedArray2BST {

    /*

    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

     */



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode sortedArrayToBST(int[] nums) {

        /*
        GOOD : Got in 5 mins, worked the first time
         */

        if ( (null == nums) || (0 == nums.length)) {
            return null;
        }

        return sortedArrayToBSTInternal(nums, 0, nums.length-1);
    }


    public TreeNode sortedArrayToBSTInternal(int[] nums, int startIndex, int endIndex) {

        //if ( (startIndex < 0) || (endIndex >= nums.length) || (startIndex > endIndex) ) {
        //  Above condition is not needed - didn't submit it - GOOD

        if ( startIndex > endIndex ) {
            return null;
        }

        int mid = (startIndex + endIndex) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBSTInternal(nums, startIndex, mid-1);
        root.right = sortedArrayToBSTInternal(nums, mid+1, endIndex);

        return root;
    }


}
