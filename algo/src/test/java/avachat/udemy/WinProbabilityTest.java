package avachat.udemy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WinProbabilityTest {

    private static WinProbability testObj = new WinProbability();

    @Test
    public void testIndex() {

        double[] nums = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0};
        assertEquals(2, WinProbability.findInsertIndex(nums, 2.1, 0.01));
        assertEquals(5, WinProbability.findInsertIndex(nums, 5.1, 0.01));
        assertEquals(0, WinProbability.findInsertIndex(nums, 1.0, 0.01));
        assertEquals(7, WinProbability.findInsertIndex(nums, 7.1, 0.01));

        double[] nums2 = new double[]{0.0, 1.0, 3.0, 5.0};
        assertEquals(1, WinProbability.findInsertIndex(nums2, 1.0, 0.1));
    }

    @Test
    public void testProb() {

        Double[] picks = new Double[] {1.0, 4.0, 2.0};
        Double[] nums = new Double[] {3.0, 1.0, 0.0, 5.0};

        List<Double> picksList = Arrays.asList(picks);
        List<Double> numsList = Arrays.asList(nums);

        System.out.println(WinProbability.pwin(picksList, numsList, 0.1));

    }

}