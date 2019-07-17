package avachat.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob0567PermutationSubstringTest {

    @Test
    public void testIt() {

        Prob0567PermutationSubstring testObj = new Prob0567PermutationSubstring();

        assertTrue(testObj.checkInclusion("ab", "ab"));
        assertTrue(testObj.checkInclusion("ab", "ba"));
        assertTrue(testObj.checkInclusion("a", "a"));
        assertFalse(testObj.checkInclusion("a", "b"));
        assertFalse(testObj.checkInclusion("abc", "xxbcbxaxx"));
        assertTrue(testObj.checkInclusion("ab", "xxbbaxx"));
        assertTrue(testObj.checkInclusion("ab", "xxbaxx"));
    }

}