package avachat.leetcode.easy;

public class Prob942DIStringMatch {

    /*

    Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]


Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]


Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".



     */

    public int[] diStringMatch(String S) {

        if ( (null == S) || (S.isEmpty())) {
            return new int[0];
        }

        // perm needs one more element than S
        int[] perm = new int[S.length() + 1];
        char[] str = S.toCharArray();

        // we need to fill it with numbers from 0 to S.length
        int max = S.length(); // max
        int min = 0;

        int i = 0;
        // Fill rest of the array
        for (char c : str) {
            perm[i++] = (c=='D') ? max-- : min++;
        }

        // at this point min and max should be same
        perm[i] = min;

        return perm;

    }

}
