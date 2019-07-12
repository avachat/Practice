package avachat.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob39CombinationSumTest {

    @Test
    public void testIt() {

        Prob39CombinationSum testObj = new Prob39CombinationSum();
        System.out.println(testObj.combinationSum(new int[] {1}, 3));
        System.out.println(testObj.combinationSum(new int[] {1,2}, 3));
        System.out.println(testObj.combinationSum(new int[] {1,2}, 4));
        System.out.println(testObj.combinationSum(new int[] {1,2,3}, 4));
        System.out.println(testObj.combinationSum(new int[] {1,2}, 5));
        System.out.println(testObj.combinationSum(new int[] {1,2,3}, 5));
        System.out.println(testObj.combinationSum(new int[] {1,2,3}, 9));
        System.out.println(testObj.combinationSum(new int[] {3,4,5,6}, 9));
    }

}