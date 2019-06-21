package avachat.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class Prob888FairCandy {

    /*

    Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.



Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]
Example 2:

Input: A = [1,2], B = [2,3]
Output: [1,2]
Example 3:

Input: A = [2], B = [1,3]
Output: [2,3]
Example 4:

Input: A = [1,2,5], B = [2,4]
Output: [5,4]


Note:

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.

     */

    /*
    NOTE : In spite of the complicated description, the problem is to exchange two numbers in the arrays
        so that each array totals to the same
     */

    public int[] fairCandySwap(int[] A, int[] B) {


        // there is guaranteed to be a solution
        // Each have diff amount of candy

        // we could have used which array is smaller or larger
        // but the answer requires order 0 is for A, 1 is for B
        // unnecessary complicates the logic with if conditions

        int totalA = 0;
        Set<Integer> availableA = new HashSet<>(A.length); // what'a available in A
        for (int i : A) {
            totalA += i;
            availableA.add(i);
        }

        int totalB = 0;
        for (int i : B) {
            totalB += i;
        }

        int desired = (totalA + totalB) / 2 ; // solution is guaranteed

        // Now iterate over B
        // See which number B can lose, and if that's added to A,
        // does A have the number to give it up
        int[] result = new int[2];
        for (int b : B) {
            // if b is removed, what would remain
            int remainB = totalB - b;
            if (remainB >= desired) {
                // removing this would not help
                continue;
            }
            // now remainB is less than desired
            // see if A can fulfill this
            int needB = desired - remainB;
            if ( availableA.contains(needB)) {
                result[0] = needB;
                result[1] = b;
                return result;
            }
            // Math
            // totalA - needB + b  === what A has
            // totalB - b + needB == what B has
            //
            // totalA - needB + b
            // = totalA - (desired - remainB) + b
            // = totalA - (desired - (totalB - b)) + b
            // = totalA - desired + totalB - b + b
            // = (totalA + totalB) - desired
            // = (2*desired) - desired
            // = desired
            //
            // totalB - b + needB
            // = totalB - b + desired - remainB
            // = totalB - b + desired - (totalB - b)
            // = totalB - B + desired - totalB + b
            // = desired


        }

        return result; // this should never happen - as result is guaranteed

    }

}
