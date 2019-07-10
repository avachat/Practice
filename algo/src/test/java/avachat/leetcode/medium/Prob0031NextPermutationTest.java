package avachat.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Prob0031NextPermutationTest {


    @Test
    public void printIt() {

        Prob0031NextPermutation testObj = new Prob0031NextPermutation();

        int[] nums = new int[]{};

        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {1, 2};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {1, 2, 3};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {3, 2, 1};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {1, 1, 5};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {5, 5, 5};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {1, 7, 6, 5};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {2, 7, 6, 5, 1};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {3, 2, 7, 6, 5, 1};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

        nums = new int[] {4,2,0,2,3,2,0};
        System.out.print(Arrays.toString(nums));
        testObj.nextPermutation(nums);
        System.out.println( " -> " + Arrays.toString(nums));

    }

}