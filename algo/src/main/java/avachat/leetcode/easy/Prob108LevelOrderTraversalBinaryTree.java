package avachat.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class Prob108LevelOrderTraversalBinaryTree {


    /*
    Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

     */


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {


        /*
         * GOOD : Got correct in first submission.
         */

        if ( root == null ) {
            return new ArrayList<>(0);
        }

        List<List<TreeNode>> leveledNodes = new ArrayList<>();

        List<TreeNode> seed = new ArrayList<>(1);
        seed.add(root);

        // init the flat representation
        leveledNodes.add(0, seed);

        for (int level = 0; level < leveledNodes.size(); level++) {

            List<TreeNode> currentLevel = leveledNodes.get(level);
            List<TreeNode> nextLevel = null;

            for (TreeNode node : currentLevel) {
                TreeNode left = node.left;
                TreeNode right = node.right;
                if ( (left == null) && (right == null)) {
                    continue;
                }
                if (nextLevel == null) {
                    nextLevel = new ArrayList<>();
                }
                if (left != null) {
                    nextLevel.add(left);
                }
                if (right != null) {
                    nextLevel.add(right);
                }
            }

            if ( nextLevel != null) {
                leveledNodes.add(nextLevel);
            }
        }

        List<List<Integer>> result = new ArrayList<>(leveledNodes.size());
        for (int level = leveledNodes.size()-1; level >= 0; level--) {
            List<TreeNode> currentLevel = leveledNodes.get(level);
            List<Integer> nodeValueList = new ArrayList<>(currentLevel.size());
            for (TreeNode treeNode : currentLevel) {
                nodeValueList.add(treeNode.val);
            }
            result.add(nodeValueList);
        }

        return result;

    }

}
