package avachat.leetcode.easy;

public class Prob844BackspaceCompare {

    /*

    Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?


     */


    public boolean backspaceCompare(String S, String T) {


        /*

        GOOD : Finally the solution is better than 100% for both time and space

        BAD : Missed many many test cases, required large number of tries
                See test cases and comments in the code

              Still couldn't thnk of stacks, not that they give a better space solution
                but stack should come to mind logically for such backtracking operations


         */



        if ( S == null) {
            return T == null;
        }

        if ( T == null) {
            return false;
        }

        if ( S.isEmpty() && T.isEmpty() ) {
            return true;
        }

        char[] strA = S.toCharArray();
        char[] strB = T.toCharArray();

        int i = strA.length - 1;
        int j = strB.length - 1;

        while ( (i >= 0) && (j >= 0)) {

            i = getNextUndeletedCharIndex(strA, i);
            j = getNextUndeletedCharIndex(strB, j);

            if ( i < 0 ) {
                return j < 0;
            }

            if (j < 0 ) {
                return false;
            }

            if ( strA[i] != strB[j] ) {
                return false;
            }

            i--;
            j--;

        }

        if  ( (i < 0) && (j < 0)) {
            return true;
        }


        // BAD : Following test case was completely missed
        // One string may be finished (index < 0)
        // But other string may have "deleted" chars at the beginning

            // check if extra chars at the beginning of the other string can be removed
        if ( i < 0 ) {
            return getNextUndeletedCharIndex(strB, j) < 0;
        }

        // this means j < 0
        return getNextUndeletedCharIndex(strA, i) < 0;

    }

    private int getNextUndeletedCharIndex(char[] chars, int i) {

        if (chars[i] != '#') {
            return i;
        }

        int count = 1;
        i--; // go to prev char

        // BAD : it took a while to get the terminating condition correctly
        while ( (i >= 0) &&  ((chars[i] == '#') || (count > 0)) ) {
            if (chars[i] == '#') {
                count++;
            } else {
                count--;
            }
            i--;
        }

        return i;
    }

}
