package avachat.hackrank.algorithm.implementation;

import java.util.Scanner;

/**
 * Created by avachat on 8/18/15.
 */
/*
Problem Statement

The Utopian Tree goes through 2 cycles of growth every year. The first growth cycle occurs during the spring, when it doubles in height. The second growth cycle occurs during the summer, when its height increases by 1 meter.

Now, a new Utopian Tree sapling is planted at the onset of spring. Its height is 1 meter. Can you find the height of the tree after N growth cycles?

Input Format

The first line contains an integer, T, the number of test cases.
T lines follow; each line contains an integer, N, that denotes the number of cycles for that test case.

Constraints
1≤T≤10
0≤N≤60
Output Format

For each test case, print the height of the Utopian Tree after N cycles. Each line thus has to contain a single integer, only.

Sample Input

3
0
1
4
Sample Output

1
2
7
Explanation

There are 3 test cases.

In the first case (N=0), the initial height (1) of the tree remains unchanged.

In the second case (when N = 1, i.e. after the 1st cycle), the tree doubles its height as it's planted at the onset of spring.

In the third case (N=4), the tree first doubles its height (2), then grows a meter (3), then doubles again (6), before growing another meter; at the end of the 4th cycle, its height is 7 meters.
 */


/**
 * Of course this can be implemented by writing a loop.
 * But it's more fun to come up with a formula.
 *
 * A cycle pair is considered as spring + summer.
 * After Nth cycle pair, for a starting height x, multiplication factor a, and constant addition of b
 * 1. height = a*x + b
 * 2. height = (a^2)*x + ab
 * 3. height = (a^3)*x + (a^2 + 1)*b
 * 4. height = (a^4)*x + (a^3 + a^2 + 1)*b
 * ...
 * N. height = (a^N)*x + ( sum of powers of a from N-1 to 0) * b
 * Sum of powers of a till K is found using formula = ( (a^(k+1)) - 1) / (a - 1)
 *
 * Substituting a = 2, b = 1, x = 1
 * height after N pairs = (2^N)*1 + ( (2^(N-1+1)) -1) / (2 -1)
 * = 2^N + 2^N - 1
 * = 2 ^ (N+1) - 1
 *
 * This is for pairs of cycles.
 * If it's an odd number of cycles, it needs to be doubled again.
 *
 * NOTE :
 * 2^N is obtained by left shifting 1.
 */
public class UtopianTree {


    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numTestCase = scanner.nextInt();

        for (int t = 0; t< numTestCase; t++) {

            int numCycles = scanner.nextInt();

            int numCyclePairs = numCycles / 2;

            long height = (1 << (numCyclePairs + 1)) - 1;
            if ( (numCycles % 2) != 0) {
                height *= 2;
            }

            System.out.println (height);
        }
    }

}
