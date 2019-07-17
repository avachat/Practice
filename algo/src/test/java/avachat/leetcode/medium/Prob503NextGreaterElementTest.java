package avachat.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Prob503NextGreaterElementTest {

    @Test
    public void testIt() {

        Prob503NextGreaterElement testObj = new Prob503NextGreaterElement();

        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{1,1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{1,1,1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{1,2,1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{1,2,3})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{3,2,1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{4,4,3,2,1})));
        System.out.println(Arrays.toString(testObj.nextGreaterElements(new int[]{4,5,3,2,1})));
    }

}