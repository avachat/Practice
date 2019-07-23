package avachat.leetcode.easy;

public class Prob0897IncreasingOrderTree {

    /*


    Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.


     */



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode increasingBST(TreeNode root) {

        /*

        GOOD : A different solution than leetcode
            Only one traversal, modified tree in place
            Faster than 100%

        BAD : Took a long long time to figure out
            Forgot to set the left to null

         */

        return convertToIncreasingBST(root, null);
    }

    /**
     *
     * Given the result so far, convert the tree at this node
     * The result so far is increasing tree
     *
     * if this node is null, it doesn't change the result, so just return prev result
     *
     * ask the right side to give a new result, by making use of result so far
     *     then attach that result to the root's right
     *     and make resultSoFar = root
     *     Now, except the root's left, the resultSoFar is increasing tree
     *
     * Ask the left side to arrange itself with the result so far
     *
     * @param root
     * @param resultSoFar
     * @return
     */
    private TreeNode convertToIncreasingBST(TreeNode root, TreeNode resultSoFar) {

        // if this node is null, just return the resultSoFar
        if (root == null) {
            // This the IMPORTANT part
            // ALWAYS guaranteeing that the resultSoFar is what we are returning
            // So from the left most leaf, we return the result
            return  resultSoFar;
        }

        // convert the right side and attach the result to the right
        root.right = convertToIncreasingBST(root.right, resultSoFar);

        // Now all nodes higher than this node have been arranged
        // attach this node to the top
        resultSoFar = root;

        // send the result so far to the left side
        TreeNode leftResult = convertToIncreasingBST(root.left, resultSoFar);
        // make sure left is null
        root.left = null;
        return leftResult;

    }



    /*

    Leetcode answer :
    In order traversal.
    Create a dummy node (ans)
    Use another (cur) to track where the tree needs to be attached
    So cur always points to the last node, and ans always points to the first node

        TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }

     */
}
