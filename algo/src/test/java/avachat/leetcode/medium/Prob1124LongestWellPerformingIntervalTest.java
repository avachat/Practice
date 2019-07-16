package avachat.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1124LongestWellPerformingIntervalTest {

    @Test
    public void testIt() {

        Prob1124LongestWellPerformingInterval testObj = new Prob1124LongestWellPerformingInterval();

        //assertEquals(0, testObj.longestWPI(new int[] {1}));
        //assertEquals(1, testObj.longestWPI(new int[] {9}));
        //assertEquals(1, testObj.longestWPI(new int[] {9, 1}));
        //assertEquals(1, testObj.longestWPI(new int[] {9, 1, 1, 1}));
        assertEquals(5, testObj.longestWPI(new int[] {9, 1, 9, 9, 1}));
        assertEquals(3, testObj.longestWPI(new int[] {9, 9, 6, 0, 6, 6, 9}));
        assertEquals(1, testObj.longestWPI(new int[] {9, 0}));
        assertEquals(1, testObj.longestWPI(new int[] {0, 9}));
        assertEquals(1, testObj.longestWPI(new int[] {0, 0, 9}));
        assertEquals(3, testObj.longestWPI(new int[] {9, 9, 0, 0, 0, 0, 9, 9}));
        assertEquals(3, testObj.longestWPI(new int[] {9, 9, 0, 0, 0, 0, 9}));
        assertEquals(5, testObj.longestWPI(new int[] {9, 0, 0, 0, 9, 9, 0, 0, 9, 0, 0, 9}));
    }
}