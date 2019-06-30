package avachat.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1104PathInZigZagBinaryTreeTest {

    @Test
    public void testIt() {
        Prob1104PathInZigZagBinaryTree testObj = new Prob1104PathInZigZagBinaryTree();

        testObj.pathInZigZagTree(2);
        testObj.pathInZigZagTree(7);
        testObj.pathInZigZagTree(8);
        testObj.pathInZigZagTree(14);
        testObj.pathInZigZagTree(16);
        testObj.pathInZigZagTree(100);
        testObj.pathInZigZagTree(200);
        testObj.pathInZigZagTree(1000);
        testObj.pathInZigZagTree(2000);
        testObj.pathInZigZagTree(10000);
        testObj.pathInZigZagTree(100000);
        testObj.pathInZigZagTree(2000000);

    }

}