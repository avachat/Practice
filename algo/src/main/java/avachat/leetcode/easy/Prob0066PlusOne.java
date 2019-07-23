package avachat.leetcode.easy;

/*
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.
 */

/**
 * Created by Abhay Avachat on 2/16/18.
 */
public class Prob0066PlusOne {

  /*

   BAD : Original code assumed 0 to be a carryover.

   Missed simple tricks.
   1. Stop when digit is less than 9. It won't generate a carryover. Only copy is needed after that.
   2. If it's all 9, just return a new array with first number set to 1. Because Java initializes the array with all 0.

   See.

       int n = digits.length;
    for(int i=n-1; i>=0; i--) {
        if(digits[i] < 9) {
            digits[i]++;
            return digits;
        }

        digits[i] = 0;
    }

    int[] newNumber = new int [n+1];
    newNumber[0] = 1;

    return newNumber;

   */

  private static int[] nextDigit = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

  public int[] plusOne(int[] digits) {

    if ( digits.length == 0 ) {
      throw new IllegalArgumentException("Empty Array");
    }

    int[] nextNumber = new int[digits.length+1];

    // add 1 to the last digit
    int digitPlus1 = nextDigit[digits[digits.length-1]];
    // store it in answer at the end
    nextNumber[nextNumber.length-1] = digitPlus1;
    boolean carryover = (digitPlus1 == 0);

    for (int i = digits.length-2; i >= 0; i--) {
      int digit = digits[i];
      // was prevDigitPlus1 0? then it's a carryover : else just copy
      if (carryover) {
        digitPlus1 = nextDigit[digit];
        nextNumber[i+1] = digitPlus1;
        carryover = (digitPlus1 == 0);
      } else {
        // just copy
        nextNumber[i+1] = digit;
        carryover = false;
      }
    }

    // check final carryover
    if ( carryover) {
      nextNumber[0] = 1;
      // no need to move elements : this is the result
      return nextNumber;
    }

    // no final carryover happened : need to move left the result in nextNumber
    int[] result = new int[digits.length];
    System.arraycopy(nextNumber, 1, result, 0, result.length);
    return result;

  }

}
