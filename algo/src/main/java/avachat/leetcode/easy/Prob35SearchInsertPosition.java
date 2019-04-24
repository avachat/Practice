package avachat.leetcode.easy;

public class Prob35SearchInsertPosition {

    /*
    Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
     */


    public int searchInsert(int[] nums, int target) {

        if ( (null == nums) || (0 == nums.length)) {
            throw new IllegalArgumentException("nums is empty:)");
        }

        return searchInsertRecursive(nums, 0, nums.length-1, target);
    }

    private int searchInsertRecursive(int[] nums, int startIndex, int endIndex, int target) {

        /*
        # stupid errors : see below
         */

        if (startIndex == endIndex) {
            if (nums[startIndex] == target) {
                return startIndex;
            }
            if ( target > nums[startIndex] ) { // MISSED : initial comparison was target > startIndex :-(
                return startIndex+1;
            } else {
                return startIndex;
                /*
                if (startIndex == 0) {
                    return 0;
                } else {
                    return startIndex; // MISSED : initiallyw as returning startIndex - 1
                }
                */
            }
        }

        int middle = (startIndex + endIndex)/2; // MISSED : Divide by 2
        if ( target == nums[middle]) {
            return middle;
        } else if ( target < nums[middle]) {
            return searchInsertRecursive(nums, startIndex, middle, target);
        } else {
            return searchInsertRecursive(nums, middle+1, endIndex, target);
        }
    }


    private int searchInsertIterative(int [] nums, int target) {

        /*
        Got it right the first time
         */

        int start = 0;
        int end = nums.length - 1;

        while (true) {

            if ( start == end) {
                if (target > nums[start]) {
                    return start+1;
                } else {
                    return start;
                }
            }

            int middle = (start + end) / 2;
            if ( target == nums[middle]) {
                return middle;
            } else if (target < nums[middle]) {
                end = middle;
            } else {
                start = middle+1;
            }
        }
    }

}
