package avachat.leetcode.easy;

public class Prob0977SquareOfSortedArray {

    /*

    Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.



Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.

     */

    public int[] sortedSquares(int[] A) {


        /*
        GOOD : Got the 2 pointer solution quickly

        BAD :
            Missed the solution : create square and sort
                It's not faster but easier

            Don't need to compare math.abs, just square and compare!


         */


        // some numbers may be negative
        // so we have to find a pivot that's >= 0
        int pivot = 0;
        for (int i = 0; (i < A.length) && (A[i] < 0); i++) {
            pivot++;
        }

        // MISSED :-( :-(
        // in case of all negative numbers, pivot will overflow
        if (pivot == A.length) {
            pivot--;
        }


        int[] result = new int[A.length];


        /*

        CANNOT DO THIS :-( :-(
        Number at pivot may not be the smallest :-( :-(

        // put pivot square into result
        result[0] = A[pivot]*A[pivot];
        int index = 1;

        */

        int index = 0;
        int right = pivot; // numbers >= 0
        int left = pivot-1; // numbers < 0

        while ( (left >= 0) && (right < A.length)) {
            if (Math.abs(A[right]) <= Math.abs(A[left]))  {
                result[index] = A[right]*A[right];
                index++;
                right++;
            } else {
                result[index] = A[left]*A[left];
                index++;
                left--;
            }
        }

        // handle remaining elements
        while ( left >= 0) {
            result[index] = A[left]*A[left];
            index++;
            left--;
        }

        while ( right < A.length) {
            result[index] = A[right]*A[right];
            index++;
            right++;
        }

        return result;

    }

}
