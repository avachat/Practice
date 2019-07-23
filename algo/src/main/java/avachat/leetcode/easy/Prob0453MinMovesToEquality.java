package avachat.leetcode.easy;

public class Prob0453MinMovesToEquality {


    /*

Given a non-empty integer array of size n,
find the minimum number of moves required to make all array elements equal,
where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]


     */



    public int minMoves(int[] nums) {

        // TODO : Need to solve using math
        return 0;

    }


    public int minMovesRunsOutOfTime(int[] nums) {

        // array is non empty

        // nothing to be done for array of 1
        if (nums.length == 1) {
            return 0;
        }

        int moves = 0;
        int[] stats = new int[5];

        analyze(nums, stats);

        // do till minVal != maxVal
        while (stats[0] != stats[2]) {

            // find moves needed
            int diff = stats[2] - stats[0];
            moves += diff;
            adjust(nums, diff, stats[4]);
            analyze(nums, stats);
        }

        return moves;
    }

    private void analyze (int nums[], int stats[]) {

        // assume array is at least size 2

        int minVal = nums[0];
        int minCount = 0;
        int maxVal = nums[0];
        int maxCount = 0;
        int maxAt = 0;

        if ( nums[0] == nums[1]) {
            minVal = nums[0];
            minCount = 2;
            maxVal = nums[0];
            maxCount = 2;
            maxAt = 0;
        } else if ( nums[0] < nums[1]) {
            minVal = nums[0];
            minCount = 1;
            maxVal = nums[1];
            maxCount = 1;
            maxAt = 1;
        } else {
            minVal = nums[1];
            minCount = 1;
            maxVal = nums[0];
            maxCount = 1;
            maxAt = 0;
        }

        // init the stats to correct values
        for (int i = 2; i < nums.length; i++) {

            if (nums[i] < minVal) {
                minVal = nums[i];
                minCount = 1;
            } else if (nums[i] == minVal) {
                minCount++;
            }

            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxCount = 1;
                maxAt = i;
            } else if (nums[i] == maxVal) {
                maxCount ++;
            }
        }

        stats[0] = minVal;
        stats[1] = minCount;
        stats[2] = maxVal;
        stats[3] = maxCount;
        stats[4] = maxAt;

    }

    private void adjust(int[] nums, int diff, int maxAt) {

        for (int i=0; i<nums.length; i++) {
            if (i != maxAt) {
                nums[i] += diff;
            }
        }
    }


}
