package avachat.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Prob1029TwoCitySchedulingTest {


    @Test
    public void testIt () {
        Prob1029TwoCityScheduling testObj = new Prob1029TwoCityScheduling();
        assertEquals(1859, testObj.twoCitySchedCost(new int[][]{{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}}));
        assertEquals(110, testObj.twoCitySchedCost(new int[][]{{10,20},{30,200},{400,50},{30,20}}));
    }

}