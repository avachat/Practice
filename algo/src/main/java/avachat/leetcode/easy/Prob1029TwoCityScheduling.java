package avachat.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Prob1029TwoCityScheduling {

    /*
    There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.



Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.


Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
     */



    /*

    First strategy was to copy the array. Sort one copy based on cost A, other on cost B.
    Then keep selecting one from both array, depending on which cost is lower.
    That's very wrong.
    Consider
    [2, 3]
    [4, 1000]

    If first row is selected based on cost A, then second must go to B, with cost 1000.

    Finally implemented the solution based on submissions on leetcode
    Needed LOOOONG time to understand

    Also see the explanation of the DP solution at
    https://leetcode.com/problems/two-city-scheduling/discuss/285566/C%2B%2B-DP-solution-with-detailed-explaination

     */

    private static class Cost {
        int costA;
        int costB;
        Cost(int a, int b) {
            costA = a;
            costB = b;
        }
        Cost (int[] a) {
            costA = a[0];
            costB = a[1];
        }
    }

    private static class CostComparator implements Comparator<Cost> {

        boolean compareA;

        CostComparator (boolean aorb) {
            compareA = aorb;
        }

        @Override
        public int compare(Cost o1, Cost o2) {
            return compareA ? (o1.costA - o2.costA) : (o1.costB - o2.costB);
        }
    }

    private Cost[] createCostPairs(int a[][]) {

        Cost[] costs = new Cost[a.length];

        for (int i = 0; i < a.length; i++) {
            costs[i] = new Cost(a[i]);
        }

        return costs;
    }

    private int nextIndex (Cost[] costs, int startAt, Set<Cost> used) {
        if (startAt >= costs.length) {
            return -1;
        }
        while (used.contains(costs[startAt])) {
            startAt++;
        }
        return (startAt < costs.length) ? startAt : -1;
    }

    public int twoCitySchedCostWrong(int[][] costs) {

        Cost[] sortedCostsA = createCostPairs(costs);
        Arrays.sort(sortedCostsA, new CostComparator(true));

        Cost[] sortedCostsB = Arrays.copyOf(sortedCostsA, sortedCostsA.length);
        Arrays.sort(sortedCostsB, new CostComparator(false));

        int a = 0;
        int usedA = 0;
        int b = 0;
        int usedB = 0;
        int numCostsNeeded = costs.length / 2;

        int sum = 0;

        Set<Cost> usedCosts = new HashSet<>(costs.length);

        while ( (usedA < numCostsNeeded) && (usedB < numCostsNeeded)) {

            int a1 = nextIndex(sortedCostsA, a, usedCosts);
            int b1 = nextIndex(sortedCostsB, b, usedCosts);

            if ( (a1== -1) || (b1 == -1)) {
                break;
            }
            int a2 = nextIndex(sortedCostsA, a1+1, usedCosts);
            int b2 = nextIndex(sortedCostsB, b1+1, usedCosts);
            if ( (a2== -1) || (b2 == -1)) {
                break;
            }

            // now both arrays have a pair
            int a1b2Cost = sortedCostsA[a1].costA + sortedCostsB[b2].costB;
            int a2b1Cost = sortedCostsA[a2].costA + sortedCostsB[b1].costB;

            if (a1b2Cost < a2b1Cost) {
                sum += sortedCostsA[a1].costA;
                usedCosts.add(sortedCostsA[a1]);
                usedA++;
                a = a1 + 1;
            } else {
                sum += sortedCostsB[b1].costB;
                usedCosts.add(sortedCostsB[b1]);
                usedB++;
                b = b1 + 1;
            }

            /*

            WRONG !!!!!!  :-( :-(

            if (sortedCostsA[a].costA < sortedCostsB[b].costB) {
                if (!usedCosts.contains(sortedCostsA[a])) {
                    sum += sortedCostsA[a].costA;
                    usedCosts.add(sortedCostsA[a]);
                    usedA++;
                }
                a++;
            } else {
                if (!usedCosts.contains(sortedCostsB[b])) {
                    sum += sortedCostsB[b].costB;
                    usedCosts.add(sortedCostsB[b]);
                    usedB++;
                }
                b++;
            }
             */

        }

        // one of the arrays has been used up now
        for (int i = a; (usedA < numCostsNeeded) && (i < sortedCostsA.length); i++) {
            if (!usedCosts.contains(sortedCostsA[i])) {
                sum += sortedCostsA[i].costA;
                usedCosts.add(sortedCostsA[i]);
                usedA++;
            }
        }

        for (int i = b; (usedB < numCostsNeeded) && (i < sortedCostsB.length); i++) {
            if (!usedCosts.contains(sortedCostsB[i])) {
                sum += sortedCostsB[i].costB;
                usedCosts.add(sortedCostsB[i]);
                usedB++;
            }
        }

        return sum;
    }

    public int twoCitySchedCost(int[][] costs) {

        // let's sort the array with a comparator function
        // that decides which person is it better to send to A
        // for example in
        // [2, 3]
        // [4, 1000]
        // It's better tp send 1st person to B and second to A
        // We do that by comparing total cost
        // cost[0][0] + cost [1][1]   ??   cost[0][1] + cost[1][0]
        //
        // Once sorted this way, the person on the top should be sent to A, other half to B

        Arrays.sort(costs, (i, j) -> (i[0] + j[1]) - (i[1] + j[0]));

        int sum = 0;

        // send first half to city A
        for (int i = 0; i < (costs.length/2); i++) {
            sum += costs[i][0];
        }

        // send next half to city B
        for (int i = costs.length/2; i < costs.length; i++) {
            sum += costs[i][1];
        }


        return sum;

    }



}
