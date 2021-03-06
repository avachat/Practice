package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob268MissingNumberTest {

    private static Prob0268MissingNumber testObj = new Prob0268MissingNumber();

    @Test
    public void testAll() {
        assertEquals(testObj.missingNumber(null), 0);
        assertEquals(testObj.missingNumber(new int[]{}), 0);
        assertEquals(testObj.missingNumber(new int[]{1}), 0);
    }

}