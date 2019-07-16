package avachat.leetcode.medium;

public class Prob1123LowestCommonAncsestorOfDeepestLeaves {

    /*

    Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.


Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation:
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
Example 2:

Input: root = [1,2,3,4]
Output: [4]
Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]


Constraints:

The given tree will have between 1 and 1000 nodes.
Each node of the tree will have a distinct value between 1 and 1000.

     */



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class LCA {
        TreeNode root;
        int level;

        public LCA(TreeNode root, int currentLevel) {
            this.root = root;
            this.level = currentLevel;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {

        if (null == root) {
            return null;
        }

        LCA lca = findLCAofDeepestLeaves(root);
        return lca.root;

    }

    private LCA findLCAofDeepestLeaves(TreeNode root) {

        // am I a leaf
        if ( (root.left == null) && (root.right == null)) {
            return new LCA(root, 0); // counting levels from bottom
        }

        // Now at least one of the branches is non null
        if (root.right == null) {
            LCA lca = findLCAofDeepestLeaves(root.left);
            // increment depth before returning
            lca.level++;
            return lca;
        }

        if (root.left == null) {
            LCA lca = findLCAofDeepestLeaves(root.right);
            // increment depth before returning
            lca.level++;
            return lca;
        }

        // now both the sides are not null
        LCA leftLCA = findLCAofDeepestLeaves(root.left);
        LCA rightLCA = findLCAofDeepestLeaves(root.right);

        // if both have LCA at the same level, this is the LCA
        if (leftLCA.level == rightLCA.level) {
            // increment depth before returning
            return new LCA(root, leftLCA.level+1);
        } else if (leftLCA.level > rightLCA.level) {
            // increment depth before returning
            leftLCA.level++;
            return leftLCA;
        } else {
            // increment depth before returning
            rightLCA.level++;
            return rightLCA;
        }

    }

}
