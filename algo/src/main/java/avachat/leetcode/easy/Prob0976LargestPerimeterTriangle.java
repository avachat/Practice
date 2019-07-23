package avachat.leetcode.easy;

import java.util.Arrays;

public class Prob0976LargestPerimeterTriangle {

    /*

    Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.



Example 1:

Input: [2,1,2]
Output: 5
Example 2:

Input: [1,2,1]
Output: 0
Example 3:

Input: [3,2,3,4]
Output: 10
Example 4:

Input: [3,6,2,3]
Output: 8


Note:

3 <= A.length <= 10000
1 <= A[i] <= 10^6
     */


    public int largestPerimeter(int[] A) {


        /*
        GOOD : Absolutely nothing :-( :-(

        BAD :
            1. First tried the brute force way, without realizing it is O(N^3)  :-( :-(
                It ran out of time
                Even then, there were many many mistakes in the impl
            2. Next realized it should be sorted and started form max
                Still implemented an O(N^3) 3 loops
                Took a long time to realize only one loop with examining 3 at a time is enough

         */



        // the array may have duplicate elements
        // there is no need to keep more than 3 copies of the elements

        // and only top 3 elements are needed
        Arrays.sort(A);
        // start looking from max
        // find the first successful triangle
        // if top 3 numbers cannot construct a triangle,
        // try the next 3

        for (int i = A.length-1; i >= 2; i--) {
            if (A[i] < (A[i-1] + A[i-2])) {
                return A[i] + A[i-1] + A[i-2];
            }
        }

        // 3 loops are NOT needed :-( :-(
        /*
        for (int i = A.length-1; i >= 2; i--) {
            for (int j = i-1; j >= 1; j--) {
                for (int k = j-1; j >= 0; j--) {
                    // i is the max
                    // j+k should be > i
                    if (A[i] < (A[j] + A[k])) {
                        return A[i] + A[j] + A[k];
                    }
                }

            }
        }
        */

        return 0;
    }



    public int largestPerimeterWrong(int[] A) {

        int max = 0;


        for (int i = 0; i < (A.length - 2); i++) {
            for (int j = i+1; j < (A.length -1); j++) {
                for (int k = j+1 ; k < A.length; k++) {
                    int area = getPerimeter(A[i], A[j], A[k]);
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }

        return max;
    }


    private int getPerimeter(int i, int j, int k) {

        if ( !possibleTriangle(i, j, k)) {
            return 0;
        }

        return i + j + k;
    }

    private boolean possibleTriangle(int i, int j, int k) {

        // do bubble sort to find the max and other two
        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
        // now i is < j
        if (j > k) {
            int tmp = j;
            j = k;
            k = tmp;
        }
        // now j < k
        // still i maybe > j  after this swap
        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
        // now i < j < k
        return (i + j) > k;

    }

}
