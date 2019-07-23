package avachat.leetcode.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */
public class Prob9PalindromeIntegerTest {

  @Test
  public void isPalindrome() {

    Prob0009PalindromeInteger testObj = new Prob0009PalindromeInteger();

    assertFalse(testObj.isPalindrome(10)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(100)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(1000)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(20)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(200)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(2000)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(23));
    assertFalse(testObj.isPalindrome(203));
    assertFalse(testObj.isPalindrome(2003));

    assertTrue(testObj.isPalindrome(0)); // orig code failed this test case
    assertTrue(testObj.isPalindrome(1)); // orig code failed this test case
    assertTrue(testObj.isPalindrome(11));
    assertTrue(testObj.isPalindrome(111));
    assertTrue(testObj.isPalindrome(121));
    assertTrue(testObj.isPalindrome(1221));
    assertTrue(testObj.isPalindrome(2147447412));

    assertTrue(testObj.isPalindrome(101));
    assertTrue(testObj.isPalindrome(1001));
    assertTrue(testObj.isPalindrome(10001));
    assertTrue(testObj.isPalindrome(202));
    assertTrue(testObj.isPalindrome(2002));
    assertTrue(testObj.isPalindrome(20002));

    assertFalse(testObj.isPalindrome(-2147483648)); // orig code failed this test case
    assertFalse(testObj.isPalindrome(-2147447412)); // orig code failed this test case

  }

}