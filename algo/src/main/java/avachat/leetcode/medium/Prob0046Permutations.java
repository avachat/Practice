package avachat.leetcode.medium;

import java.util.*;

public class Prob0046Permutations {


    /*

    Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

     */


    /*

    Strategy

    Keep a global availability array for each choice
    Keep a state associated with each position
        Total choices that can be made
        Count of choices made so far
        Index of current choice
    At every position
        If there are choices to be made, make it and move forward
        If there is no more choices left, go backward
     */

    /*

    GOOD : Got it right the first time, with 99% better on time and space

    !!!!!!!!!!

     */

    public List<List<Integer>> permute(int[] nums) {

        if ( (null == nums) || (nums.length == 0)) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>(factorial(nums.length));

        boolean[] availability = new boolean[nums.length];
        Arrays.fill(availability, true);

        generatePermutations(nums, availability, 0, nums.length, new LinkedList<Integer>(), result);

        return result;
    }


    private void generatePermutations (int [] choices, boolean[] availability,
                                       int position, int maxSelections,
                                       LinkedList<Integer> permutation, List<List<Integer>> result) {

        if (position >= choices.length) {
            // all positions filled
            result.add(new ArrayList<>(permutation));
            return;
        }

        int numRemainingSelections = maxSelections;
        int currentSelection = -1 ; // init to -1, we find the next available inside the loop

        while (numRemainingSelections > 0) {

            // find a selection
            currentSelection = getNextSelection(availability, currentSelection);

            // mark it as unavailable
            availability[currentSelection] = false;

            // add to the current permutation
            permutation.addLast(choices[currentSelection]);

            // go to the next position
            generatePermutations(choices, availability, position+1, maxSelections-1, permutation, result);

            // came back from the next position

            // mark the current selection as available
            availability[currentSelection] = true;

            // remove the selection from the permutation
            permutation.removeLast();

            numRemainingSelections--;
        }


    }


    private int getNextSelection(boolean[] availability, int index) {

        int start = index+1;

        // first go till the end of teh array
        while (start < availability.length) {
            if (availability[start]) {
                return start;
            }
            start++;
        }

        // not found any available
        // should not happen
        return -1;
    }

    private int factorial(int n) {
        int result = 1;
        for (int factor = 2; factor <= n; factor++) {
            result *= factor;
        }
        return result;
    }


    /**
     * Find index of next available choice after 'index'
     *
     * @param availability
     * @param index
     * @return
     */
    private int getNextIndexWrap(boolean[] availability, int index) {

        int start = index+1;

        // first go till the end of teh array
        while (start < availability.length) {
            if (availability[start]) {
                return start;
            }
            start++;
        }

        // not found till end
        // rewind
        start = 0 ;
        while (start < index) {
            if (availability[start]) {
                return start;
            }
            start++;
        }

        // not found any available
        // should not happen
        return -1;
    }
}
