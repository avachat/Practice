package avachat.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Prob0034FindFirstAndLastPositionOfElementTest {


    @Test
    public void testIt() {

        Prob0034FindFirstAndLastPositionOfElement testObj = new Prob0034FindFirstAndLastPositionOfElement();

        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1}, 1)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,1}, 1)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,1,1}, 1)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,1,1,1,1,1,1,1,1,1}, 1)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,5,5,5,5,5,5,5,5,5,5}, 1)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,5,5,5,5,5,5,5,5,5,5,6}, 6)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,5,5,5,5,5,5,5,5,5,5,6}, 5)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,2,3,4,5,6,7,8,9}, 5)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{0,1,2,3,4,5,6,7,8,9}, 5)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{0,1,2,3,4,5,6,7,8,9}, 6)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{1,2,3,4,5,5,6,7,8,9}, 5)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{0,1,2,3,4,5,6,6,7,8,9}, 6)));
        System.out.println(Arrays.toString(testObj.searchRange(new int[]{0,1,2,3,4,4,4,7,8,9}, 4)));

    }

}