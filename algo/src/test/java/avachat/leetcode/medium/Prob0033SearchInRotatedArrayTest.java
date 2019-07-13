package avachat.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob0033SearchInRotatedArrayTest {


    @Test
    public void testIt() {

        Prob0033SearchInRotatedArray testObj = new Prob0033SearchInRotatedArray();

        assertEquals(testObj.search(new int[] {5, 7, 8, 9, 1, 2, 4}, 2), 5);
        assertEquals(testObj.search(new int[] {5, 7, 8, 9, 1, 2, 4}, 1), 4);
        assertEquals(testObj.search(new int[] {5, 7, 8, 9, 1, 2, 4}, 0), -1);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 4}, 2), 6);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 4}, 5), 0);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 4}, 4), 7);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 4}, 0), -1);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 5), 0);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 4), 8);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 1), 5);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 9), 4);
        assertEquals(testObj.search(new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 0), -1);

    }

}