package avachat.leetcode.easy;

public class Prob0069Sqrt {

    /*
    Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
     */



    public int mySqrt_BAD(int x) {


        /*


        !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        VERY VERY BAD

        Took multiple attempts to get it right.

        Serious issue : Did not anticipate the correct terminating conditions
                        Specifically low can step over the real square root.
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!


         */

        // x is guaranteed to be non negative

        if ( x == 0 ) {
            return 0;
        }

        int low = 1;
        int high = x;
        int sqrt = low;

        while ( high > low) {
            long middle = ((long)high + low) / 2 ; // NOTE : Need the long cast
            long square = middle * middle;
            if (square == x) {
                return (int)middle;
            }
            if ( (square < 0) || (square > Integer.MAX_VALUE) || (square > x)) {
                // overflow
                //------ INCORRECT : high = middle - 1;
                high = (int)middle;
            } else {
                sqrt = (int)middle; // NOTE : THIS IS ESSENTIAL and was missed
                // ------ INCORRECT low = middle;
                low = (int)middle + 1;
            }
        }

        return sqrt;

    }


    public int mySqrt(int x) {

        if (x == 0) {
            return 0;
        }

        // No integer > 1 will have a square root < 1.
        // And the lowest floor(sqrt(x)) is 1.
        // So this is the correct initialization.
        // INVARIANT : low <= floor(sqrt(x))
        // NOTE the <= sign above. Which means low can be the answer.
        int low = 1;

        // No integer floor of a square root is going to be > half of the number
        // Mathematical fact floor(sqrt(x)) < (floor(x/2) + 1)
        //
        // this is obviously true for any x >= 4
        //
        // For 6, sqrt is 2.xxx and (floor(x/2) + 1) is 4
        //
        // For 5, sqrt is 2.xxxx and (floor(x/2) + 1) is 3
        //
        // For 4, sqrt is 2 and (floor(x/2) + 1) is 3
        //
        // for x=3, and x=2, the floor(sqrt(x)) == 1,, and (floor(x/2) +1) == 2
        //
        // This init (with a divide by 2) also avoids the overflow that can happen with the addition, low+high
        int high = (x/2) + 1;

        // --------
        // So the MAIN INVARIANT is
        //
        // low <=  floor(sqrt(x))
        //
        // Also while we are in the loop, we will maintain the IN LOOP CONDITION
        // low <=  floor(sqrt(x))  < high
        // NOTE the signs above, high is strictly > floor(sqrt(x))
        // --------

        while (low < high) {

            // While we are in the loop, the following IN LOOP CONDITION is true
            // low <=  floor(sqrt(x))  < high
            // NOTE the signs above, high is strictly > floor(sqrt(x))



            // middle is the floor(half of the sum)
            // and when high == low +1, middle == low
            //
            // LOOP INVARIANT is
            // low <=  middle  < high
            //
            // If middle is indeed the answer (floor(sqrt(x)), then this setting of middle satisfies the MAIN INVARIANT
            //
            // We will maintain the INVARIANT low <= middle when assigning new values to low
            int middle = (low + high) / 2;

            // the cast is absolutely essential to avoid integer overflow
            long square = ((long) middle) * middle;

            if (x == square) {
                return middle; // found it
            }

            if (x < square) {

                // NOTE that LOOP INVARIANT is
                // low <=  middle  < high
                // And the MAIN INVARIANT is
                // low <=  floor(sqrt(x))
                //
                // Since x < square, it means sqrt(x) < middle, and obviously floor(sqrt(x) < middle
                // Combining
                // low <= floor(sqrt(x) < middle < high
                // So in this case, the following strict comparison is true
                // low < middle < high
                // And in this if condition, low is strictly < middle, meaning here, low != middle

                // Need to go lower
                high = middle;
                //
                // Note that middle is strictly > low, meaning low <= middle - 1
                // So setting high = middle
                // Will mean low < high
                // And the loop continues


                // --------------------------------------------------------------------------------------
                //Following are original wrong comments before the code was tested
                // --------------------------------------------------------------------------------------
                //------ INCORRECT : high = middle - 1;
                // So setting high = middle - 1
                // Will mean low <= high
                // Possibility 1 :
                //          If low == high, IN LOOP CONDITION is invalid
                //          It also means low == middle -1
                //          And since low <= floor(sqrt(x) < middle < high
                //          Which means, floor(sqrt(x) == low, and low is the answer
                //          The loop will terminate and low will be correctly returned
                // Possibility 2 :
                //          If low < high, the loop continues
                //
                // --------------------------------------------------------------------------------------

            } else {

                // Need to adjust the low

                // NOTE that LOOP INVARIANT is
                // low <=  middle  < high
                // This means
                // low <= middle < (sqrt(x))
                // If floor is taken, it will mean
                // low <= middle <= floor(sqrt(x)
                // Combining this with the IN LOOP CONDITION
                // low <= middle <= floor(sqrt(x) < high

                // Two possibilities for low
                //
                // Possibility 1 : low < middle (strictly)
                //     This means low < middle <= floor(sqrt(x) < high
                //     So setting low = middle will mean
                //     low <= floor(sqrt(x) < high  - still satisfying the IN LOOP CONDITION
                //     So the loop continues
                //
                // Possibility 2 : low == middle
                //     The only way this is possible is when high == low +1, middle == low
                //     Combine this with the IN LOOP CONDITION
                //     low == middle <= floor(sqrt(x) < (low + 1)
                //     Which means low is the answer

                if ( low == middle){
                    return low;
                }

                low = middle; // loop continues
            }

        }

        // See the notes
        return low;

    }

}
