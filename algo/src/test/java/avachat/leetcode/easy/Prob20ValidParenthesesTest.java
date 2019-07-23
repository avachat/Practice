package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob20ValidParenthesesTest {

    private static final Prob0020ValidParentheses testObj = new Prob0020ValidParentheses();

    @Test
    public void testValid() {
        assertTrue(testObj.isValid(""));
        assertTrue(testObj.isValid("()"));
        assertTrue(testObj.isValid("()()()()"));
        assertTrue(testObj.isValid("()[]()[]"));
        assertTrue(testObj.isValid("()[]()[]{}{}{}"));
        assertTrue(testObj.isValid("([])([{}]{{}})"));
        assertTrue(testObj.isValid("([])([{}]{{{{{{{}}}}}}})"));
    }

    @Test
    public void testInvalid() {
        assertFalse(testObj.isValid("z"));
        assertFalse(testObj.isValid(")"));
        assertFalse(testObj.isValid("("));
        assertFalse(testObj.isValid("()()()("));
        assertFalse(testObj.isValid("()()())"));
        assertFalse(testObj.isValid("()[])[]"));
        assertFalse(testObj.isValid("()[]()[{}{}{}"));
        assertFalse(testObj.isValid("([])([{}]{{{{{{{}}}}}})"));
        assertFalse(testObj.isValid("([])([{}]{{{{{{{}}}}}}}})"));
    }
}