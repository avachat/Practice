package avachat.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */
public class Prob1TwoSum {

  /*
  Given an array of integers, return indices of the two numbers such that they add up to a specific target.

  You may assume that each input would have exactly one solution, and you may not use the same element twice.

  Example:
  Given nums = [2, 7, 11, 15], target = 9,

  Because nums[0] + nums[1] = 2 + 7 = 9,
  return [0, 1].

   */


  /*
  NOTE : This should have been done in one pass!!!!!
  This does not need two passes
   */

  public int[] twoSum(int[] nums, int target) {

    Map<Integer, Integer> numsAvailable = new HashMap<>(nums.length);

    // first pass : store all the nums in a hashmap num -> index
    for (int i = 0; i < nums.length; i++) {
      numsAvailable.put(nums[i], i);
    }

    // second pass : see if the diff required is available
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      if ( numsAvailable.containsKey(diff)) {
        int diffIndex = numsAvailable.get(diff);
        if ( i != diffIndex ) {
          return new int[]{i, numsAvailable.get(diff)};
        }
      }
    }

    return null;
  }


}
