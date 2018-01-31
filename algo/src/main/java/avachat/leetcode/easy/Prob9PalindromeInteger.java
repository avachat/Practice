package avachat.leetcode.easy;

/**
 *
 * Determine whether an integer is a palindrome. Do this without extra space.

 click to show spoilers.

 Some hints:
 Could negative integers be palindromes? (ie, -1)

 If you are thinking of converting the integer to string, note the restriction of using extra space.

 You could also try reversing an integer.
 However, if you have solved the problem "Reverse Integer",
 you know that the reversed integer might overflow.

 How would you handle such case?

 There is a more generic way of solving this problem.
 *
 *
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */
public class Prob9PalindromeInteger {

  /*
     MISTAKES

     1. See the test case code. Many edge conditions were missed.

     The loop cannot handle single digits as written.

     Numbers ending in 0, were incorrectly marked as palindromes.

     The speed is still TOO SLOW. Not much difference with the solution. Not sure why this is slower.

   */

  public boolean isPalindrome(int x) {

    if (x < 0) {
      return false; // this is what the problem definition wants
    }

    // handle edge condition
    if ( x < 10) { // x is not negative here, and all single digits are palindromes
      return true;
    }

    // handle edge condition
    if ( x % 10 == 0 ) {
      return false; // anything that ends with 0 cannot be a palindrome
    }

    int reverseX = 0;

    // keep removing digits till x is greater than reverseX
    // and only till x becomes single digit
    // single digit is an edge condition not handled by logic inside the loop
    while ( (x >= 10) && (x > reverseX) ) {

      int digit = x % 10; // remove the last digit

      // new value for x
      x = x / 10;

      // if removing rightmost digit makes x and reverseX equal, then it's a palindrome
      // this will happen for x with odd number of digits
      if ( x == reverseX ) {
        return true;
      }

      int temp = reverseX; // for overflow checks

      // calculate new reverseX
      reverseX = (reverseX * 10) + digit;

      // did overflow occur?
      if ( (reverseX / 10) != temp) {
        return false;
      }

      // if adding the rightmost digit makes them equal, it's a palindrome
      // would happen for x with even number of digits
      if ( x == reverseX) {
        return true;
      }

    }

    // all palindromes are detected in the loop
    return false;
  }



  public boolean isPalindromeDoesNotWork(int x) {

    // handle edge condition
    if ( x == 0) {
      return true;
    }

    if (x < 0) {
      return false; // this is what the problem definition wants
    }

    // The following is needed ONLY IF -ve numbers are to be considered palindromes
    /***********
     // convert x to positive
     // check for overflow first
     if ( x == Integer.MIN_VALUE) {
     return false;
     }
     if (x < 0) {
     x = x * -1;
     }
     ***********/

    int reverseX = 0;
    boolean is_odd_digit = false;
    while ( x != 0 ) {

      int digit = x % 10; // remove the last digit
      is_odd_digit = !is_odd_digit; // flip the flag

      // new value for x
      x = x / 10;

      // does x equal to reverseX : will be true for palindrome with odd digits
      if ( is_odd_digit && (x == reverseX)) {
        return true;
      }

      int temp = reverseX; // for overflow checks

      // calculate new reverseX
      reverseX = (reverseX * 10) + digit;

      // did overflow occur?
      if ( (reverseX / 10) != temp) {
        return false;
      }

      // does X equal to reverseX now? will be true for even number palindromes
      if ( (!is_odd_digit) && (x == reverseX)) {
        return true;
      }
    }

    return false;
  }

}
