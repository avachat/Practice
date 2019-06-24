package avachat.leetcode.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Prob933NumberOfRecentCalls {

    /*
    Write a class RecentCounter to count recent requests.

It has only one method: ping(int t), where t represents some time in milliseconds.

Return the number of pings that have been made from 3000 milliseconds ago until now.

Any ping with time in [t - 3000, t] will count, including the current ping.

It is guaranteed that every call to ping uses a strictly larger value of t than before.



Example 1:

Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
Output: [null,1,2,3,3]


Note:

Each test case will have at most 10000 calls to ping.
Each test case will call ping with strictly increasing values of t.
Each call to ping will have 1 <= t <= 10^9.
     */


    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */

    private static class RecentCounter {

        /*

        GOOD : Got it correct on first try : But slower than most :-( Don't understand why.
            Exactly similar solutions are claiming ot be faster

         */

        private List<Integer> listEvents = new LinkedList<>();
        private int size = 0;

        public RecentCounter() {

        }

        public int ping(int t) {

            // add this event to the list
            listEvents.add(t);
            size++;

            // remove the first few elements up to this if their TS is 3000 ago
            ListIterator<Integer> iter = listEvents.listIterator();
            int i = iter.next();
            while ( (t - i) > 3000) {
                iter.remove();
                size--;
                i = iter.next();
            }

            //return listEvents.size();
            return size;
        }
    }


}
