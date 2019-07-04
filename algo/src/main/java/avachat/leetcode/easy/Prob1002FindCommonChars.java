package avachat.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prob1002FindCommonChars {


    /*

    Given an array A of strings made only from lowercase letters,
    return a list of all characters that show up in all strings within the list (including duplicates). 
    For example, if a character occurs 3 times in all strings but not 4 times,
    you need to include that character three times in the final answer.

You may return the answer in any order.



Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter

     */


    public List<String> commonChars(String[] A) {

        /*

        GOOD : Got quickly, almost no errors (except one silly mistake)

        BAD : Not fast enough. Why ?
            If intersection is done after every counting, it is not slower.
            Missed the big O calculation

            For example : There are 2 loops here.
            N : number of strings
            L : Length of the string
            First loop runs O(N*L) times
            Second loop runs O(26*N) times (That's also the memory needed)

            If intersection is done after every string
            Outer loop runs N times
                Inner loop to compute totals L times
                Intersection loop 26 times
            That is O(N * (L + 26)), which is same as this algo

         */

        if ( (null == A) || (A.length == 0)) {
            return Collections.emptyList();
        }

        int[][] count = new int[A.length][26];

        // count all occurrences of all chars
        for (int s = 0; s < A.length; s++) {

            char[] str = A[s].toCharArray();
            for (int i = 0; i < str.length; i++) {
                char c = str[i];
                count[s][c - 'a']++;
            }

        }

        // keep the strings ready
        String[] alphaStrings = new String[26];
        for (int c = 0; c < 26; c++) {
            alphaStrings[c] = String.valueOf((char) (c + 'a'));
        }

        // find the min for each char and if it's more than 0, put it in the list as many time
        List<String> result = new ArrayList<>(A.length);
        for (int c = 0; c < 26; c++) {
            int min = Integer.MAX_VALUE;
            for (int s = 0; s < A.length; s++) {
                min = Math.min(min, count[s][c]);
            }
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    result.add(alphaStrings[c]);
                }
            }
        }

        return result;

    }

}
