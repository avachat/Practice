package avachat.leetcode.easy;

public class Prob0628MaxProd3Nums {

    /*
    Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6


Example 2:

Input: [1,2,3,4]
Output: 24


Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

     */

    public int maximumProduct(int[] nums) {

        /*
        GOOD : Quickly implemented, faster than 99.5%

        BAD : Realized that there would be negative numbers, but incorrectly deduced that they won't matter
            In the case where the 2 most negative numbers are in absolute terms give higher product than low and med
                they need to be used.
         */

        // the array is guaranteed to have 3 numbers
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i : nums) {
            if ( i > max1) {
                max3 = max2;
                max2 = max1;
                max1 = i;
            } else if ( i > max2) {
                max3 = max2;
                max2 = i;
            } else if ( i > max3) {
                max3 = i;
            }
            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
        }

        return Math.max(max1 * max2 * max3,   max1 * min1 *min2);

    }
}
