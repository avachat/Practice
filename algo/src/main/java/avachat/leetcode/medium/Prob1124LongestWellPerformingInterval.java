package avachat.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class Prob1124LongestWellPerformingInterval {

    /*

    We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.



Example 1:

Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].


Constraints:

1 <= hours.length <= 10000
0 <= hours[i] <= 16

     */

    /*

    GOOD : Nothing much

    BAD : Took many hours of trying and failing.
        Then many hours to understand the solutions found on the internet

     */

    /*

    Even better explanation is at
    https://leetcode.com/problems/longest-well-performing-interval/discuss/334635/Java-HashMap-O(n)-solution-with-explanation-similar-to-lc525

    See the comments

     */
    public int longestWPI(int[] hours) {

        // Treat regular day as -1, tiring day as 1
        // maintain running score
        // when the score is positive - entire array up to that is a good candidate answer
        //
        // when the score is not positive, say 0 or negative
        // that's when the trick is handy
        // let say the score is -x at i
        // see if the score -x-1 (one less) was found at j
        // if it was then the sequence that starts at j+1 and ends at i must have score 1 !!!!
        // So even if the score is negative here, finding a more negative number tell us a positive sequence
        //
        // NOTE : For any sequence that start at 'start' and ends at 'end'
        // the length of that sequence is end - start + 1
        // So if such a j is found
        // length = i - (j+1) + 1
        //        = i - j - 1 + 1
        //        = i - j

        // hours is guaranteed to be non null
        if (hours.length == 0) {
            return 0;
        }

        Map<Integer, Integer> scoreSeenAt = new HashMap<>(1+2*hours.length); // possible sums range from -n to n
        int maxLength = 0;
        int runningScore = 0;

        for (int i = 0; i < hours.length; i++) {

            // update runningScore
            runningScore +=  (hours[i] > 8) ? 1 : -1;

            if (! scoreSeenAt.containsKey(runningScore)) {
                // first time seeing this score
                scoreSeenAt.put(runningScore, i);
            }

            if (runningScore > 0) {
                // the entire array so far has more tiring days
                maxLength = i+1;
            } else {
                // score is 0 or -ve
                // see a score of one less was seen before
                // if yes, then we have found a sub-sequence that totals to 1
                // if it's longer, update the maxLength
                if (scoreSeenAt.containsKey(runningScore - 1)) {
                    int start = scoreSeenAt.get(runningScore - 1);
                    int length = i - start;
                    if (length > maxLength) {
                        maxLength = length;
                    }
                }
            }

        }

        return maxLength;
    }

    /*

    Completely missed that we don't need 0 score, we need 2 equal scores to know that the segment between them is zero.

    The explanation at this link is very good.
    https://leetcode.com/problems/contiguous-array/solution/

    I was able to see the chart - but kept on worrying how to get the zero score.

    Strategy : Treat the regular days, as -1, and tiring days as +1 score.
    Then the problem is to find the longest subsequence that has 0 sum.
    A length less than 1 of that is the answer : as we need a positive sum.

    If the array ends with a positive cumulative sum, entire array is the answer

     */


    public int longestWPI_WRONG_AGAIN(int[] hours) {

        // hours is guaranteed to be non null etc
        if (hours.length == 1) {
            return (hours[0] > 8) ? 1 : 0; // this is the length of the array
        }

        // score is the deficit or surplus
        int[] runningScore = new int[hours.length];
        runningScore[0] = (hours[0] > 8) ? 1 : -1;

        // keep a map to remember what was the earliest point at which we have seen this score
        Map<Integer, Integer> scoreSeenAt = new HashMap<>(1+2*hours.length); // possible sums range from -n to n
        scoreSeenAt.put(runningScore[0], 0); // add the first element score

        // keep track of the max sequence
        int maxStart = 0;
        int maxEnd = 0;
        int maxLength = (runningScore[0] > 0) ? 1 : 0;

        for (int i = 1; i < hours.length; i++) {
            int updateScoreBy = (hours[i] > 8) ? 1 : -1;
            int score = runningScore[i-1] + updateScoreBy;
            runningScore[i] = score;
            // have we seen the score before?
            if (scoreSeenAt.containsKey(score)) {
                int start = scoreSeenAt.get(score);
                // find the length of this sequence
                //
                // let's say the i = 7, and we had seen this sum at 3
                // so the sequence of index 4 5 6 7 has zero sum
                // as adding the numbers ast those indices to the value at 3, produced the same result
                // so sum of values at 4 elements gave sum to 0
                // that's 7 - 3
                // but since we need the positive sum, NOT zero
                // the length is one less that that
                // so 7 - 3 - 1
                // Let's take another example
                // say, i = 5, and this sum was last seen at 3
                // 3 4 5
                // values at 4 and 5 added to zero
                // so the length we want is just 1
                // NOTE : the two consecutive elements will NEVER have the same sum,
                // as every element is either +1 or -1
                // So the equation i - start -1 will never be <= 0
                //
                // The above logic fails in this case of hours
                // [9, 9, 0]
                // The answer here is 3
                // Above logic will give 1
                //
                // Let's tweak it
                // If the sum is positive we need entire sequence length
                // examples [9, 9. 0]   [9, 0, 9, 0, 9]
                // That is end - start + 1
                //
                // If the sum is negative - then above logic holds
                //
                // If the sum is zero
                // [0 0 0 0 0 9 9 9 9 9] [0 9 0 9 0 9 0 9 0 9]
                // We have equal numbers of +1 and -1 at this point
                // So Removing just 1 element, would give us a desired sequence
                //
                int length = -1;
                if (score > 0) {
                    length = i - start + 1;
                } else  {
                    length = i - start - 1;
                }

                // is this a better length
                if (length > maxLength) {
                    maxStart = start;
                    maxEnd = i;
                    maxLength = length;
                }
            } else {
                // first time seeing the score
                scoreSeenAt.put(score, i);
                // if score is not negative, and seen for the first time, this is a good sequence
                int length = -1;
                if (score > 0) {
                    length = i + 1;  // i + 1 is the length of sequence from 0 to i
                } else if (score == 0) {
                    // we are seeing 0 for the first time, so we have equal number -1 and 1 till now
                    // so length is this while subsequence less 1
                    length = i; //  i + 1 is the length of sequence from 0 to i
                }
                // is this a better length
                if (length > maxLength) {
                    maxStart = 0;
                    maxEnd = i;
                    maxLength = length;
                }
            }
        }

        // is the array sum at end positive?
        /*
        if (runningScore[hours.length -1] > 0) {
            return hours.length;
        }
        */

        return maxLength;

    }


        /*

    Let's try to rebase the running sum
    0   0   0   0   0   9    9   9   9   0   0   0   0   0
    -1  -2  -3 -4  -5   -4  -3  -2  -1  -2  -3  -4  -5  -6
                       -1   -2  -3  -4  -5  -4  -3  -2  -1

     */


    /*

    Let's try to rebase the running sum
    0   0   0   0   0   9    9   9   9   0   0   0   0   0
    -1  -2  -3 -4  -5   -4  -3  -2  -1  -2  -3  -4  -5  -6
    Add 5
    4   3   3   1   0   1    2   3   4


    REBASE DOESN'T WORK


     */


    /*

    Let's look at a long example

  Hours Score
    9   1   <--------
    0   0
    9   1
    0   0
    9   1
    0   0
    9   1
    0   0
    0   -1
    0   -2
    0   -3
    0   -4
    0   -5
    9   -4
    9   -3
    9   -2
    0   -3
    9   -2
    9   -1
    9   0
    9   1
    0   0
    0   -1
    0   -2
    9   -1
    9   0
    9   1
    9   2
    9   3
    0   2
    0   1   <---------
    0   0
    0   -1

    Looks like the longest sub sequence would be
    from the first element at which the score turns positive
    to the last element where the score was positive

    -------------
    THIS FAILS for [0, 0, 9]
    There is no positive in the cumulative sum

     */

    public int longestWPI_WRONG(int[] hours) {

        // hours is guaranteed to be non null etc
        if (hours.length == 1) {
            return (hours[0] > 8) ? 1 : 0; // this is the length of the array
        }

        // score is the deficit or surplus
        int[] runningScore = new int[hours.length];
        runningScore[0] = (hours[0] > 8) ? 1 : -1;

        for (int i = 1; i < hours.length; i++) {
            int updateScoreBy = (hours[i] > 8) ? 1 : -1;
            runningScore[i] = runningScore[i-1] + updateScoreBy;
        }

        // now find the left where runningScore became positive first
        int left = -1;
        for (int i = 0; i < hours.length; i++) {
            if (runningScore[i] > 0) {
                left = i;
                break;
            }
        }

        if (left == -1) {
            // no positive found
            return 0;
        }

        // now find the right where the last positive was found
        // look only till left
        int right = hours.length;
        for (int i = hours.length-1; i >= left; i--) {
            if (runningScore[i] > 0) {
                right = i;
                break;
            }
        }

        return right - left + 1;
    }


    /*

    Strategy : Treat tiring days as +1 and other days as -1
    Then this is just a longest positive sum subsequence

    ---------------

    THAT OBSERVATION IS WRONG TOO
     */








    /*
    ??? NOT SURE OF THIS

    Strategy

    If the entire array has more tiring days, then that's the answer

    If there are more easy days, then we have to trim the array such that,
    there are more tiring days.

    We can see how much trimming is needed from just left
    Or from just right
    Or the tricky one : both sides

    -----------------------

    This is too complicated - and maybe wrong

     */






    /*

    DP Strategy :

    Build a NxN matrix
    Each cell [i, j] represents the score for the sequence that starts at j and ends at i

    Look at i th value in the array
    for j : 0 to i-1
    Update the score for every [i, j]

    ------------
    THIS IS NOT THAT GREAT OF A STRATEGY : It is is still O(N^2) and also takes O(N^2) memory

    A simpler impl would hve been to just examine all the N^2 sub sequences  :-( :-(

     */


    public int longestWPI_ON2(int[] hours) {

        // hours is guaranteed to be non null etc
        if (hours.length == 1) {
            return (hours[0] > 8) ? 1 : 0; // this is the length of the array
        }

        int[][] scoreMatrix = new int[hours.length][hours.length];

        // init cell [0,0]
        scoreMatrix[0][0] = (hours[0] > 8) ? 1 : -1; // score is the deficit or surplus

        // track the values found so far
        //int maxScore = scoreMatrix[0][0]; // max score so far is the sequence that starts and ends at 0
        int longestSequenceLength = scoreMatrix[0][0] > 0 ? 1 : 0; // [0,0] is a either a good sequence or not
        int start = 0; // where does the sequence start
        int end = 0; // where does the sequence end

        for (int i = 1; i < hours.length; i++) {

            int updateScoreBy = (hours[i] > 8) ? 1 : -1;

            // examine all the sequences that ended in previous day
            for (int j = 0; j < i; j++) {
                scoreMatrix[i][j] = scoreMatrix[i-1][j] + updateScoreBy;
                // is the score still positive?
                if (scoreMatrix[i][j] > 0) {
                    // is it a longer sequence?
                    if ( longestSequenceLength < (i-j+1)) {
                        start = j;
                        end = i;
                        longestSequenceLength = end - start + 1;
                    }
                }
            }

            // init diagonal value
            scoreMatrix[i][i] = updateScoreBy;

            // this single cell might be the longest sequence
            if ( (updateScoreBy > 0) && (longestSequenceLength == 0)) {
                longestSequenceLength = 1;
                start = i;
                end = i;
            }
        }

        return longestSequenceLength;
    }


}
