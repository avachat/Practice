package avachat.leetcode.easy;

import java.util.List;

public class Prob0559DepthNaryTree {

    /*

    Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:






We should return its max depth, which is 3.



Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.

     */

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {

        /*

        GOOD : Quick impl, fast execution time > 99%

        BAD : Forgot add 1 :-( :-(
         */

        if (null == root) {
            return 0;
        }

        int maxDepth = 0;

        for (Node child : root.children) {
            int depth = maxDepth(child);
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }

        return maxDepth + 1; // FORGOT to add 1

    }

}
