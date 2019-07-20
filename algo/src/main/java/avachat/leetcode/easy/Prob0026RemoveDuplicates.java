package avachat.leetcode.easy;


/*

Given a sorted array, remove the duplicates in-place such that
each element appear only once and return the new length.

Do not allocate extra space for another array,
you must do this by modifying the input array in-place with O(1) extra memory.

Example:

Given nums = [1,1,2],

Your function should return length = 2,
with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.

 */

public class Prob26RemoveDuplicates {

    /*

    GOOD : First attempt accepted with good execution time.

    BAD : Took a while to write code that avoids unnecessary overwriting and takes care of all edge conditions

     */

    public int removeDuplicates(int[] nums) {

        if ( nums.length <= 1) {
            return nums.length;
        }

        // find FIRST 2 identical numbers before the copying loop
        int copyToIndex = 0;
        int copyFromIndex = 1;

        while ((copyFromIndex < nums.length) && (nums[copyFromIndex] != (nums[copyToIndex]))){
            copyFromIndex++;
            copyToIndex++;
        }

        // if end of array reached, all nums were unique, return
        if ( copyFromIndex == nums.length) {
            return nums.length;
        }

        // At this point both indices point to elements that are identical
        // copyToIndex should be incremented to point to the place of overwriting
        copyFromIndex++; // it's possible for copyFromIndex to be == nums.length
        copyToIndex++; // max value after increment can be nums.length-1 when only last 2 nums are same
        // value of copyToIndex indicates the position that should be overwriiten
        // it will always be 1 more than the unique numbers so far

        while (copyFromIndex < nums.length) {
            if ( nums[copyFromIndex] == nums[copyToIndex-1]) {
                copyFromIndex++;
            } else {
                nums[copyToIndex] = nums[copyFromIndex];
                copyFromIndex++;
                copyToIndex++;
            }
        }

        return copyToIndex; // will always be one + num of uniques

    }
}
