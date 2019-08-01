package avachat.leetcode.medium;

import java.util.Arrays;

public class Prob0567PermutationSubstring {


    /*

    Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
    In other words, one of the first string's permutations is the substring of the second string.



Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False


Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

     */


    /*

    Strategy :
    The string s1 needs certain counts of chars in any order.
    First determine the counts needed for each char, as well as the total count needed.

    Start scanning s2.
    Keep updating counts needed.
    When you find a char whose needed count is zero, it's a mismatch.

    The question is where should the scan be adjusted.
    Let's see an example.

    s1 = abcd
    s2 = xxxxxxxdcbcaxxxxxxxx

    The second c would fail the current scan.
    Which means, there is no point starting the next scan at or before that c.
    So the next scan should start from the character to the right of c
    For this, we need to maintain, a position for "firstSeenAt".

    ------------

    A much simpler solution is sliding window to keep counts
    https://leetcode.com/problems/permutation-in-string/solution/


     */


    public boolean checkInclusion(String s1, String s2) {

        // edge conditions
        if ( (null == s1) || (s1.length() == 0)) {
            return false;
        }
        if ( (null == s2) || (s2.length() < s1.length())) {
            return false;
        }

        // now both s1 and s2 are non-empty strings
        // and s2 is longer than s1

        // pre-process s1
        int[] countsNeeded = new int[26];
        //boolean[] allowedChars = new boolean[26];
        for(int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            int index = ch - 'a';
            //allowedChars[index] = true;
            countsNeeded[index]++;
        }

        // start scanning s1
        int startScanAt = 0;

        while ( (startScanAt + s1.length()) <= s2.length() ) {

            // create a new array for found counts
            int[] countsFound = new int[26];
            int needed = s1.length();

            // keep a note of where the chars was seen last
            int[] lastSeenAt = new int[26];
            Arrays.fill(lastSeenAt, -1);

            int i = startScanAt;
            while (i < s2.length() && (needed > 0)) {

                char ch = s2.charAt(i);
                int index = ch - 'a';
                /*
                if (!allowedChars[index]) {
                    // found a char that's not present in s1
                    // next scan begins at next char
                    startScanAt = i+1;
                    break;
                }
                */

                // mark the lastSeenAt, if this is the first time we are seeing it
                if (lastSeenAt[index] < 0) {
                    lastSeenAt[index] = i;
                }

                // increment countsFound
                countsFound[index]++;
                // have we found too much
                if (countsFound[index] > countsNeeded[index]) {
                    // get the lastSeenIndex for this char
                    // and start the scan from the next position
                    startScanAt = lastSeenAt[index] + 1;
                    break;
                }

                // update count of more needed
                needed--;

                // go to the next char
                i++;
            }

            // have we found all the chars?
            if (needed == 0) {
                return true;
            }
        }

        return false;
    }


}
