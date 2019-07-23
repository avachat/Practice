package avachat.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 13
 *
 * Created by Abhay Avachat on 1/31/18.
 */

/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 */

public class Prob0013RomanToInt {

  /*
      GOOD : Got accepted the first time with all test cases.

      BAD : The second case is not needed. No need to keep a subset total. Only 1 char value can be deleted.
   */

  static Map<Character, Integer> romanChars = null;

  private void init() {
    if (romanChars != null) {
      return;
    }
    romanChars = new HashMap<>(7);
    romanChars.put('I', 1);
    romanChars.put('V', 5);
    romanChars.put('X', 10);
    romanChars.put('L', 50);
    romanChars.put('C', 100);
    romanChars.put('D', 500);
    romanChars.put('M', 1000);
  }

  private int romanCharValue(char c) {
    if (!romanChars.containsKey(c)) {
      throw new IllegalArgumentException("No value for " + c);
    }
    return romanChars.get(c);
  }


  public int romanToInt(String romanStr) {

    if ( (null == romanStr) || (romanStr.trim().isEmpty())) {
      return 0;
    }

    init();

    int romanVal = 0; // cumulative
    int prevCharVal = romanCharValue(romanStr.charAt(0));
    int subsetTotal = prevCharVal; // current string total : will keep getting added till we see the same char

    for (int i = 1; i < romanStr.length(); i++) {
      int currCharVal = romanCharValue(romanStr.charAt(i));

      // case 1 :
      // if the current char value is less, no subtraction needed
      if (currCharVal < prevCharVal) {
        // add the prev char value to the cumulative val
        romanVal += subsetTotal;
        // move the prevCharVal to currCharVal
        prevCharVal = currCharVal;
        subsetTotal = currCharVal;
      }
      // case 2
      // current char is same as prev char
      // keep the total - it may have to be subtracted later
      else if (currCharVal == prevCharVal) {
        // only the subset total goes up
        // here we can add check to see if there are more than three characters in the subset
        subsetTotal += currCharVal;
        // no need to change prevCharVal
      }
      // case 3
      // a higher char was seen : so subtraction needed
      else {
        if (currCharVal < subsetTotal) {
          throw new RuntimeException("Bug : subsetTotal " + subsetTotal + " > currCharVal " + currCharVal);
        }
        if (subsetTotal <= 0) {
          throw new RuntimeException("Bug : subsetTotal " + subsetTotal + " > currCharVal " + currCharVal);
        }
        romanVal += (currCharVal - subsetTotal);
        // after subtractions : the next char must be lower in a proper string : can add that check here
        prevCharVal = currCharVal;
        subsetTotal = 0;
      }

    }

    // the subsetTotal may not be zero at the end : eg XVIII

    return romanVal + subsetTotal;
  }


}
