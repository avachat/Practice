package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob198HouseRobberTest {

    private static Prob198HouseRobber testObj = new Prob198HouseRobber();

    @Test
    public void testGiven() {
        assertEquals(testObj.rob(new int[] {1,2,3,1}), 4);
        assertEquals(testObj.rob(new int[] {2,7,9,3,1}), 12);
    }

}