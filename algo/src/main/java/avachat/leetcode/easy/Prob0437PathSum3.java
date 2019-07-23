package avachat.leetcode.easy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Prob0437PathSum3 {

    /*
    You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

     */


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int pathSum(TreeNode root, int sum) {

        /*

        GOOD : Got the solution, not much else :-(

        BAD : Seems to be very slow - in the bottom 5 percentile on leetcode
        Not sure how to optimize :-(

         */

        long[] result = new long[] {0};

        pathSumInternal(root, sum, result);

        return (int) result[0];
    }

    private Map<Long, Long> pathSumInternal(TreeNode root, int sum, long[] result) {

        if (root == null) {
            return Collections.emptyMap();
        }

        Map<Long, Long> leftMap = pathSumInternal(root.left, sum, result);
        Map<Long, Long> rightMap = pathSumInternal(root.right, sum, result);

        int size = 1;
        size += leftMap.size();
        size += rightMap.size();

        Map<Long, Long> map = new HashMap<>(size);

        // add from left map
        accumulateFromChildPaths(root, sum, result, leftMap, map);

        // add from right map
        accumulateFromChildPaths(root, sum, result, rightMap, map);

        // add a single node path for this node
        long pathCountRootVal = 0;
        if (map.containsKey((long)root.val)) {
            pathCountRootVal = map.get((long)root.val);
        }
        pathCountRootVal ++;
        map.put((long)root.val, pathCountRootVal);

        if (root.val == sum) {
            result[0]++;
        }

        return map;
    }

    private void accumulateFromChildPaths(TreeNode root, int sum, long[] result, Map<Long, Long> childMap, Map<Long, Long> map) {
        for (long pathSum : childMap.keySet()) {
            long pathCount  = childMap.get(pathSum);

            // every child path can be extended with this node
            long newPathSum = root.val + pathSum;
            long newPathCount = pathCount; // number of paths don't change when this node is added
            if ( map.containsKey(newPathSum)) {
                newPathCount += map.get(newPathSum); // if there are paths of this length add to the map
            }
            map.put(newPathSum, newPathCount);

            // if the new path matches the sum, increment result by prev pathCount
            if (sum == newPathSum) {
                result[0] += pathCount;
            }

        }
    }

}
