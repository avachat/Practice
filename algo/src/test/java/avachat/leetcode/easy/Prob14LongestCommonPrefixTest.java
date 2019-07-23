package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob14LongestCommonPrefixTest {

    private Prob0014LongestCommonPrefix testObj = new Prob0014LongestCommonPrefix();

    @Test
    public void testHappyPath() {
        assertEquals(testObj.longestCommonPrefix(new String[]{"abcd", "abcxxxxx", "abcyyyyyyyy"}), "abc");
        assertEquals(testObj.longestCommonPrefix(new String[]{"abz", "abcxxxxx", "abcyyyyyyyy", "abab", "abbbb", "abaaa"}), "ab");
    }

    @Test
    public void testNothingCommon() {
        assertEquals(testObj.longestCommonPrefix(new String[]{"aaa", "bbb"}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"baa", "abb"}), "");
    }

    @Test
    public void testEdgeConditions() {
        assertEquals(testObj.longestCommonPrefix(new String[]{}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{null}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{""}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"a"}), "a");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab"}), "ab");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab", ""}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab", null}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab", null, ""}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab", "ab", ""}), "");
        assertEquals(testObj.longestCommonPrefix(new String[]{"ab", "ab", "ab"}), "ab");
    }

}