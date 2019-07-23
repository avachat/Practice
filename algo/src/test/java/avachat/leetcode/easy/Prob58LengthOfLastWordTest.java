package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob58LengthOfLastWordTest {

    private Prob0058LengthOfLastWord testObj = new Prob0058LengthOfLastWord();

    @Test
    public void  testOneChar() {
        //assertEquals(testObj.lengthOfLastWord(""), 0);
        assertEquals(testObj.lengthOfLastWord("a"), 1);
        assertEquals(testObj.lengthOfLastWord("a "), 1);
        //assertEquals(testObj.lengthOfLastWord("aa"), 2);
    }

}