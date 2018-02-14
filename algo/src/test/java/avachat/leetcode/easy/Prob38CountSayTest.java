package avachat.leetcode.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 2/8/18.
 */
public class Prob38CountSayTest {

  @Test
  public void countAndSay() throws Exception {

    Prob38CountSay testObj = new Prob38CountSay();

    assertEquals("1", testObj.countAndSay(1));
    assertEquals("11", testObj.countAndSay(2));
    assertEquals("21", testObj.countAndSay(3));
    assertEquals("1211", testObj.countAndSay(4));
    assertEquals("111221", testObj.countAndSay(5));
    assertEquals("312211", testObj.countAndSay(6));
    assertEquals("13112221", testObj.countAndSay(7));
    assertEquals("1113213211", testObj.countAndSay(8));
    assertEquals("31131211131221", testObj.countAndSay(9));
    assertEquals("13211311123113112211", testObj.countAndSay(10));

  }

}