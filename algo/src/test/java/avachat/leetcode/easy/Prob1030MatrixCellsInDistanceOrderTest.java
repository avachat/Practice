package avachat.leetcode.easy;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Prob1030MatrixCellsInDistanceOrderTest {


    @Test
    public void testIt() {
        Prob1030MatrixCellsInDistanceOrder testObj = new Prob1030MatrixCellsInDistanceOrder();
        System.out.println(Arrays.deepToString(testObj.allCellsDistOrder(1, 2, 0, 0)));
        System.out.println(Arrays.deepToString(testObj.allCellsDistOrder(2, 2, 0, 1)));
        System.out.println(Arrays.deepToString(testObj.allCellsDistOrder(2, 3, 1, 2)));
    }

}