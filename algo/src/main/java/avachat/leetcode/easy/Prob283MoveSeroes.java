package avachat.leetcode.easy;

public class Prob283MoveSeroes {

    /*
    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
     */


    public void moveZeroes(int[] nums) {

        /*
        GOOD : Got in first try

        BAD : Took a while to implement.
              Leetcode says this is slow. Not sure why. Their solutions don't look that optimal either.
         */

        if ((null == nums) || (nums.length <= 1)) {
            return;
        }

        int to = 0;
        int from = 1;
        while (true) {
            while ( (nums[to] != 0) && (to <= (nums.length -2) )  ) {
                to++;
            }
            if ( to >= (nums.length -1)) {
                break;
            }
            from = to + 1;
            while ( (from < nums.length) && (nums[from] == 0)) {
                from ++;
            }
            if (from == nums.length) {
                break;
            }
            nums[to] = nums[from];
            nums[from] = 0;
        }

    }




}
