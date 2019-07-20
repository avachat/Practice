package avachat.leetcode.easy;

import java.util.*;

public class Prob970PowerfulIntegers {


    /*

    Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.

Return a list of all powerful integers that have value less than or equal to bound.

You may return the answer in any order.  In your answer, each value should occur at most once.



Example 1:

Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation:
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
Example 2:

Input: x = 3, y = 5, bound = 15
Output: [2,4,6,8,10,14]


Note:

1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6

     */



    public List<Integer> powerfulIntegers(int x, int y, int bound) {

        /*
        GOOD : Better time than 100%

        BAD : Took forever to write correctly : Couldn't get two loops correct :-( :-( :-(
            Very bad on memory

            VERY complex code
            Using pow function and two loos with i, j makes the code look very simple.
            Of course by not using pow, time was really good

         */

        if (bound <= 1) {
            return Collections.emptyList();
        }

        Set<Integer> set = new HashSet<>();
        set.add(2);

        if ( (x == 1) && (y ==1)) {
            return new ArrayList<>(set);
        }

        if ( (x== 1) || (y ==1)) {
            int max = Math.max(x, y);
            int power = max;
            while ( (power+1) <= bound) {
                set.add(power+1);
                power *= max;
            }
            return new ArrayList<>(set);
        }

        int powx = 1; // power of x, start at 0
        while ( generateCombinations(powx, y, bound, set) > 0) {
            powx *= x;
        }


        return new ArrayList<>(set);

    }

    private int generateCombinations(int fixed, int n, int bound, Set<Integer> set) {

        int count = 0;
        int pow = 1;
        int sum = fixed + pow;

        while (sum <= bound) {
            set.add(sum);
            count++;
            pow *= n;
            sum = fixed + pow;
        }

        return count;

    }


}
