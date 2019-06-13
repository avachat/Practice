package avachat.leetcode.easy;

public class Prob674LongestContIncrSubseq {

    /*
    Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
Note: Length of the array will not exceed 10,000.


     */


    public int findLengthOfLCIS(int[] nums) {


        /*
        GOOD : First try right, better than  100% on both time and memory
         */

        if (nums == null) {
            return 0;
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        int end = 0;
        int maxLen = 1;
        int currentLen = 1;

        while (end < (nums.length -1)) {

            if ( nums[end+1] > nums[end] ) {
                // current sequence can be extended
                currentLen ++;
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                }
            } else {
                // current sequence has ended
                // start a new sequence
                currentLen = 1;
            }

            end++;
        }

        return maxLen;

    }

}
