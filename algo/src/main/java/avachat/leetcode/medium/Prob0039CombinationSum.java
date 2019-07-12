package avachat.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Prob0039CombinationSum {

    /*

    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

     */


    /*

    Strategy :

    For every number - see how many times it can be used.
    Start a loop that many times, and proceed to the next number.

    if the numbers are sorted, then it's easy to stop when the required sum goes over the current number
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if ( (null == candidates) || (candidates.length == 0)) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);

        combinationSumHelper(candidates, 0, target, result, new ArrayList<>());

        return result;
    }


    private void combinationSumHelper(int[] candidates, int index, int target,
                                      List<List<Integer>> result, List<Integer> combination) {


        if (index >= candidates.length) {
            // recursion terminating condition
            return;
        }

        int number = candidates[index]; // current number
        if (number > target) {
            return; // neither this number nor the higher numbers can be useful
        }

        int maxUseCount = target / number; // maximum number of times this number can be used
        int useCount = maxUseCount; // init to max

        while (useCount >= 0) {

            // create a copy of the current combination
            List<Integer> currentCombination = new ArrayList<>(combination);

            // add this number 'useCount' times to the combination list
            for (int i = 1; i <= useCount; i++) {
                currentCombination.add(number);
            }

            int nextTarget = target - (number * useCount);
            if (nextTarget == 0) {
                // found a combination
                // and then add combination to the result
                result.add(currentCombination);
            } else {
                // this number has been used usedCount times, still the combination is not complete
                // proceed with the next number
                combinationSumHelper(candidates, index + 1, nextTarget, result, currentCombination);
            }

            useCount--;
        }

    };



}
