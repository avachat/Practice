package avachat.leetcode.easy;

public class Prob581ShortestUnsortedContinuousSubarray {

    /*

iven an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.


     */

    public int findUnsortedSubarray(int[] nums) {

        /*

        GOOD : Figured out all the corner cases and the algorithm
            Correct impl - faster than 100% :-)

        BAD : Made a mistake in figuring out where to put the min and max
            Started scanning from start and end, should have started from 0 and length-1


         */

        // we know array at least has one element

        // find the incorrect element where unsorted subarray starts
        // it's the element whose next element is less
        // NOTE : there would be duplicates
        int start = 0;
        while ( ((start +1) < nums.length) && (nums[start] <= nums[start+1]) ) {
            start++;
        }
        // have we reached end?
        if ( (start+1) == nums.length) {
            return 0;
        }

        // find the incorrect element where the unsorted subarray ends
        int end = nums.length - 1;
        // loop only till start - if this subarray has only 2 elements, end-1 == start
        // NOTE there might be duplicates
        while ( ((end -1) >= start) && (nums[end] >= nums[end-1]) ) {
            end--;
        }

        // at this point, start points to the first element where unsorted segment begins
            // at value 6 in above example
        // and end points to the element where unsorted segment ends
            // at value 9 in above example

        // This segment definitely is the minimum that needs to be resorted
        // But resorting this may not be sufficient
        // It may contain elements that need to be places outside this segment
        // See example
        // [1, 4, 4, 5, 10, 15, 40, 9, 4, 20, 45, 25, 30, 35, 45, 45, 50]
        //
        // we need to find the min (4 above) and max within this segment
        // then find the best spot for these
        int min = nums[start];
        int max = nums[start];
        for (int i = start+1; i<=end; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // now lets find the best spot for min and max
        // if min is less than the element at start, keep going left
        int minAt = 0;
        while ( (minAt < start) && (min >= nums[minAt]) ) {
            minAt++;
        }

        int maxAt = nums.length - 1;
        while ( (maxAt > end) && (max <= nums[maxAt])) {
            maxAt--;
        }

        return (maxAt - minAt + 1);
    }

}
