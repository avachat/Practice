package avachat.leetcode.easy;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123 Output:  321 Example 2:
 *
 * Input: -123 Output: -321 Example 3:
 *
 * Input: 120 Output: 21
 *
 * Note: Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer
 * range. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 *
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */

/*
  MISTAKES :
  1. The overflow check needs to be done
     - BEFORE multiplying by sign for positive overflow
     - AFTER multiplying by sign for negative overflow

   2. sign is not needed

   3. Checking overflow in the loop improved running time

   4. Overflow checking should not need long. Another math operation should work.
   For example : store the result in temp. See if result / 10 is same as temp.
   Of course, this requires an extra math operation.

 */


public class Prob7ReverseInteger {

  /**
   * NOTE :
   *
   * 1. The number can be negative. In that case, a negative number must be returned.
   *
   * 2. The last digit can be zero. In that case, it should be ignored.
   *
   * 3. Must return 0 for overflows?? : Apparently YES
   */

  public int reverse(int x) {

    // 1. Note the sign : as a multiplier
    // 2. Divide by 0, find remainder, keep ignoring 0 : use a flag to begin multiplication
    // 3. Using a long for sending overflows :-(

    //long sign = (x >= 0) ? 1 : -1; // this needs to be long : for subsequent multiplication
    long result = 0;
    //long num = sign * x; // num is now always positive
    long num = x;

    //boolean flag = false ; // don't multiply till you see a non-zero

    while (num != 0) { // needed to handle both positive and negative numbers (prev condition was num > 0)
      // checking if removing this extra variable improves time
      // it did not : so mot sure why this solution is slow
      //
      //
      // OH ! Maybe the overflow can be checked inside the loop
      //
      //
      //long digit = num % 10; // remainder
      //result = (result * 10) + digit; // shift left prev result, add digit
      result = (result * 10) + (num % 10); // shift left prev result, add digit

      // check for negative overflow
      if (result < Integer.MIN_VALUE) {
        return 0;
      }

      // check for positive overflow
      if (result > Integer.MAX_VALUE) {
        return 0;
      }

      num = num / 10; //remove digit from num
    }

    // check for positive overflow
    //if (result > Integer.MAX_VALUE) {
    //return 0;
    //}

    // adjust sign
    //result *= sign;

    // check for negative overflow
    //if (result < Integer.MIN_VALUE) {
    //return 0;
    //}

    return (int) result;

  }


}
