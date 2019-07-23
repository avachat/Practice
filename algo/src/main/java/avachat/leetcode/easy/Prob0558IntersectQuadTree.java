package avachat.leetcode.easy;

public class Prob0558IntersectQuadTree {

    /*

    A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.

We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.

For example, below are two quad trees A and B:

A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F


Your task is to implement a function that will take two quadtrees and return a quadtree that represents the logical OR (or union) of the two trees.

A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+
Note:

Both A and B represent grids of size N * N.
N is guaranteed to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.
The logic OR operation is defined as this: "A or B" is true if A is true, or if B is true, or if both A and B are true.


     */


    // Definition for a QuadTree node.
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    public Node intersect(Node quadTree1, Node quadTree2) {


        /*
         NOTE : This is a union problem . Leetcode name is wrong.

         GOOD : Understood the idea very correctly.
                Quick implementation.
                Faster than 100%.

         BAD : Forgot that if a new node is created after union, then it should be examined for collapsing.
                Made couple of errors in checking if all the values are same :-(


         */




        // if none of the trees are leaf nodes, then this node is not a leaf node
        // in that case the node should be constructed out of the union of all quads
        // (leetcode has incorrectly called it intersection)

        // if either of the tree is a leaf node, then this node is a union
        // if the leaf node value is true, then value of the other region does not matter
        // if the leaf node value is false, then the other node represents the union region

        // parameters for construction
        boolean v; // init to false for leetnode test cases?
        boolean l; // is leaf

        if ( (!quadTree1.isLeaf) && (!quadTree2.isLeaf) ) {
            // neither is leaf
            Node tl = intersect(quadTree1.topLeft, quadTree2.topLeft);
            Node tr = intersect(quadTree1.topRight, quadTree2.topRight);
            Node bl = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            Node br = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

            // can all these be collapsed into one?
            Node collapsedNode = tryToCollapse(tl, tr, bl, br);
            if ( null != collapsedNode) {
                return collapsedNode;
            }

            // create new node
            return new Node(false, false, tl, tr, bl, br);
        } else if (quadTree1.isLeaf) {
            // if the leaf node is true, the entire region in the union would be true
            // if the leaf node is false, the entire region represented by it is false,
                // so the other node represents the union region
            return (quadTree1.val) ? quadTree1 : quadTree2;
        } else {
            return (quadTree2.val) ? quadTree2 : quadTree1;
        }

    }

    private Node tryToCollapse (Node tl, Node tr, Node bl, Node br) {

        if ( (!tl.isLeaf) || (!tr.isLeaf) || (!bl.isLeaf) || (!br.isLeaf)) {
            // at least one node is not leaf, cannot collapse
            return null;
        }

        // all are leaves, there value must match
        // boolean values will match if all and ed together is same as all or ed together
        boolean andedVal = tl.val && tr.val && bl.val && br.val;
        boolean oredVal = tl.val || tr.val || bl.val || br.val;
        if ( oredVal != andedVal) {
            // cannot collapse - values are different
            return null;
        }

        // return collapsed node
        return new Node (andedVal, true, null, null, null, null);
    }

}
