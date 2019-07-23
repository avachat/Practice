package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob994RottingOrangesTest {

    private static final Prob0994RottingOranges testObj = new Prob0994RottingOranges();

    @Test
    public void testIt() {
        assertEquals(testObj.orangesRotting(new int[][]{{1,2}}), 1);
    }

}