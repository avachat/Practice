package avachat.leetcode.easy;

public class Prob908SmallestRange {

    /*

    Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.



Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4]


Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000


     */


    public int smallestRangeI(int[] A, int K) {

        /*

        GOOD : Got in 2 mins, worked the first time and better than 100% on space and time

         */

        // find the min and max of array
        // see how high can min be increased
        // and how low can the max be taken
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i : A) {

            if (i < min) {
                min = i;
            }

            if (i > max) {
                max = i;
            }
        }

        // get min as high as possible
        // get max as low as possible
        // K is guaranteed to be positive
        min +=  K;
        max -= K;

        if (min >= max) {
            // both can be made equal with an appropriate x ( |x| <= k)
            return 0;
        }

        return max - min;

    }

}