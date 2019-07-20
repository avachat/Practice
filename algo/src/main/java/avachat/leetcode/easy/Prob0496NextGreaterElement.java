package avachat.leetcode.easy;

import java.util.*;

public class Prob496NextGreaterElement {

    /*

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

     */

    public int[] nextGreaterElementNoStack(int[] nums1, int[] nums2) {

        /*

        GOOD : First implementation correct and very fast.

        BAD : Took longer than what it should for such problems
            !!! Could not come up with the solution that pre-processes the nums2 array
                Was trying to think about it, but did not even think of stacks
                SEE SECOND IMPL BELOW

         */

        Map<Integer, Integer> mapNums2 = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            mapNums2.put(nums2[i], i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            int posn = mapNums2.get(num) + 1;
            int nextGreater = -1;
            while (posn < nums2.length) {
                if (nums2[posn] > num) {
                    nextGreater = nums2[posn];
                    break;
                }
                posn++;
            }
            result[i] = nextGreater;
        }

        return result;

    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        /*

        GOOD : Quick implementation, succeeded

        BAD : Could not come up with this on own
            And slower than first implementation : Why???

         */

        int result[] = new int[nums1.length];

        // stack will contain elements for which there is no next greater element found yet
        Deque<Integer> stack = new ArrayDeque<>(nums2.length);

        // map contains the answer - the next greater element
        Map<Integer, Integer> mapNums2 = new HashMap<>(nums2.length);

        for (int current : nums2) {

            // see if this number is an answer to the elements that are waiting in the stack
            while ( (!stack.isEmpty()) && (stack.peekFirst() < current)) {
                int n = stack.removeFirst();
                mapNums2.put(n, current);
            }

            // add this element to the stack - waiting or the next higher to be found
            stack.addFirst(current);
        }

        // stack may still contain elements - there is no guarantee that there is next higher element

        // Now map has the solution
        for (int i = 0; i < nums1.length; i++) {
            result[i] = mapNums2.getOrDefault(nums1[i], -1);
        }

        return result;
    }

}
