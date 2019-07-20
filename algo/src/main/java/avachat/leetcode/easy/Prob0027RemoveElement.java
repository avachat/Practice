package avachat.leetcode.easy;

/*
Given an array and a value,
remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array,
you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed.
It doesn't matter what you leave beyond the new length.

Example:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

 */

/**
 * Problem 27.
 *
 * Created by Abhay Avachat on 2/7/18.
 */
public class Prob27RemoveElement {


  /*
  GOOD : Got it in first submission

  BAD : Initially wrote the do-while loop as while
  missing that removeAt should be incremented first and then checked
   */


  public int removeElement(int[] nums, int val) {

    // find the first place to remove the element
    int removeAt = 0;
    while ( (removeAt < nums.length) && (nums[removeAt] != val)) {
      removeAt++;
    }

    // check if no val is found
    if (removeAt == nums.length) {
      return removeAt;
    }

    // find first replacement
    // start from end
    // keep decrementing, stop when
    // replacement is not val
    // or
    // replacement index collides with removeAt
    int replacement = nums.length-1;
    while ( (replacement > removeAt) && (nums[replacement] == val) ) {
      replacement--;
    }

    // did the two pointer collide
    if ( replacement == removeAt) {
      return removeAt;
    }

    // keep replacing
    // after each overwrite, increment removeAt, decrement replacement
    // stop when replacement is no longer higher than removeAt
    while (removeAt < replacement) {
      // replace it
      nums[removeAt] = nums[replacement];
      // advance removeAt : only till replacement
      do {
        removeAt++;
      } while ( (removeAt < replacement) && (nums[removeAt] != val));

      // decrement replacement only till removeAt
      do {
        replacement--;
      } while ( (replacement > removeAt) && (nums[replacement] == val) );
    }

    // removeAt has been incremented and indicates new length
    return removeAt;

  }
}
