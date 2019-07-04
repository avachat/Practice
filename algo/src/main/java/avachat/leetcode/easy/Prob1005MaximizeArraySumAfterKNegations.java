package avachat.leetcode.easy;

import java.util.Arrays;

public class Prob1005MaximizeArraySumAfterKNegations {

    /*

    iven an array A of integers, we must modify the array in the following way:
    we choose an i and replace A[i] with -A[i], and we repeat this process K times in total. 
    (We may choose the same index i multiple times.)

Return the largest possible sum of the array after modifying it in this way.



Example 1:

Input: A = [4,2,3], K = 1
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].
Example 2:

Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
Example 3:

Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].


Note:

1 <= A.length <= 10000
1 <= K <= 10000
-100 <= A[i] <= 100


     */



    public int largestSumAfterKNegations(int[] A, int K) {

        /*

        GOOD : Wrote heap functionality

        BAD : Took a very very long time
            Missed many cases
            Took a while to simplify the code

         */

        // A is guaranteed to be not null and not empty
        Arrays.sort(A);

        // Now A is like a min heap
        // after every negation the heap should be rearranged
        for (int i = 0; i < K; i++) {
            A[0] = -A[0];
            sink(A);
        }

        int sum = 0;
        for (int i : A) {
            sum += i;
        }

        return sum;
    }

    private void sink(int[] A) {
        // sink the top element
        int i = 0;
        int leftChild =  (2*i) + 1;
        int rightChild =  (2*i) + 2;
        while ( (leftChild < A.length) || (rightChild < A.length)) {

            // Either the rightChild does not exit OR leftChild is less than rightChild
            // AND leftChild is less than i : we need a swap
            if ( ((rightChild >= A.length) || isLess(A, leftChild, rightChild))
                    && isLess(A, leftChild, i)) {

                swap(A, leftChild, i);
                i = leftChild;
            } else if ( ((leftChild >= A.length) || isLess(A, rightChild, leftChild))
                    && isLess(A, rightChild, i) ) {
                // EITHER the leftChild does not exit, OR right child is less
                // AND i is less than rightChild
                swap(A, i, rightChild);
                i = rightChild;
            } else if ( (leftChild < A.length) && (rightChild < A.length)
                    && (A[leftChild] == A[rightChild])
                    && (isLess(A, leftChild, i))){
                swap(A, leftChild, i);
                i = leftChild;
            } else {
                break;
            }

            leftChild =  (2*i) + 1;
            rightChild =  (2*i) + 2;
        }
    }

    private void sinkLong(int[] A) {
        // sink the top element
        int i = 0;
        int leftChild =  (2*i) + 1;
        int rightChild =  (2*i) + 2;
        while ( (leftChild < A.length) || (rightChild < A.length)) {
            // at least one child exists
            if (leftChild >= A.length) {
                // left child does not exist
                if (isLess(A, rightChild, i)) {
                    // right child is less, swap it
                    swap(A, rightChild, i);
                    i = rightChild;
                } else {
                    // only right child exists and it's not less
                    // we are done
                    break;
                }
            } else if (rightChild >= A.length) {
                // right child does not exist
                if (isLess(A, leftChild, i)) {
                    swap(A, leftChild, i);
                    i = leftChild;
                } else {
                    // only left child exists and it's not less
                    // we are done
                    break;
                }
            } else {
                // both left and right child exist
                // find out which is lower
                if ( isLess(A, leftChild, rightChild)) {
                    // left is less, is it less than i
                    if (isLess(A, leftChild, i)) {
                        swap(A, leftChild, i);
                        i = leftChild;
                    } else {
                        // left is smaller than right, but not less than i
                        // nothing more to do
                        break;
                    }
                } else {
                    // right is less than left
                    // is it less than i?
                    if (isLess(A, rightChild, i)) {
                        swap(A, rightChild, i);
                        i = rightChild;
                    } else {
                        // right is smaller than left, but smaller than i
                        // no more swaps needed
                        break;
                    }
                }
            }

            leftChild =  (2*i) + 1;
            rightChild =  (2*i) + 2;
        }
    }


    private boolean isLess (int[] A, int i, int j) {
        return (i < A.length) && (A[i] < A[j]);
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }



}
