package avachat.leetcode.easy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prob235LowestCommonAncsestor {


    /*
   Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]




Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.
     */




    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {


        /*
         GOOD : Got it in first implementation.
                This is a much more generic solution.
                    - It can work for finding ancestors of more then 2 values
                    - It will work even when there is no guarantee that the values exist (returns null if no ancestor)

         BAD : Slower compared to other submissions : maybe because of following
                The problem allows the assumption to be made that the values of the tree are unique AND
                    that the search values actually exist
               This means : there is a node where p, q are not on the same side of the node !!!
                    So if p and q are both > node.val, search on right
                       if p and q are both < node.val, search on left
                       ELSE node is the return val !!
               The above results into a very simple recursive solution
         */



        Set<Integer> remainingValues = new HashSet<>(2);
        remainingValues.add(p.val);
        remainingValues.add(q.val);

        return lowestCommonAncestor(root, remainingValues, remainingValues.size());

    }

    private TreeNode lowestCommonAncestor(TreeNode root, Set<Integer> remainingValues, int totalMatchesNeeded) {

        boolean nothingFoundBeforeReachingHere = (remainingValues.size() == totalMatchesNeeded);

        if (root.left != null) {
            TreeNode treeNode = lowestCommonAncestor(root.left, remainingValues, totalMatchesNeeded);
            if (null != treeNode) {
                return treeNode;
            }
        }

        if (root.right != null) {
            TreeNode treeNode = lowestCommonAncestor(root.right, remainingValues, totalMatchesNeeded);
            if ( null != treeNode) {
                return treeNode;
            }
        }

        remainingValues.remove(root.val); // will remove only if it's there

        return (nothingFoundBeforeReachingHere && remainingValues.isEmpty()) ? root : null;

    }

}

