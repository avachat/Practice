package avachat.algo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by avachat on 8/3/15.
 */
public class RollingAverageTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RollingAverageTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RollingAverageTest.class );
    }


    public void testRollingAverage() {

        RollingAverage rollingAverage = new RollingAverage(5);

        rollingAverage.putNextInt(20);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(20, rollingAverage.getMax());
        assertTrue( Math.abs (20-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(1);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(20, rollingAverage.getMax());
        assertTrue( Math.abs (10.5-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(2);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(20, rollingAverage.getMax());
        assertTrue( Math.abs (7.6666-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(3);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(20, rollingAverage.getMax());
        assertTrue( Math.abs (6.5-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(4);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(20, rollingAverage.getMax());
        assertTrue( Math.abs (6-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(5);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(5, rollingAverage.getMax());
        assertTrue( Math.abs (3-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(6);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(6, rollingAverage.getMax());
        assertTrue( Math.abs (4-rollingAverage.getAverage()) < 0.01);
        assertEquals(1, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(7);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(7, rollingAverage.getMax());
        assertTrue( Math.abs (5-rollingAverage.getAverage()) < 0.01);
        assertEquals(2, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(8);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(8, rollingAverage.getMax());
        assertTrue( Math.abs (6-rollingAverage.getAverage()) < 0.01);
        assertEquals(3, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(9);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(9, rollingAverage.getMax());
        assertTrue( Math.abs (7-rollingAverage.getAverage()) < 0.01);
        assertEquals(4, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(10);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(10, rollingAverage.getMax());
        assertTrue( Math.abs (8-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(9);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(10, rollingAverage.getMax());
        assertTrue( Math.abs (8.6-rollingAverage.getAverage()) < 0.01);
        assertEquals(0, rollingAverage.getMaxAt());

        rollingAverage.putNextInt(10);
        System.out.println(rollingAverage.getAverageAndMAx());
        assertEquals(10, rollingAverage.getMax());
        assertTrue( Math.abs (9.2-rollingAverage.getAverage()) < 0.01);
        assertEquals(2, rollingAverage.getMaxAt());

        assertTrue(true);
    }

}
