package avachat.leetcode.medium;

import java.util.Arrays;

public class Prob0031NextPermutation {

    /*

    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

     */


    public void nextPermutation(int[] nums) {

        if ( (null == nums) || (nums.length < 2)) {
            return;
        }


        // Strategy
        // Consider digits string
        // a b c d e f g h i j k
        // Let's say a b c d are all less than k
        // and e f g h i j are all higher or equal to k
        //
        // so we swap d and k, and it will definitely be a higher number
        // But is it the nest higher number?
        // How do we know some other swap wouldn't produce a better candidate?
        //
        // Now, there is no point in replacing a b c by a higher number
        // as they are on the left of d, and replacing any would give
        // an even higher number
        //
        // So the swap must happen at d or right of d
        //
        // Can other number (from f g h i j) be better than k to swap at d?
        // It must be lower than k, else it would give an even higher number
        // But if it was lower than k, then our scan would have chosen it already
        // and we wouldn't have reached d
        // So they are all higher or equal to k
        //
        // But is there a better swap BETWEEN d and k?
        // Even if these numbers are higher than k, a correct swap would be better
        // as then the number that gets replaced would be to the right of d
        // and would result in a lower number than the first candidate
        //
        // So whenever a candidate is found (d and k here), we should search of there is
        // a better candidate BETWEEN them
        //

        int swapAt = -1; // init to left of array 
        int swapFrom = nums.length; // init to the right of array
        int swapFromCandidate = swapFrom;
        
        while (swapFromCandidate > swapAt) {
            
            // see if a better option is available
            // that's between current swapAt and swapFrom
            int segmentRight = swapFromCandidate - 1;
            int segmentLeft = swapAt + 1;
            int swapAtCandidate = findSwapPosition(nums, segmentLeft, segmentRight);
            if (swapAtCandidate != -1) {
                swapAt = swapAtCandidate; // adjust the segment - new left is at candidate
                swapFrom = segmentRight; // the segmentRight is where digit should be swapped from
            }
            swapFromCandidate--; // try the next right
        }

        // if swapAt was found - do the swap
        if (swapAt >= 0) {
            int tmp = nums[swapFrom];
            nums[swapFrom] = nums[swapAt];
            nums[swapAt] = tmp;
        }

        // sort the array to the right of swapAt
        Arrays.sort(nums, swapAt+1, nums.length);

    }

    /**
     * Find the position for which end can be swapped with
     * This position is >= start and < end
     * @param nums
     * @param start
     * @param end
     * @return the index where end can be swapped to
     */
    private int findSwapPosition(int[] nums, int start, int end) {

        if (start >= end) {
            // cannot swap is start is not to the left of end
            return -1;
        }

        int candidate = end-1;
        while (candidate >= start) {
            if (nums[candidate] < nums[end]) {
                return candidate;
            }
            candidate--;
        }

        return -1;
    }


    public void nextPermutationWrong(int[] nums) {

        /*

        This fails for
        [4,2,0,2,3,2,0]
        Gives out output : [4,2,2,0,0,2,3]
        Correct is : [4,2,0,3,0,2,2]

         */

        if ( (null == nums) || (nums.length < 2)) {
            return;
        }

        // for next permutation, swap the rightmost number with a first number to it's left and lower than it
        // if such a swap is not possible try with the next rightmost number
        // once the swap is done, everything to the right must then be sorted
        // consider the example
        // number : 3 2 7 6 5 1
        // the last number 1 cannot be moved anywhere yet - as it will only generate a smaller number, not larger
        // try this with 5 - swap with 2
        // 3 5 7 6 2 1
        // Now everything to the right of must be sorted
        // 3 5 1 2 6 7

        // start from the rightmost number till index 1 (it needs at least one number on the left to be compared
        int swappedAt = -1;
        boolean keepLooking = true;
        for (int right = nums.length - 1; keepLooking && (right > 0); right--) {
            for (int left = right -1; keepLooking && (left >= 0); left--) {
                if (nums[left] < nums[right]) {
                    // found the place to swap
                    keepLooking = false;
                    int tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                    swappedAt = left;
                    break;
                }
            }
        }

        // now numbers to the right of swappedAt should be sorted
        Arrays.sort(nums, swappedAt+1, nums.length);

    }


}
