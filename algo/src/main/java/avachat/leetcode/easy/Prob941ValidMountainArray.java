package avachat.leetcode.easy;

public class Prob941ValidMountainArray {

    /*

Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]


Example 1:

Input: [2,1]
Output: false
Example 2:

Input: [3,5,5]
Output: false
Example 3:

Input: [0,3,2,1]
Output: true


Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
     */



    public boolean validMountainArray(int[] A) {

        /*

        GOOD : Better space and time than 100%

        BAD : Took 7 attempts to get the boundary conditions right
            1. Missed correct >, < signs
            2. Could not imagine in head what values of pivot would be
            3. Missed monotonically increasing/decreasing array (it needs to be a perfect mountain)

        https://leetcode.com/problems/valid-mountain-array/submissions/

         */

        if ( (null == A) || (A.length < 3)) {
            return false;
        }

        // find the first index whose next is decreasing
        int pivot = 0;

        for (int i = 0; (i < (A.length-1)) && (A[i] < A[i+1]); i++) {
            pivot++;
        }

        if (( pivot == 0) || (pivot == A.length-1)) {
            return false; // MISSED THIS : There has to be a segment with decreasing value
        }

        //from this point onwards elements need to be in a strictly decreasing order
        int right = pivot+1;
        for (int i = right; (i < A.length) && (A[right] < A[right-1]); i++) {
            right++;
        }

        return right == A.length;
    }


}
