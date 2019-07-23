package avachat.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class Prob0501FindModeInBST {

    /*

    Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.


For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2


return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private boolean started = false;
    private int prevMaxVal;
    private int currentCount = 0;
    private int maxCount = 0;

    public int[] findMode(TreeNode root) {


        /*

        GOOD : First implementation succeeded, with fastest time
            (NOTE : Streaming API submission made it really slow)

        BAD : Could not come up with this solution which takes O(1) space
            Using hashmap was the original idea
            But this idea of treating inorder traversal to get the effect of the sorted array
                should have been thought of.
         */

        List<Integer> resultList = new ArrayList<>();
        findModeInternal(root, resultList);


        // The streaming one liner made the total runtime at leetcode 37 ms - slowest 10% :-(
        // return resultList.stream().mapToInt(i->i).toArray();

        // replacing this with the loop reduced the runtime to 1 ms - faster than 97%
        int[] result = new int[resultList.size()];
        int i = 0;
        for (int n : resultList) {
            result[i++] = n;
        }
        return result;
    }

    /**
     * This inorder traversal guarantees that we process values in a sorted order
     * This is same as flattening the tree into a sorted array and processing the array
     *
     * @param root
     * @param result
     */
    private void findModeInternal(TreeNode root, List<Integer> result) {

        if (null == root) {
            return;
        }

        // traverse the left tree first
        findModeInternal(root.left,result);
        // after this traversal, the prevMaxVal will be the max val found in the left tree
        // that will either be less than this node val or same as this node val
        // either way at this point, we have already processed all the values les than this node val

        // if counting has not started this is the left mode leaf node of the tree
        // if the prevMaxVal is not same as this node, we are seeing this value for the first time
        // In either case currentCount should be set to 1
        // if counting has started, and
        // the current inorder traversal has the max so far, same as this val

        if ( started && (prevMaxVal == root.val)) {
            // current val was found before somewhere in the tree
            currentCount++;
            // have we found another mode ?
            if (currentCount == maxCount) {
                result.add(root.val);
            } else if (currentCount > maxCount) {
                // we found a new mode
                result.clear();
                result.add(root.val);
                maxCount = currentCount;
            }
        } else {
            // this is the first occurrence of this val
            started = true; // maybe because we are the left most leaf node : so start counting
            prevMaxVal = root.val;
            currentCount = 1;
            if (maxCount == 0) {
                maxCount = 1; // will happen only at the left most leaf node
            }
            if ( maxCount == 1) {
                // if we have seen only distinct values so far, maxCount will be 1
                // in that case, this val should also be added to the list of modes
                result.add(root.val);
            }
        }

        // start the traversal of the right tree
        // at this point the prevMaxVal is set to this root.val
        // This value may still be present on the right side
        // If it is, it will be processed before any other higher values,
            // and then the currentCount will be incremented
        findModeInternal(root.right,result);


    }
}
