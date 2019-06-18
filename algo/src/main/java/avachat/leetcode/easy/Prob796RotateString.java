package avachat.leetcode.easy;

public class Prob796RotateString {

    /*

    We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.


     */


    public boolean rotateString(String A, String B) {

        /*

        GOOD : Time and space better than 100%
            First attempt

         */

        if ( A == null) {
            return B == null;
        }

        if ( B == null) {
            return false;
        }

        if (A.equals(B)) {
            return true;
        }

        if (A.length() != B.length()) {
            return false;
        }

        char[] strA = A.toCharArray();
        char[] strB = B.toCharArray();

        int i = 0;
        int j = findChar(strB, strA[0], 0);
        int foundAt = j;
        while ( (i < strA.length) && (j >= 0)) {
            if (strA[i] == strB[j]) {
                i++;
                j++;
                if (j >= strB.length) {
                    j = 0; // wrap around
                }
            } else {
                i = 0;
                j = findChar(strB, strA[0], foundAt+1);
                foundAt = j;
            }
        }

        return (j > 0);
    }

    private int findChar (char[] str, char c, int startAt) {

        for(int i = startAt; i < str.length; i++) {
            if (c == str[i]) {
                return i;
            }
        }

        return -1;
    }



}
