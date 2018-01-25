package avachat.euler;

import java.util.List;
import java.util.Scanner;

import avachat.common.math.PrimeGenerator;
import com.google.common.base.Stopwatch;

/**
 * Created by Abhay Avachat 212552612 on 1/23/18.
 */
public class Prob72CountingFractions {


  /**
   * Consider the fraction, n/d, where n and d are positive integers.
   * If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
   * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
   *    1/8, 1/7, 1/6, 1/5, 1/4,
   *    2/7, 1/3, 3/8, 2/5, 3/7,
   *    1/2, 4/7, 3/5, 5/8, 2/3,
   *    5/7, 3/4, 4/5, 5/6, 6/7,
   *    7/8
   *
   *    It can be seen that there are 21 elements in this set.
   *
   *    How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
   */

  public static long solveProb72CountingFractions(int N) {

    if ( N < 2) {
      throw new IllegalArgumentException("N must be > 2");
    }

    long totalNumOfProperFractions = 0;

    Stopwatch stopwatchFactors = Stopwatch.createStarted();

    // generate prime factors of all numbers till N
    List<List<Integer>> allPrimeFactors = PrimeGenerator.generatePrimeFactors(N);

    stopwatchFactors.stop();
    System.out.println("Time to calculate the prime factors = " + stopwatchFactors.elapsed().getSeconds() + " seconds");


    Stopwatch stopwatch = Stopwatch.createStarted();
    long prevMinutes = 0;
    // for each number from 2 to N, find it's relative primes
    // keep adding the count of that set
    for (int i = 2 ; i <= N ; i++) {

      // find relative primes for i
      List<Integer> relativePrimes = PrimeGenerator.generateRelativePrimes(i, allPrimeFactors.get(i));

      //System.out.println("relative primes for " + i + " = " + relativePrimes);

      // add to cumulative count
      // NOTE : the relative primes does NOT include number 1,
      // but for fractions, it should be considered
      // So add 1 to list size
      totalNumOfProperFractions += relativePrimes.size() + 1;

      if ( (i % 1000) == 0 ) {
        long minutes = stopwatch.elapsed().toMinutes();
        System.out.println(i + " cumulative = " + minutes + " incremental = " + (minutes-prevMinutes));
        prevMinutes = minutes;
      }
    }

    return totalNumOfProperFractions;
  }

  public static void main (String args[]) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Provide the target N");
    int N = scanner.nextInt();

    System.out.println("totalNumOfProperFractions till " + N + " = " + solveProb72CountingFractions(N));
  }

}
