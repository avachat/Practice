package avachat.leetcode.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by Abhay Avachat 212552612 on 1/31/18.
 */
public class Prob13RomanToIntTest {

  @Test
  public void romanToInt() throws Exception {

    Prob13RomanToInt testObj = new Prob13RomanToInt();

    assertEquals(1, testObj.romanToInt("I"));
    assertEquals(2, testObj.romanToInt("II"));
    assertEquals(3, testObj.romanToInt("III"));
    assertEquals(4, testObj.romanToInt("IV"));
    assertEquals(5, testObj.romanToInt("V"));
    assertEquals(6, testObj.romanToInt("VI"));
    assertEquals(7, testObj.romanToInt("VII"));
    assertEquals(8, testObj.romanToInt("VIII"));
    assertEquals(9, testObj.romanToInt("IX"));
    assertEquals(10, testObj.romanToInt("X"));
    assertEquals(11, testObj.romanToInt("XI"));
    assertEquals(12, testObj.romanToInt("XII"));
    assertEquals(13, testObj.romanToInt("XIII"));
    assertEquals(14, testObj.romanToInt("XIV"));
    assertEquals(15, testObj.romanToInt("XV"));
    assertEquals(16, testObj.romanToInt("XVI"));
    assertEquals(17, testObj.romanToInt("XVII"));
    assertEquals(18, testObj.romanToInt("XVIII"));
    assertEquals(19, testObj.romanToInt("XIX"));
    assertEquals(20, testObj.romanToInt("XX"));
    assertEquals(21, testObj.romanToInt("XXI"));
    assertEquals(24, testObj.romanToInt("XXIV"));
    assertEquals(25, testObj.romanToInt("XXV"));
    assertEquals(26, testObj.romanToInt("XXVI"));
    assertEquals(30, testObj.romanToInt("XXX"));
    assertEquals(31, testObj.romanToInt("XXXI"));
    assertEquals(33, testObj.romanToInt("XXXIII"));
    assertEquals(34, testObj.romanToInt("XXXIV"));
    assertEquals(35, testObj.romanToInt("XXXV"));
    assertEquals(36, testObj.romanToInt("XXXVI"));
    assertEquals(40, testObj.romanToInt("XL"));
    assertEquals(41, testObj.romanToInt("XLI"));
    assertEquals(43, testObj.romanToInt("XLIII"));
    assertEquals(44, testObj.romanToInt("XLIV"));
    assertEquals(45, testObj.romanToInt("XLV"));
    assertEquals(46, testObj.romanToInt("XLVI"));
    assertEquals(49, testObj.romanToInt("IL"));
    assertEquals(88, testObj.romanToInt("LXXXVIII"));
    assertEquals(89, testObj.romanToInt("LXXXIX"));
    assertEquals(90, testObj.romanToInt("XC"));
    assertEquals(94, testObj.romanToInt("XCIV"));
    assertEquals(194, testObj.romanToInt("CXCIV"));

    // from wikipedia
    assertEquals(39, testObj.romanToInt("XXXIX"));
    assertEquals(204, testObj.romanToInt("CCIV"));
    assertEquals(207, testObj.romanToInt("CCVII"));
    assertEquals(246, testObj.romanToInt("CCXLVI"));
    assertEquals(1066, testObj.romanToInt("MLXVI"));
    assertEquals(1776, testObj.romanToInt("MDCCLXXVI"));
    assertEquals(1954, testObj.romanToInt("MCMLIV"));
    assertEquals(1990, testObj.romanToInt("MCMXC"));
    assertEquals(2014, testObj.romanToInt("MMXIV"));

  }

}