package avachat.hired;

import org.junit.Test;

import static org.testng.Assert.*;

public class LongestCommonPrefixTest {

    @Test
    public void testAll() {

        assertEquals(LongestCommonPrefix.solution(null), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{null}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{""}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", ""}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", "abcd", ""}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", "abcd", "abcd"}), "abcd");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", "acd", "abd"}), "a");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", "xacd", "abd"}), "");
        assertEquals(LongestCommonPrefix.solution(new String[]{"abcd", "abcde", "abcdef"}), "abcd");

    }

}