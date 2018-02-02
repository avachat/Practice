package avachat.leetcode.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Prob26RemoveDuplicatesTest {

    @Test
    public void testRemoveDuplicates() throws Exception {
        Prob26RemoveDuplicates testObj = new Prob26RemoveDuplicates();

        int[] emptyArr = new int[0];
        assertEquals(0, testObj.removeDuplicates(emptyArr));

        int[] singletonArray = new int[] {1};
        assertEquals(1, testObj.removeDuplicates(singletonArray));

        int[] pairArrayDuplicate = new int[] {2, 2};
        assertEquals(1, testObj.removeDuplicates(pairArrayDuplicate));
        assertEquals(new int[] {2, 2}, pairArrayDuplicate);

        int[] pairArrayUnique = new int[] {2, 3};
        assertEquals(2, testObj.removeDuplicates(pairArrayUnique));
        assertEquals(new int[] {2, 3}, pairArrayUnique);

        int[] tripleArrayUnique = new int[] {2, 3, 4};
        assertEquals(3, testObj.removeDuplicates(tripleArrayUnique));
        assertEquals(new int[] {2, 3, 4}, tripleArrayUnique);

        int[] tripleArrayDuplicate = new int[] {2, 2, 2};
        assertEquals(1, testObj.removeDuplicates(tripleArrayDuplicate));
        assertEquals(new int[] {2, 2, 2}, tripleArrayDuplicate);

        int[] duplicatePairAtBegin = new int[] {1, 1, 2, 3, 4};
        assertEquals(4, testObj.removeDuplicates(duplicatePairAtBegin));
        assertEquals(new int[] {1, 2, 3, 4, 4}, duplicatePairAtBegin);

        int[] duplicatePairAtEnd = new int[] {1, 2, 3, 4, 4};
        assertEquals(4, testObj.removeDuplicates(duplicatePairAtEnd));
        assertEquals(new int[] {1, 2, 3, 4, 4}, duplicatePairAtEnd);

        int[] duplicateTripleAtBegin = new int[] {1, 1, 1, 2, 3, 4};
        assertEquals(4, testObj.removeDuplicates(duplicateTripleAtBegin));
        assertEquals(new int[] {1, 2, 3, 4, 3, 4}, duplicateTripleAtBegin);

        int[] duplicateTripleAtEnd = new int[] {1, 2, 3, 4, 4, 4};
        assertEquals(4, testObj.removeDuplicates(duplicateTripleAtEnd));
        assertEquals(new int[] {1, 2, 3, 4, 4, 4}, duplicateTripleAtEnd);

        int[] duplicateManyAtBegin = new int[] {1, 1, 1, 1, 1, 2, 3, 4};
        assertEquals(4, testObj.removeDuplicates(duplicateManyAtBegin));
        assertEquals(new int[] {1, 2, 3, 4, 1, 2, 3, 4}, duplicateManyAtBegin);

        int[] duplicateManyAtEnd = new int[] {1, 2, 3, 4, 4, 4, 4, 4};
        assertEquals(4, testObj.removeDuplicates(duplicateManyAtEnd));
        assertEquals(new int[] {1, 2, 3, 4, 4, 4, 4, 4}, duplicateManyAtEnd);

        int[] duplicateInMiddle = new int[] {1, 2, 3, 4, 4, 4, 5};
        assertEquals(5, testObj.removeDuplicates(duplicateInMiddle));
        assertEquals(new int[] {1,2,3,4,5,4,5}, duplicateInMiddle);

        int[] multipleDuplicatesInMiddle = new int[] {1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 7, 7, 7, 7, 8};
        assertEquals(8, testObj.removeDuplicates(multipleDuplicatesInMiddle));
        assertEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 6, 7, 7, 7, 7, 7, 8}, multipleDuplicatesInMiddle);
    }
}