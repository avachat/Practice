package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob67AddBinaryTest {

    private Prob67AddBinary testObj = new Prob67AddBinary();

    @Test
    public void testAll() {
        assertEquals(testObj.addBinary("0", "0"), "0");
        assertEquals(testObj.addBinary("0", "1"), "1");
        assertEquals(testObj.addBinary("1", "0"), "1");
        assertEquals(testObj.addBinary("1", "1"), "10");
        assertEquals(testObj.addBinary("10", "1"), "11");
        assertEquals(testObj.addBinary("11", "1"), "100");
        assertEquals(testObj.addBinary("11", "11"), "110");
        assertEquals(testObj.addBinary("100", "1"), "101");
        assertEquals(testObj.addBinary("1001", "101"), "1110");
    }

}