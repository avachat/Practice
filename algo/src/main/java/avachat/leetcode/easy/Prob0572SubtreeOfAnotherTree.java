package avachat.leetcode.easy;

public class Prob0572SubtreeOfAnotherTree {

    /*

    Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

     */

    /*

    GOOD : Correct in 10 minutes

    The leetcode solution of generating preorder traversal strings and doing a substring is neat

     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {

        // trees are guaranteed to be non empty
        return findRootAndCheck(s, t);
    }

    private boolean findRootAndCheck (TreeNode s, TreeNode t) {

        if (s == null) {
            return false;
        }

        if (s.val == t.val) {
            boolean matchedHere = compare(s, t);
            if (matchedHere) {
                return true;
            }
        }

        return findRootAndCheck(s.left, t) || findRootAndCheck(s.right, t);
    }

    private boolean compare (TreeNode s, TreeNode t) {

        if ( (s == null) || (t == null)) {
            return s == t;
        }

        if (s.val != t.val) {
            return false;
        }

        return compare(s.left, t.left) && compare(s.right, t.right);

    }
}
