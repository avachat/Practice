package avachat.leetcode.easy;

public class Prob0189RotateArray {

    /*


    Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

     */

    public void rotate(int[] nums, int k) {

        /*

        GOOD : Eventually came up with a very efficient solution

        BAD : Took a long long time to figure out the handling of cycles
        and took couple of tries to correct mistakes
         */

        if ( nums.length <= 1) {
            return;
        }

        int steps = k % nums.length; // take mod
        if ( k== 0 ) {
            return;
        }

        int cycleStart = 0;
        int val = nums[0]; // store the val that should be moved to the next location
        int to = steps;
        int i = 0;
        while (i < nums.length-1) {
            i++;
            int temp = nums[to];
            nums[to] = val;
            val = temp; // val will be copied forward
            to = (to + steps) % nums.length;
            if (to == cycleStart) {
                i++;
                nums[to] = val; // finish the current cycle
                cycleStart++; // start the new cycle
                val = nums[cycleStart];
                to = cycleStart + steps;
            }
        }
    }


}
