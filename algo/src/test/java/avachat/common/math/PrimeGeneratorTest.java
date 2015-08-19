package avachat.common.math;

import junit.framework.TestCase;

/**
 * Created by avachat on 8/18/15.
 */
public class PrimeGeneratorTest extends TestCase {

    public void testGenerate_1() throws Exception {
        int[] primes = new int[1];
        int numPrimes = PrimeGenerator.generate(1, primes);
        assertTrue(numPrimes == 0);
    }

    public void testGenerate_2() throws Exception {
        int[] primes = new int[2];
        int numPrimes = PrimeGenerator.generate(2, primes);
        assertTrue(numPrimes == 1);
        assertTrue(primes[0] == 2);
    }

    public void testGenerate_3() throws Exception {
        int[] primes = new int[3];
        int numPrimes = PrimeGenerator.generate(3, primes);
        assertTrue(numPrimes == 2);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
    }

    public void testGenerate_4() throws Exception {
        int[] primes = new int[4];
        int numPrimes = PrimeGenerator.generate(4, primes);
        assertTrue(numPrimes == 2);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
    }

    public void testGenerate_5() throws Exception {
        int[] primes = new int[5];
        int numPrimes = PrimeGenerator.generate(5, primes);
        assertTrue(numPrimes == 3);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
        assertTrue(primes[2] == 5);
    }

    public void testGenerate_20() throws Exception {
        int[] primes = new int[20];
        int numPrimes = PrimeGenerator.generate(20, primes);
        assertTrue(numPrimes == 8);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
        assertTrue(primes[2] == 5);
        assertTrue(primes[7] == 19);
    }

    public void testGenerate_100() throws Exception {
        int[] primes = new int[100];
        int numPrimes = PrimeGenerator.generate(100, primes);
        assertTrue(numPrimes == 25);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
        assertTrue(primes[2] == 5);
        assertTrue(primes[7] == 19);
        assertTrue(primes[9] == 29);
        assertTrue(primes[19] == 71);
        assertTrue(primes[24] == 97);
    }

    public void testGenerate_545() throws Exception {
        int[] primes = new int[545];
        int numPrimes = PrimeGenerator.generate(545, primes);
        assertTrue(numPrimes == 100);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
        assertTrue(primes[2] == 5);
        assertTrue(primes[7] == 19);
        assertTrue(primes[9] == 29);
        assertTrue(primes[19] == 71);
        assertTrue(primes[24] == 97);
        assertTrue(primes[99] == 541);
    }

    public void testGenerate_7920() throws Exception {
        int[] primes = new int[7920];
        int numPrimes = PrimeGenerator.generate(7920, primes);
        assertTrue(numPrimes == 1000);
        assertTrue(primes[0] == 2);
        assertTrue(primes[1] == 3);
        assertTrue(primes[2] == 5);
        assertTrue(primes[7] == 19);
        assertTrue(primes[9] == 29);
        assertTrue(primes[19] == 71);
        assertTrue(primes[24] == 97);
        assertTrue(primes[99] == 541);
        assertTrue(primes[999] == 7919);
    }

}