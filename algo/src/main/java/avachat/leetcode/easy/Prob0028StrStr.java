package avachat.leetcode.easy;

public class Prob28StrStr {


    /*
    Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     */


    public int strStr(String haystack, String needle) {


        /*
        GOOD : No test case needed. Passed in first run.
        Except that the problem does not specify the return value for a null haystack, so that check must come later.
         */

        if ( (null == needle) || (needle.isEmpty())) {
            return 0;
        }

        if ((null == haystack) || (haystack.isEmpty())) {
            return -1;
        }

        for (int startAt = 0; (startAt + needle.length()) <= haystack.length(); startAt++) {
            // look for the string at startAt
            boolean found = true;
            for (int i = 0; i < needle.length(); i++) {
                if ( needle.charAt(i) != haystack.charAt(startAt+i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return startAt;
            }
        }

        return -1;

    }

}
