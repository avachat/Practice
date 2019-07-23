package avachat.leetcode.easy;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Created by Abhay Avachat 212552612 on 2/16/18.
 */
public class Prob66PlusOneTest {

  @Test
  public void testPlusOne() throws Exception {

    Prob0066PlusOne testObj = new Prob0066PlusOne();

    assertEquals(new int[] {1}, testObj.plusOne(new int[] {0}));
    assertEquals(new int[] {2}, testObj.plusOne(new int[] {1}));
    assertEquals(new int[] {7}, testObj.plusOne(new int[] {6}));
    assertEquals(new int[] {1,0}, testObj.plusOne(new int[] {9}));
    assertEquals(new int[] {1,1}, testObj.plusOne(new int[] {1,0}));
    assertEquals(new int[] {1,6}, testObj.plusOne(new int[] {1,5}));
    assertEquals(new int[] {2,0}, testObj.plusOne(new int[] {1,9}));
    assertEquals(new int[] {1,0,0}, testObj.plusOne(new int[] {9,9}));
    assertEquals(new int[] {1,0,1}, testObj.plusOne(new int[] {1,0,0}));
    assertEquals(new int[] {1,1,0}, testObj.plusOne(new int[] {1,0,9}));
    assertEquals(new int[] {1,0,0,1,0}, testObj.plusOne(new int[] {1,0,0,0,9}));

  }

}