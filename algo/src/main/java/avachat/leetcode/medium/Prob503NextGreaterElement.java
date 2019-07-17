package avachat.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class Prob503NextGreaterElement {

    /*

    Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.

     */


    /*

    GOOD : Adapted strategy of previous solution successfully.
        Better space time than almost 100%.
        Cleaner implementation than leetcode's solution


     */

    private static class Waiting {
        // represents that 'number' at 'index' is waiting for next greater
        int number;
        int index;
        Waiting(int n, int i) {
            number = n;
            index = i;
        }
    }

    public int[] nextGreaterElements(int[] nums) {

        if ( (null == nums) || (nums.length == 0)) {
            return nums;
        }

        int[] result = new int[nums.length];

        // An entry on the stack means, that number is waiting for it's next highest
        Deque<Waiting> stack = new ArrayDeque<>(nums.length);
        Waiting bottomOfStack = null;

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];

            // is current an answer for any number waiting on the stack?
            // if yes, keep popping till either stack is empty, or current is not an answer
            // NOTE : all numbers less than current will be popped out
            // which means, when current gets pushed on the stack
            // it's equal or less than what's on stack
            // which means stack is sorted from top (lowest) to bottom (highest)
            while ( (!stack.isEmpty()) && (current > stack.peek().number)) {
                Waiting w = stack.pop();
                // current is the answer for w
                result[w.index] = current;
            }

            // current is always going to be waiting
            Waiting w = new Waiting(current, i);
            if (stack.isEmpty()) {
                // bottom of the stack will always be the leftmost element that's waiting
                bottomOfStack = w;
            }
            stack.push(w);
        }

        // now there are numbers remaining on the stack
        // The last element of the array is always going to be there
        // The bottom of the stack if the leftmost element that's waiting for an answer
        // it does not have an answer to it's right
        // It is also the largest element of teh stack
        // If it's not the answer to the numbers that are waiting, then there is no answer
        // So we need to reexamine all the numbers from 0 to that

        for (int i = 0; (i <= bottomOfStack.index) && (!stack.isEmpty()); i++) {

            int current = nums[i];

            // is current an answer for any number waiting on the stack?
            // if yes, keep popping till either stack is empty, or current is not an answer
            while ( (!stack.isEmpty()) && (current > stack.peek().number)) {
                Waiting w = stack.pop();
                // current is the answer for w
                result[w.index] = current;
            }

        }

        // Now some numbers may still remain on the stack
        // They have no answer at all
        while (! stack.isEmpty()) {
            Waiting w = stack.pop();
            result[w.index] = -1;

        }


        return result;
    }
}
