package avachat.leetcode.easy;

import org.junit.Test;

import static org.testng.Assert.*;

public class Prob1010PairOfSongsTest {

    @Test
    public void testIt() {
        Prob1010PairOfSongs testObj = new Prob1010PairOfSongs();

        assertEquals(testObj.numPairsDivisibleBy60(new int[]{60, 60, 60}), 3);
    }

}