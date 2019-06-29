package avachat.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1013PartitionArrayIn3EqualSumTest {

    @Test
    public void testIt() {
        Prob1013PartitionArrayIn3EqualSum testObj = new Prob1013PartitionArrayIn3EqualSum();

        assertTrue(testObj.canThreePartsEqualSum(new int[] { 1, 1, 2, 4, 2, 2}));
        assertTrue(testObj.canThreePartsEqualSum(new int[] { 1, 1, 1}));
        assertFalse(testObj.canThreePartsEqualSum(new int[] { 3, 1, 2}));
        assertTrue(testObj.canThreePartsEqualSum(new int[] { 3, 3, 1, 2}));
        assertTrue(testObj.canThreePartsEqualSum(new int[] { -3, 3, 3, 3, 1, 2, -3, -1, 4}));
        assertFalse(testObj.canThreePartsEqualSum(new int[] { -3, 3, 3, 6}));
        assertTrue(testObj.canThreePartsEqualSum(new int[] { -3, 3, 3, -3, -3, 3}));
    }

}