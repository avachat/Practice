package avachat.leetcode.easy;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Prob1042FlowerPlantingTest {


    @Test
    public void testIt() {

        Prob1042FlowerPlanting testObj = new Prob1042FlowerPlanting();

        //assertTrue(Arrays.equals(new int[]{1, 2, 3}, testObj.gardenNoAdj(3, new int[][]{{1,2},{2,3},{3,1}})));
        System.out.println(Arrays.toString(testObj.gardenNoAdj(3, new int[][]{{1,2},{2,3},{3,1}})));
        System.out.println(Arrays.toString(testObj.gardenNoAdj(4, new int[][]{{1,2},{3,4}})));
        System.out.println(Arrays.toString(testObj.gardenNoAdj(4, new int[][]{{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}})));
    }

}