package avachat.common.math;

import static avachat.common.math.PrimeGenerator.generatePrimeFactors;

import java.util.List;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by avachat on 8/18/15.
 */
public class PrimeGeneratorTest {


    @Test (expected = Exception.class)
    public void testFactor_negative() {
        generatePrimeFactors(-10);
    }

    @Test (expected = Exception.class)
    public void testFactor_0() {
        generatePrimeFactors(0);
    }

    @Test (expected = Exception.class)
    public void testFactor_1() {
        generatePrimeFactors(1);
    }

    @Test
    public void testFactor_2() {
        List<List<Integer>> allPrimeFactors = PrimeGenerator.generatePrimeFactors(2);
        Assert.assertEquals(3, allPrimeFactors.size());
        Assert.assertNull(allPrimeFactors.get(0));
        Assert.assertNull(allPrimeFactors.get(1));
        List<Integer> factors_2 = allPrimeFactors.get(2);
        Assert.assertEquals(0, factors_2.size());
    }

    @Test
    public void testFactor_3() {
        List<List<Integer>> allPrimeFactors = PrimeGenerator.generatePrimeFactors(3);
        Assert.assertEquals(4, allPrimeFactors.size());
        Assert.assertNull(allPrimeFactors.get(0));
        Assert.assertNull(allPrimeFactors.get(1));
        List<Integer> factors_3 = allPrimeFactors.get(3);
        Assert.assertEquals(0, factors_3.size());
    }

    @Test
    public void testFactor_4() {
        List<List<Integer>> allPrimeFactors = PrimeGenerator.generatePrimeFactors(4);
        Assert.assertEquals(5, allPrimeFactors.size());
        Assert.assertNull(allPrimeFactors.get(0));
        Assert.assertNull(allPrimeFactors.get(1));
        List<Integer> factors_4 = allPrimeFactors.get(4);
        Assert.assertEquals(factors_4, ImmutableList.of(2));
    }

    @Test
    public void testFactor_1000() {
        List<List<Integer>> allPrimeFactors = PrimeGenerator.generatePrimeFactors(1000);
        Assert.assertEquals(1001, allPrimeFactors.size());

        Assert.assertNull(allPrimeFactors.get(0));
        Assert.assertNull(allPrimeFactors.get(1));

        List<Integer> factors_30 = allPrimeFactors.get(30);
        Assert.assertEquals(factors_30, ImmutableList.of(2, 3, 5));

        List<Integer> factors_100 = allPrimeFactors.get(100);
        Assert.assertEquals(factors_100, ImmutableList.of(2, 5));

        List<Integer> factors_105 = allPrimeFactors.get(105);
        Assert.assertEquals(factors_105, ImmutableList.of(3, 5, 7));

        List<Integer> factors_512 = allPrimeFactors.get(512);
        Assert.assertEquals(factors_512, ImmutableList.of(2));

        List<Integer> factors_1000 = allPrimeFactors.get(1000);
        Assert.assertEquals(factors_1000, ImmutableList.of(2, 5));

    }



    @Test
    public void testGenerate_1() throws Exception {
        int[] primes = new int[1];
        int numPrimes = PrimeGenerator.generate(1, primes);
        Assert.assertTrue(numPrimes == 0);
    }

    @Test
    public void testGenerate_2() throws Exception {
        int[] primes = new int[2];
        int numPrimes = PrimeGenerator.generate(2, primes);
        Assert.assertTrue(numPrimes == 1);
        Assert.assertTrue(primes[0] == 2);
    }

    @Test
    public void testGenerate_3() throws Exception {
        int[] primes = new int[3];
        int numPrimes = PrimeGenerator.generate(3, primes);
        Assert.assertTrue(numPrimes == 2);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
    }

    @Test
    public void testGenerate_4() throws Exception {
        int[] primes = new int[4];
        int numPrimes = PrimeGenerator.generate(4, primes);
        Assert.assertTrue(numPrimes == 2);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
    }

    @Test
    public void testGenerate_5() throws Exception {
        int[] primes = new int[5];
        int numPrimes = PrimeGenerator.generate(5, primes);
        Assert.assertTrue(numPrimes == 3);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
        Assert.assertTrue(primes[2] == 5);
    }

    @Test
    public void testGenerate_20() throws Exception {
        int[] primes = new int[20];
        int numPrimes = PrimeGenerator.generate(20, primes);
        Assert.assertTrue(numPrimes == 8);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
        Assert.assertTrue(primes[2] == 5);
        Assert.assertTrue(primes[7] == 19);
    }

    @Test
    public void testGenerate_100() throws Exception {
        int[] primes = new int[100];
        int numPrimes = PrimeGenerator.generate(100, primes);
        Assert.assertTrue(numPrimes == 25);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
        Assert.assertTrue(primes[2] == 5);
        Assert.assertTrue(primes[7] == 19);
        Assert.assertTrue(primes[9] == 29);
        Assert.assertTrue(primes[19] == 71);
        Assert.assertTrue(primes[24] == 97);
    }

    @Test
    public void testGenerate_545() throws Exception {
        int[] primes = new int[545];
        int numPrimes = PrimeGenerator.generate(545, primes);
        Assert.assertTrue(numPrimes == 100);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
        Assert.assertTrue(primes[2] == 5);
        Assert.assertTrue(primes[7] == 19);
        Assert.assertTrue(primes[9] == 29);
        Assert.assertTrue(primes[19] == 71);
        Assert.assertTrue(primes[24] == 97);
        Assert.assertTrue(primes[99] == 541);
    }

    @Test
    public void testGenerate_7920() throws Exception {
        int[] primes = new int[7920];
        int numPrimes = PrimeGenerator.generate(7920, primes);
        Assert.assertTrue(numPrimes == 1000);
        Assert.assertTrue(primes[0] == 2);
        Assert.assertTrue(primes[1] == 3);
        Assert.assertTrue(primes[2] == 5);
        Assert.assertTrue(primes[7] == 19);
        Assert.assertTrue(primes[9] == 29);
        Assert.assertTrue(primes[19] == 71);
        Assert.assertTrue(primes[24] == 97);
        Assert.assertTrue(primes[99] == 541);
        Assert.assertTrue(primes[999] == 7919);
    }

}