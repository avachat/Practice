package avachat.leetcode.easy;

public class Prob859BuddyStrings {

    /*
   Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.



Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false


Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

     */

    public boolean buddyStrings(String A, String B) {

        /*

        GOOD : Got all the edge cases correct in 1 attempt
            Faster than 97%, less memory than 100%

        BAD : Made a couple of silly mistakes

         */


        if ( (null == A) || (null == B) ) {
            return false;
        }

        if ( (A.length() < 2) || (B.length() < 2)) {
            return false;
        }

        char[] strA = A.toCharArray();
        char[] strB = B.toCharArray();

        if (strA.length != strB.length) {
            return false;
        }

        // now the strings have equal length that is >= 2

        int[] counts = new int[26]; // only 26 possible chars in the string
        int mismatches = 0;
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < strA.length; i++) {

            if (strA[i] != strB[i]) {
                mismatches ++;
                if (mismatches > 2) {
                    return false;
                }
                if (mismatches == 1) {
                    index1 = i;
                } else { // no need for another if mismatches == 2, if it's more than 2, we return false already
                    index2 = i;
                }
            }


            counts[strA[i] -'a']++; // increment the count of the chars seen
        }

        if (0 == mismatches) {
            // the strings are equal
            // there must be at least one character which occurs twice
            for (int c : counts) {
                if (c >= 2) {
                    return true;
                }
            }
            // only single chars in two strings that are equal
            return false;
        } else if (2 != mismatches) {
            return false;
        }

        // now that there are exact two mismatches, let's make sure they can be swapped
        return ( (strA[index1] == strB[index2]) && (strA[index2] == strB[index1]));

    }



}
