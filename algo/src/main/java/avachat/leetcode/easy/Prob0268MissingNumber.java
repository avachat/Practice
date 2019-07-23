package avachat.leetcode.easy;

public class Prob0268MissingNumber {

    /*

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
     */



    public int missingNumber(int[] nums) {

        /*
        GOOD : Completely different approach than any mentioned at leetcode site
                This is more generic (does not depend on things being integers - will work on any ordered set)
                was able to simplify the code by removing unnecessary code

        BAD : Took a while to write the code, and simplify it
             Made many mistakes
              Not fast enough according to leetcode

         */

        if ( (null == nums) || (nums.length == 0)) {
            return 0;
        }

        // space for the highest number
        //int last = -1; // init to incorrect val

        for (int i = 0; i < nums.length; i++) {

            // start moving numbers to their proper positions
            int val = nums[i];
            // start the loop to move successive numbers to their correct positions
            // cannot move if the val is num.length
            while ((val < nums.length) && (nums[val] != val)) {
                int tmp = nums[val];
                nums[val] = val;
                val = tmp;
            }

        }

        // now everything is at the right position except one
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        // if everything was in the right place, the last number was missing
        return nums.length;

    }



}
