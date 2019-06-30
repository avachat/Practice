package avachat.leetcode.easy;

import java.util.List;

public class Prob1104PathInZigZagBinaryTree {

    /*

    In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.



Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.



Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]


Constraints:

1 <= label <= 10^6
     */


    /*
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    */

    public List<Integer> pathInZigZagTree(int label) {

        // create an index with power of 2
        // note that the problem states root is at level 1
        // each level start at 2^(n-1) and ends at 2^n - 1
        // for example
        // level 2 starts at 2 and ends at 3
        // level 3 starts at 4 and ends at 7
        // level 4 starts at 8 and ends at 15
        int[] levelStart = new int[25];
        levelStart[0] = 0; // we don't need this
        levelStart[1] = 1; // root node
        for (int i = 2; i < levelStart.length; i++) {
            levelStart[i] = 2* levelStart[i-1];
        }

        // now locate, using binary search the level where label exists
        // the label exists at level, where levelStart[level] <= label levelStart[level+1]
        int level = -1;
        int left = 1;
        int right = levelStart.length;
        int mid = left + ((right - left) / 2);
        while ( ! ( (label >= levelStart[mid]) && (label < levelStart[mid+1]))) {
            if (label < levelStart[mid]) {
                right = mid;
                mid = left + ((right - left) / 2);
            } else {
                left = mid + 1;
                mid = left + ((right - left) / 2);
            }
        }

        return null;
    }


}
