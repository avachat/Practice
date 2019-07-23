package avachat.leetcode.easy;

public class Prob0680ValidPalndrome {

    /*
    Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     */


    public boolean validPalindrome(String s) {

        /*

        GOOD : Fast solution, better than leetcode answer, no stack space needed
            Beats 100% on time and space

        BAD : Initial impl was using only one lookahead - that's not enough
            (See test case)

         */

        if ( (s==null) || s.isEmpty()) {
            return false;
        }

        char[] str = s.toCharArray();

        int start = 0;
        int end = str.length - 1;

        while (start < end) {
            if (str[start] != str[end]) {
                break;
            }

            start++;
            end--;
        }

        if (start >= end) {
            // we checked everything
            return true;
        }

        // Now we may find a palindrome by removing either the first char or the last char
        if ( validPalindromeStrict(str, start+1, end)) {
            return true;
        } else {
            return validPalindromeStrict(str, start, end-1);
        }


    }


    private boolean validPalindromeStrict(char[] str, int start, int end) {

        while (start < end) {
            if (str[start] != str[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


}
