package avachat.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class Prob0637AverageOfLevelsBinaryTree {

    /*
    Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {

        /*
        GOOD : First impl in 5 mins, and correct
        BAD : Faster than only 50% :-( Why?
         */

        List<Double> result = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>(1);
        nodes.add(root);

        while (!nodes.isEmpty()) {
            result.add(listAverage(nodes));
            List<TreeNode> children = new ArrayList<>(nodes.size() * 2);
            for (TreeNode node: nodes) {
                if (null != node.left) {
                    children.add(node.left);
                }
                if (null != node.right) {
                    children.add(node.right);
                }
            }
            nodes = children;
        }

        return result;
    }

    private double listAverage(List<TreeNode> nodes) {
        if (nodes.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        int count = 0;
        for (TreeNode node : nodes) {
            count++;
            sum += node.val;
        }

        return sum / count;
    }
}
