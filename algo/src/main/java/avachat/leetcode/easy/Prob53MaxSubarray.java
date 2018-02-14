package avachat.leetcode.easy;

/*
Find the contiguous subarray within an array
(containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 */

/**
 * Classic.
 *
 * Created by Abhay Avachat on 2/8/18.
 */
public class Prob53MaxSubarray {


  /*

  GOOD : Submitted without test cases, just tested the code in mind, and was accepted

  BAD : Took a while to simplify the code

   */

  public int maxSubArray(int[] nums) {

    if (nums.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty");
    }

    // These vars will always hold the max subarray
    //
    //int maxStart = 0;
    //int maxEnd = 0;
    int maxSum = nums[0];

    // These vars will hold the candidate set being examined
    //
    //int candidateStart = 0;
    //int candidateEnd = 0;
    int candidateSum = nums[0];

    for (int i = 1; i < nums.length; i++) {

      // get the next num
      int currNum = nums[i];

      // if prev candidate was NOT positive
      // start a new candidate
      if ( candidateSum <= 0) {
        //candidateStart = i;
        //candidateEnd = i;
        candidateSum = currNum;
        // is this single number better than max
        if (candidateSum > maxSum) {
          //maxStart = i;
          //maxEnd = i;
          maxSum = currNum;
        }
        continue;
      }

      // expand the candidate
      candidateSum += currNum;
      //candidateEnd = i;

      // check if expanded candidate is better
      if (candidateSum > maxSum) {
        // max and candidate are same at whenever new candidate is found
        //maxStart = candidateStart;
        //maxEnd = candidateEnd;
        maxSum = candidateSum;
      }

    }

    return maxSum;

  }

}
