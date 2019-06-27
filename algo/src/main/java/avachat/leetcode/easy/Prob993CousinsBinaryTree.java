package avachat.leetcode.easy;

public class Prob993CousinsBinaryTree {

    /*
    n a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.



Example 1:


Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false


Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.

     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode parentX = null;
    TreeNode parentY = null;
    TreeNode grandParentX = null;
    TreeNode grandParentY = null;
    int levelX = -1;
    int levelY = -1;
    int found = 0;

    public boolean isCousins(TreeNode root, int x, int y) {

        /*

        GOOD : Got it quickly, correct in first try,
            better space and time than 100%

        BAD : Some silly mistakes

         */

        if (root == null) {
            return false;
        }

        if ( (root.val == x) || (root.val == y)) {
            return false;
        }

        this.dfs(0, null, root, x, y);
        return ( (found == 2) && (levelX == levelY) && (parentX != parentY));
    }

    private void dfs (int level, TreeNode parent, TreeNode root, int x, int y) {

        if (root.left != null) {
            if (root.left.val == x) {
                parentX = root;
                grandParentX = parent;
                levelX = level + 1;
                found++;
            } else if (root.left.val == y) {
                parentY = root;
                grandParentY = parent;
                levelY = level + 1;
                found++;
            }
            if (found < 2) {
                dfs(level+1, root, root.left, x, y);
            }
        }

        if (root.right != null) {
            if (root.right.val == x) {
                parentX = root;
                grandParentX = parent;
                levelX = level + 1;
                found++;
            } else if (root.right.val == y) {
                parentY = root;
                grandParentY = parent;
                levelY = level + 1;
                found++;
            }
            if (found < 2) {
                dfs(level+1, root, root.right, x, y);
            }
        }

    }



}
