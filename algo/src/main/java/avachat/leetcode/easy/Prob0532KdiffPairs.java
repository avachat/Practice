package avachat.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Prob532KdiffPairs {

    /*

    Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

     */


    public int findPairs(int[] nums, int k) {

        /*
          GOOD : Quickly got the solution, faster than 85%

          BAD : The problem is weird : Need to handle k == 0 and k < 0 cases.
         */

        if ( k < 0 ) {
            return 0;
        }

        if ( k == 0) {

            Map<Integer, Integer> map = new HashMap<>(nums.length);

            for (int i : nums) {
                int occurrence = map.getOrDefault(i, 0);
                map.put(i, occurrence+1);
            }

            int count = 0;
            for (int i : map.values()) {
                if (i > 1) {
                    count++;
                }
            }

            return count;
        }

        Set<Integer> set = new HashSet<>(nums.length);
        for (int i : nums) {
            set.add(i);
        }

        int count = 0;

        for (int i : set) {
            long n1 = (long)i + k;
            long n2 = (long)i - k;

            if ( n1 <= Integer.MAX_VALUE) {
                if ( set.contains((int)n1)) {
                    count++;
                }
            }

            if ( n2 >= Integer.MIN_VALUE) {
                if ( set.contains((int)n1)) {
                    count++;
                }
            }
        }

        return count/2;

    }

}
