package avachat.leetcode.medium;

public class Prob0033SearchInRotatedArray {

    /*

    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1


     */


    public int search(int[] nums, int target) {

        if ( (nums == null) || (nums.length == 0) ) {
            return -1;
        }

        if (nums.length == 1) {
            return (nums[0] == target) ? 0 : -1;
        }

        // now nums has at least 2 elements

        if (nums[0] < nums[nums.length-1]) {
            // perfectly sorted array
            return binarySearch(nums, 0, nums.length-1, target);
        }

        // now nums has a pivot
        int pivot = findPivot(nums, 0, nums.length-1);

        // if target is > than end, target is in left segment
        if (target > nums[nums.length-1]) {
            return binarySearch(nums, 0, pivot-1, target);
        } else {
            return binarySearch(nums, pivot, nums.length, target);
        }


    }


    private int binarySearch(int[] nums, int left, int right, int target) {

        if (left == right) {
            return (nums[left] == target) ? left : -1;
        }

        int mid = left + (right - left) / 2;

        if (target == nums[mid]) {
            return mid;
        } else if (target < nums[mid]) {
            return binarySearch(nums, left, mid, target);
        } else {
            return binarySearch(nums, mid+1, right, target);
        }
    }


    /**
     * ASSUMPTION : There are no duplicates in the array
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int findPivot(int[] nums, int left, int right) {

        /*
        if (left >= right) {
            // should not happen
            return -1;
        }
        */

        /*
        if (nums[left] < nums[right]) {
            // then this is a sorted segment, no pivot here
            return -1;
        }
        */

        // is it a two element segment
        /*
        if ((left+1) == right) {
            if (nums[right] < nums[left]) {
                return right;
            } else {
                return -1;
            }
        }
        */

        if ((left+1) == right) {
            // pivot MUST be here
            // see commented code above
            return right;
        }

        // at this point segment has at least 3 elements
        int mid = left + (right - left) / 2;

        // see which side will have the pivot
        // if both are correctly sorted
        // then the pivot is mid+1 !!!
        // Because we have already established that this segment must contain a pivot
        // So if the mid divides this segment into two sorted segments, mid+1 is the pivot
        if (nums[left] > nums[mid]) {
            return findPivot(nums, left, mid);
        } else if (nums[mid+1] > nums[right]) {
            return findPivot(nums, mid+1, right);
        } else {
            return mid + 1;
        }

    }
}
