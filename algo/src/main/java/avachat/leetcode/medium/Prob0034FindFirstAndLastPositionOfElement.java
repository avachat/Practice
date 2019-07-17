package avachat.leetcode.medium;

public class Prob0034FindFirstAndLastPositionOfElement {

    /*

    Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

     */




    private int findLeftMost(int nums[], int left, int right) {

        // assumption num[right] == target
        // left is the leftmost bound for target
        if (right == left) {
            return left;
        }

        int mid = left + (right-left)/2;
        // NOTE : mid is strictly < right here

        if (nums[mid] == nums[right]) {
            // mid has the target, so leftmost is NOT on the right side
            // search on the left side
            // NOTE : rightmost index still has the target
            //      : left is still the leftmost bound
            return findLeftMost(nums, left, mid);
        } else {
            // mid does not have the target, so it's the new left bound
            // NOTE : rightmost index still has the target
            return findLeftMost(nums, mid+1, right);
        }
    }


    private int findRightMost(int nums[], int left, int right) {

        // assumption : num[left] == target
        // right is the rightmost bound for the target
        if (left == right) {
            return right;
        }

        /*
        if (nums[left] != nums[left+1]) {
            return left;
        }
        */

        int mid = left + (right - left)/2;
        // NOTE : mid is strictly < right
        // BUT : mid >= left : so mid == left (must be handled : will happen for segment size 2)

        if ( nums[mid] != nums[left]) {
            // NOTE : leftmost index still has the target
            // mid does not have the target, so it becomes the new rightmost bound
            // it also must be true that mid is strictly > left
            return findRightMost(nums, left, mid);
        } else if (nums[mid+1] != nums[left]) {
            // so we are not searching on the left side
            // if we simply search on the right side between mid+1 and right, we will mishandle the edge condition
            // where mid is the rightmost bound and mid+1 has a higher number
            // so before recursively searching in mid+1 to right, we have to ensure that mid is not the rightmost
            return mid;
        } else {
            // mid+1 is same as left, so we search from mid+1 to right
            // NOTE : leftmost index still has the target
            return findRightMost(nums, mid+1, right);
        }
    }

    private int findWithBinarySearch(int nums[], int left, int right, int target) {

        if (left == right) {
            return (nums[left]==target) ? left : -1;
        }

        int mid = left + (right-left)/2;

        if (target == nums[mid]) {
            return mid;
        } else if (target < nums[mid]) {
            return findWithBinarySearch(nums, left, mid, target);
        } else {
            return findWithBinarySearch(nums, mid+1, right, target);
        }
    }


    public int[] searchRange(int[] nums, int target) {

        if ( (null == nums) || (nums.length == 0)) {
            return new int[]{-1, -1};
        }

        int index = findWithBinarySearch(nums, 0, nums.length-1, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }

        // so the number exists
        // search for leftmost and rightmost in 2 halves
        // we need to do both searches with 'index' inclusive range

        int leftMost = findLeftMost(nums, 0, index);
        int rightMost = findRightMost(nums, index, nums.length-1);

        return new int[]{leftMost, rightMost};
    }

}
