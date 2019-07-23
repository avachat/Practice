package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob844BackspaceCompareTest {

    private static final Prob0844BackspaceCompare testObj = new Prob0844BackspaceCompare();

    @Test
    public void testIt() {
        assertTrue(testObj.backspaceCompare("ab##", "c#d#"));
        assertTrue(testObj.backspaceCompare("xywrrmp","xywrrmu#p"));
        assertTrue(testObj.backspaceCompare("nzp#o#g","b#nzp#o#g"));
    }

}