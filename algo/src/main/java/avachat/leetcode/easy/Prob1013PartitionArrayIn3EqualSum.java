package avachat.leetcode.easy;

public class Prob1013PartitionArrayIn3EqualSum {

    /*

    Given an array A of integers,
    return true if and only if we can partition the array into three non-empty parts with equal sums.

Formally, we can partition the array if we can find indexes
i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])



Example 1:

Input: [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
Example 2:

Input: [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false
Example 3:

Input: [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4


Note:

3 <= A.length <= 50000
-10000 <= A[i] <= 10000

     */


    public boolean canThreePartsEqualSum(int[] A) {

        /*
        GOOD : Got it right on 1st submission
            Quickly got the idea that one left and right partition is necessary and sufficient

        BAD : Took a bit long to write correct manipulation of array indices
         */

        if ( (null == A) || (A.length < 3)) {
            return false;
        }

        // find the total
        int total = 0;
        for (int i : A) {
            total += i;
        }

        if ((total % 3) != 0) {
            // not divisible by 3
            return  false;
        }

        int partSum = total / 3;

        // find the left partition
        int left = 0;
        int leftSum = 0;
        while ( (left < A.length) && (leftSum != partSum)) {
            leftSum += A[left];
            left++;
        }

        if (left >= A.length) {
            return false;
        }
        int leftEnd = left - 1;

        // find the right partition
        int right = A.length - 1;
        int rightSum = 0;
        while ( (right >= 0) && (rightSum != partSum)) {
            rightSum += A[right];
            right--;
        }
        int rightStart = right + 1;

        return rightStart > leftEnd;
    }

}
