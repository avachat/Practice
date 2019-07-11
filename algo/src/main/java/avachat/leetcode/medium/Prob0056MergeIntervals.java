package avachat.leetcode.medium;

import java.util.Arrays;

public class Prob0056MergeIntervals {


    /*

    Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

     */


    /*

    Strategy : Comparing all pairs will be O(N^2)

    Consider 3 pairs [a1, a2]  [b1 b2]  [c1 c2]
    We know a1 < a2, b1 < b2, c1< c2
    And assume they are sorted a1 < b1 < c1
    If b does not overlap with a, then b1 > a2, and so c1 must be > a2
    So if b does not overlap with a, then c definitely won't
    So comparing only the previous interval is enough

     */

    public int[][] merge(int[][] intervals) {

        /*

        GOOD : Got it correct in first try

        BAD : Time complexity bad?? This in O(nlogn) Maybe because the input has changed this year??
         */

        if ( (intervals == null) || (intervals.length == 0)) {
            return intervals;
        }

        // sort the array - based on the interval start, and if they are same, interval end
        Arrays.sort(intervals, (a,b) -> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]);

        // result is at most as big a intervals
        int[][] result = new int[intervals.length][2];
        // copy the first interval
        result[0] = intervals[0];
        int count = 1; // count of merged intervals

        // start looking from 2nd interval onwards
        for (int i = 1; i < intervals.length; i++) {

            // compare it with previous interval
            int[] merged = result[count-1];
            int[] current = intervals[i];

            // do the overlap
            if (current[0] <= merged[1]) {
                // current starts before merge ends
                // update where merged ends
                merged[1] = Math.max(current[1], merged[1]);
            } else {
                // new entry in result
                result[count] = current;
                count++;
            }
        }

        // did anything merge?
        if (count == intervals.length) {
            return intervals;
        }

        return Arrays.copyOf(result, count);

    }
}
