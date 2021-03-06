package avachat.leetcode.easy;

public class Prob0070ClimbingStairs {

    /*

    You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

     */

    public int climbStairs(int n) {

        /*
        GOOD : Very simple problem : Finished in 2 mins.
         */


        if ( n == 0 ) {
            return 0;
        }

        if ( n < 2 ) {
            return n;
        }

        int n1 = 1;
        int n2 = 2;

        for (int i = 3; i<= n; i++) {
            int n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }

        return n2;
    }


}
