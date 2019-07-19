package avachat.leetcode.medium;

public class Prob1008ReconstructBSTFromPreorder {

    /*
    Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)



Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]



Note:

1 <= preorder.length <= 100
The values of preorder are distinct.


     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode bstFromPreorder(int[] preorder) {

        if ( (null == preorder) || (preorder.length == 0)) {
            return null;
        }

        /*
        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            insertRecursive(root, preorder[i]);
        }
        return root;
        */

        return scanAndConstruct (preorder, 0, preorder.length-1);
    }

    private TreeNode scanAndConstruct(int[] nums, int start, int end) {

        if (start > end) {
            return null;
        }

        // create a node for this number
        TreeNode root = new TreeNode(nums[start]);

        // find right node
        int right = start + 1;
        while ( (right <= end) && (nums[start] >= nums[right])) {
            right++;
        }
        root.left = scanAndConstruct(nums, start+1, right-1);
        root.right = scanAndConstruct(nums, right, end);

        return root;
    }

    private void insertRecursive(TreeNode root, int num) {

        if (num > root.val) {
            if (root.right != null) {
                insertRecursive(root.right, num);
            } else {
                root.right = new TreeNode(num);
            }
        } else {
            if (root.left != null) {
                insertRecursive(root.left, num);
            } else {
                root.left = new TreeNode(num);
            }
        }
    }

}
