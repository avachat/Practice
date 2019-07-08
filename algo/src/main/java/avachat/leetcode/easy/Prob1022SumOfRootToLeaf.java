package avachat.leetcode.easy;

public class Prob1022SumOfRootToLeaf {

    /*

    Given a binary tree, each node has value 0 or 1.
    Each root-to-leaf path represents a binary number starting with the most significant bit.
    For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.



Example 1:



Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22


Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.


     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int sum = 0;

    public int sumRootToLeaf(TreeNode root) {

        /*

        GOOD : Quick impl, correct the first time, good space/time complexity

        BAD : Not much
         */

        sumDFS(root, "");
        return sum;
    }


    private void sumDFS(TreeNode root, String parentStr) {

        if (root == null) {
            return;
        }

        String str = parentStr + root.val;

        if ( (root.left == null) && (root.right == null)) {
            // leaf node
            sum += Integer.parseInt(str, 2);
            return;
        }

        sumDFS(root.left, str);
        sumDFS(root.right, str);
    }

}
