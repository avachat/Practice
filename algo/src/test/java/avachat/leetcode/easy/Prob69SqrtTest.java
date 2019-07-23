package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob69SqrtTest {

    private Prob0069Sqrt testObj = new Prob0069Sqrt();

    @Test
    public void testAll() {
        assertEquals(testObj.mySqrt(8), 2);
        assertEquals(testObj.mySqrt(0), 0);
        assertEquals(testObj.mySqrt(2147483647), 46340);
        assertEquals(testObj.mySqrt(5), 2);
        assertEquals(testObj.mySqrt(1), 1);
        assertEquals(testObj.mySqrt(2), 1);
        assertEquals(testObj.mySqrt(3), 1);
        assertEquals(testObj.mySqrt(4), 2);
        assertEquals(testObj.mySqrt(5), 2);
        assertEquals(testObj.mySqrt(9), 3);
        assertEquals(testObj.mySqrt(10), 3);
        assertEquals(testObj.mySqrt(15), 3);
        assertEquals(testObj.mySqrt(16), 4);
    }

}