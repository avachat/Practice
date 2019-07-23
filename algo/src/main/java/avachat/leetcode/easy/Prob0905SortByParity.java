package avachat.leetcode.easy;

public class Prob0905SortByParity {

    /*

    Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.



Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.


Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000

     */


    public int[] sortArrayByParity(int[] A) {

        /*

        GOOD : Got it quickly, worked in 1st run, better solution than any on leetcode
            Better than 100% on space and time

        BAD : Made a couple of silly mistakes

         */

        if ( (A == null) || (A.length < 2)) {
            return A;
        }

        int i = 0;
        int j = A.length -1;
        while (i < j) {
            if ((A[i] % 2) == 0) {
                // i is in correct place move on
                i++;
                continue;
            }
            if ((A[j] % 2) == 1) {
                // j is in correct place, move on
                j--;
                continue;
            }
            // swap
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
            i++;
            j--;
        }

        return A;

    }

}
