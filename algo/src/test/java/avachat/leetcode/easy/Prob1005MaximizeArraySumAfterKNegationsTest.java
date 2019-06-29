package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob1005MaximizeArraySumAfterKNegationsTest {

    @Test
    public void testIt() {
        Prob1005MaximizeArraySumAfterKNegations testObj = new Prob1005MaximizeArraySumAfterKNegations();

        assertEquals(testObj.largestSumAfterKNegations(new int[]{5,0,-5,3,-3,2,0},7), 18);
        assertEquals(testObj.largestSumAfterKNegations(new int[]{4, 2, 3}, 1), 5);
        assertEquals(testObj.largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3), 6);
        assertEquals(testObj.largestSumAfterKNegations(new int[]{-8, 3, -5, -3, -5, -2}, 6), 22);
    }

}