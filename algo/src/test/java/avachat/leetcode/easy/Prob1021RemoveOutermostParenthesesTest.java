package avachat.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1021RemoveOutermostParenthesesTest {

    @Test
    public void testIt() {
        Prob1021RemoveOutermostParentheses testObj = new Prob1021RemoveOutermostParentheses();

        assertEquals(testObj.removeOuterParentheses("(()())(())"), "()()()");
        assertEquals(testObj.removeOuterParentheses("(()())(())(()(()))"), "()()()()(())");
        assertEquals(testObj.removeOuterParentheses("()()"), "");
    }

}