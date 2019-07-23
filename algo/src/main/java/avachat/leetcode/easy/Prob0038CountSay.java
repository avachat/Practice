package avachat.leetcode.easy;

/*

The count-and-say sequence is the sequence of integers with the first five terms
as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Problem 38 count and say.
 *
 * Created by Abhay Avachat 212552612 on 2/8/18.
 */
public class Prob0038CountSay {

  /*

  GOOD : Quickly done, correct on first attempt

  BAD : Needed a test case to detect incorrect for loop edge conditions

   */

  private List<Integer> generateNext(List<Integer> sequence) {

    List<Integer> result = new ArrayList<>();

    if (sequence.isEmpty()) {
      return Collections.emptyList();
    }

    Iterator<Integer> iter = sequence.iterator();
    int prevNum = iter.next();
    int prevCount = 1;

    while (iter.hasNext()) {
      int num = iter.next();
      if (num == prevNum) {
        prevCount++;
      } else {
        result.add(prevCount);
        result.add(prevNum);
        prevNum = num;
        prevCount = 1;
      }
    }

    // add the last remaining num
    result.add(prevCount);
    result.add(prevNum);

    return result;
  }

  public String countAndSay(int n) {

    if ( n <= 0 ) {
      throw new IllegalArgumentException("N must be > 0");
    }

    List<Integer> list = Collections.singletonList(1);

    for (int i = 1; i < n; i++) {
      list = generateNext(list);
    }

    StringBuilder builder = new StringBuilder();
    for (int num : list) {
      builder.append(num);
    }

    return builder.toString();
  }

}
