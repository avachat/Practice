package avachat.leetcode.easy;

public class Prob746MinCostClimbing {

    /*

    On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

     */

    public int minCostClimbingStairs(int[] cost) {

        /*

        GOOD : Simple, got the best algo in 2 mins
            Faster than 100%

         */

        // the min length of the array is 2

        // start looking from cost.length - 3 (3rd rightmost index)

        int cost2 = cost[cost.length -1]; // cost for the rightmost step, reachable by a 2 step from our starting index
        int cost1 = cost[cost.length -2]; // cost for the rightmost step, reachable by a 1 step from our starting index

        for (int i = cost.length - 3; i >= 0; i--) {
            int c = cost[i] + Math.min(cost1, cost2); // this is the cost of current step to reach the top
            cost2 = cost1;
            cost1 = c;
        }

        return Math.min (cost1, cost2);

    }
}
