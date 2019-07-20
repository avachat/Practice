package avachat.leetcode.medium;

public class Prob0865SmallestSubtreeWithDeepestNodes {

    /*

    Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.



Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.


Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.

     */

    /*

    The problem description is not very clear.
    Seems from the test cases that the leaf node CAN be an answer.
    Generally this is not the case with leetcode problems.

    Hence the two checks in the helper function : read the comments

     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode smallestSubtree = null;
    private int maxDepth = -1;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        if (root == null) {
            return null;
        }

        if ( (root.left == null) && (root.right == null)) {
            // edge case
            // single node tree
            return root;
        }

        int depth = traverseAndAnalyze(root, 0);

        return smallestSubtree;
    }

    private int traverseAndAnalyze(TreeNode root, int depth) {

        if ( (root.left == null) && (root.right == null)) {
            // leaf node
            // it seems that the leaf node CAN be an answer!!
            if (depth > maxDepth) {
                maxDepth = depth;
                smallestSubtree = root;
            }
            return depth;
        }

        // not a leaf node
        // at least one side has depth
        int leftDepth = (root.left != null) ? traverseAndAnalyze(root.left, depth+1) : -1;
        int rightDepth = (root.right != null) ? traverseAndAnalyze(root.right, depth+1) : -1;

        if (leftDepth == rightDepth) {
            // this node has leaves on both sides at the same depth
            // so this might be a better answer
            // NOTE : the check is ==
            // The == is necessary
            // The left side leaf has set the maxDepth
            // The right side also has the maxDepth : but it hasn't set it
            // For this node, the what we are saying is, the leaf was found at maxDepth
            if (leftDepth == maxDepth) {
                // we found a new node with deepest leaves
                smallestSubtree = root;
            }
        }

        // return largest depth of either side
        return Math.max(leftDepth, rightDepth);
    }


}
