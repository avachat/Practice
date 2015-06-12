package avachat.algo;

import java.util.Arrays;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by avachat on 6/10/15.
 */
public class RotateArrayTest extends TestCase {

    RotateArray rotateArray = new RotateArray();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RotateArrayTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RotateArrayTest.class );
    }

    /**
     * Rotate once
     */
    public void testRotateWithExtraArray1()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        int[] outputArray = rotateArray.rotateWithExtraArray(inputArray, 1);
        int[] expectedArray = new int[] {1, 2, 3, 4, 0};
        assertTrue( Arrays.equals(outputArray, expectedArray));
    }

    /**
     * Rotate 4 times
     */
    public void testRotateWithExtraArray4()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        int[] outputArray = rotateArray.rotateWithExtraArray(inputArray, 4);
        int[] expectedArray = new int[] {4, 0, 1, 2, 3};
        assertTrue( Arrays.equals(outputArray, expectedArray));
    }

    /**
     * Rotate effective modulo 1
     */
    public void testRotateWithExtraArrayModulo1()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        int[] outputArray = rotateArray.rotateWithExtraArray(inputArray, 51);
        int[] expectedArray = new int[] {1, 2, 3, 4, 0};
        assertTrue( Arrays.equals(outputArray, expectedArray));
    }

    /**
     * Rotate effective modulo none
     */
    public void testRotateWithExtraArrayExactModulo()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        int[] outputArray = rotateArray.rotateWithExtraArray(inputArray, 10);
        assertTrue( Arrays.equals(outputArray, inputArray));
    }



    /**
     * Rotate 1 times, using temp space of 1
     */
    public void testRotateInSpace11()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        rotateArray.rotateInPlaceWithTempSpace(inputArray, 1, 1);
        int[] expectedArray = new int[] {1, 2, 3, 4, 0};
        assertTrue( Arrays.equals(inputArray, expectedArray));
    }


    /**
     * Rotate 3 times, using temp space of 1
     */
    public void testRotateInSpace31()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        rotateArray.rotateInPlaceWithTempSpace(inputArray, 3, 1);
        int[] expectedArray = new int[] {3, 4, 0, 1, 2};
        assertTrue( Arrays.equals(inputArray, expectedArray));
    }


    /**
     * Rotate 3 times, using temp space of 2
     */
    public void testRotateInSpace32()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        rotateArray.rotateInPlaceWithTempSpace(inputArray, 3, 2);
        int[] expectedArray = new int[] {3, 4, 0, 1, 2};
        assertTrue( Arrays.equals(inputArray, expectedArray));
    }

    /**
     * Rotate 3 times, using temp space of 20
     */
    public void testRotateInSpace320()
    {
        int[] inputArray = {0, 1, 2, 3, 4};
        rotateArray.rotateInPlaceWithTempSpace(inputArray, 3, 20);
        int[] expectedArray = new int[] {3, 4, 0, 1, 2};
        assertTrue( Arrays.equals(inputArray, expectedArray));
    }


}
