package avachat.leetcode.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */
public class Prob9PalindromeIntegerTest {

  @Test
  public void isPalindrome() throws Exception {

    Prob9PalindromeInteger testObj = new Prob9PalindromeInteger();

    assertFalse(testObj.isPalindrome(10));
    assertFalse(testObj.isPalindrome(100));
    assertFalse(testObj.isPalindrome(1000));

    assertTrue(testObj.isPalindrome(0));
    assertTrue(testObj.isPalindrome(1));
    assertTrue(testObj.isPalindrome(11));
    assertTrue(testObj.isPalindrome(111));
    assertTrue(testObj.isPalindrome(121));
    assertTrue(testObj.isPalindrome(1221));
    assertTrue(testObj.isPalindrome(2147447412));

    assertFalse(testObj.isPalindrome(-2147483648));
    assertFalse(testObj.isPalindrome(-2147447412));

  }

}