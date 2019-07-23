package avachat.leetcode.easy;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/30/18.
 */
public class Prob7ReverseIntegerTest {

  private static Prob0007ReverseInteger testObj = null;

  @BeforeClass
  public static void setup() {
    testObj = new Prob0007ReverseInteger();
  }

  @Test
  public void testPositive() {
    Assert.assertEquals(321, testObj.reverse(123));
    Assert.assertEquals(222, testObj.reverse(222));
    Assert.assertEquals(540321, testObj.reverse(123045));
    Assert.assertEquals(123456, testObj.reverse(654321));
    Assert.assertEquals(123456789, testObj.reverse(987654321));
  }

  @Test
  public void testNegative() {
    Assert.assertEquals(-321, testObj.reverse(-123));
    Assert.assertEquals(-222, testObj.reverse(-222));
    Assert.assertEquals(-540321, testObj.reverse(-123045));
    Assert.assertEquals(-654321, testObj.reverse(-123456));
  }

  @Test
  public void testEndingInZero() {
    Assert.assertEquals(321, testObj.reverse(1230));
    Assert.assertEquals(321, testObj.reverse(12300));
    Assert.assertEquals(321, testObj.reverse(123000));
  }

  @Test
  public void testOverflow() {
    Assert.assertEquals(0, testObj.reverse(-1563847412));
    Assert.assertEquals(0, testObj.reverse(1563847412));
  }


}