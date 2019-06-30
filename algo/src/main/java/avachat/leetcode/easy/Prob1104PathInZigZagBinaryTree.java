package avachat.leetcode.easy;

import java.util.ArrayList;
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


        /*

        GOOD : First impl ran, space time better than 100%

        BAD : Took a while to get the loop right

         */


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

        int level = mid;
        List<Integer> path = new ArrayList<>(level);
        int current = label;
        while (current > 1) {
            // whatever current is, add to the list
            // flip position
            // find parent
            path.add(0, current);
            int position = current; // where parent would be if level was written correctly
            // reverse position
            position = levelStart[level + 1] - 1 - current + levelStart[level];
            // for 8 : position = 16 - 1 - 8 + 8 = 15
            // for 15 : position = 16 - 1 - 15 + 8 = 8
            // for 12 : position = 16 - 1 - 12 + 8 = 11
            // for 10 : position = 16 - 1 - 10 + 8 = 13
            // this position is what goes in the path
            // based on the effective position, calculate parent
            current = position / 2;
            level --;
            // trace through :
            // if current was at 12
            // list.add 12
            // flip position to 11
            // current = 5
            // list.add 5
            // flip position to 6
            // current = 3
            // list add 3
            // flip position to 2
            // parent = 1
        }

        // finally add 1 ro the path
        path.add(0, current);

        return path;
    }


}
