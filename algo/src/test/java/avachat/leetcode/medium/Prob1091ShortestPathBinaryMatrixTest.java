package avachat.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1091ShortestPathBinaryMatrixTest {


    @Test
    public void testIt() {

        Prob1091ShortestPathBinaryMatrix testObj = new Prob1091ShortestPathBinaryMatrix();

        assertEquals(4, testObj.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));

    }
}