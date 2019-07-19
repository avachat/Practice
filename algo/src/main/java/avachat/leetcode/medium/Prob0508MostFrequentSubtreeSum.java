package avachat.leetcode.medium;

import java.util.*;

public class Prob0508MostFrequentSubtreeSum {

    /*

       Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.

     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*

    GOOD : Ran in first attempt. Good space / time.
    BAD : Should have maintained the max sum while traversing the tree,
            as opposed to finding out while going through the map :-( :-(

     */

    public int[] findFrequentTreeSum(TreeNode root) {

        if (root == null) {
            return new int[]{};
        }
        Map<Integer, Integer> frequencies = new HashMap<>();
        int sum = subtreeSum(root, frequencies);
        return findHighestFrequencies(frequencies);
    }


    private int subtreeSum (TreeNode root, Map<Integer, Integer> frequencies) {

        if (root == null) {
            return 0;
        }

        int sum = root.val + subtreeSum(root.left, frequencies) + subtreeSum(root.right, frequencies);
        int freq = frequencies.getOrDefault(sum, 0);
        frequencies.put(sum, freq+1);

        return sum;
    }

    private int[] findHighestFrequencies(Map<Integer, Integer> frequencies) {

        List<Integer> resultList = new LinkedList<>();

        int maxFrequency = 0;

        for (int sum : frequencies.keySet()) {

            int freq = frequencies.get(sum);

            if (freq < maxFrequency) {
                continue;
            }

            if (freq > maxFrequency) {
                resultList.clear();
                maxFrequency = freq;
            }

            resultList.add(sum);
        }

        int[] result = new int[resultList.size()];
        int index = 0;
        for (int sum : resultList) {
            result[index++] = sum;
        }

        return result;
    }

}
