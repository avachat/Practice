package avachat.hired;

import org.junit.Test;

import static org.testng.Assert.*;

public class PermOfPalindromeTest {

    @Test
    public void testAll() {

        assertFalse(PermOfPalindrome.solution("ab"));

        assertTrue(PermOfPalindrome.solution("carrace"));
        assertTrue(PermOfPalindrome.solution("aabbb"));
        assertTrue(PermOfPalindrome.solution("a"));
        assertFalse(PermOfPalindrome.solution("ab"));
        assertFalse(PermOfPalindrome.solution("abcabd"));
        assertFalse(PermOfPalindrome.solution("abcabcab"));
    }

}