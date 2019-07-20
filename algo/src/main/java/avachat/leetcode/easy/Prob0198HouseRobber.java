package avachat.leetcode.easy;

public class Prob198HouseRobber {


    /*

    You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed,
    the only constraint stopping you from robbing each of them is that
    adjacent houses have security system connected
    and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

     */


    public int rob(int[] nums) {

        /*
        GOOD : Got the algorithm quickly and implemented it in first try!

        BAD : 1. Took a while to get the swaps right
              2. Missed the initialization of sumBefore2 and sumBefore1 to first 2 elements

        TBD : Instead of keeping 3 sums, think of implementing iterative solution of a recursive strategy
        https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
         */

        if ( (null == nums) || (nums.length == 0)) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return (nums[0] > nums[1]) ? nums[0] : nums[1];
        }

        int sumBefore3 = 0;
        int sumBefore2 = nums[0];
        int sumBefore1 = nums[1];

        for (int i = 2; i < nums.length; i++) {

            int candidate3 = sumBefore3 + nums[i];
            int candidate2 = sumBefore2 + nums[i];
            int newSumBefore1 = (candidate2 > candidate3) ? candidate2 : candidate3;
            sumBefore3 = sumBefore2;
            sumBefore2 = sumBefore1;
            sumBefore1 = newSumBefore1;

        }

        int max = (sumBefore1 > sumBefore2) ? sumBefore1 : sumBefore2;
        max = (max > sumBefore3) ? max : sumBefore3;

        return max;
    }

}
