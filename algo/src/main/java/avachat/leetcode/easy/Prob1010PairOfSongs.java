package avachat.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class Prob1010PairOfSongs {

    /*

    In a list of songs, the i-th song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.



Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.


Note:

1 <= time.length <= 60000
1 <= time[i] <= 500

     */

    public int numPairsDivisibleBy60(int[] time) {

        /*

        GOOD : Got it correct in 2nd try
            Did not write two loops

        BAD : Could have used array
            Missed the test case (in the test code now)

         */

        Map<Integer, Integer> countsMap = new HashMap<>(60);

        int pairs = 0;

        for (int i : time) {
            int mod = i % 60;

            // is there a matching mod
            int required = (mod == 0) ? 0 : 60 - mod;
            pairs += countsMap.getOrDefault(required, 0);

            // update the count
            int count = countsMap.getOrDefault(mod, 0);
            countsMap.put(mod, count+1);
        }

        return pairs;

    }

}
