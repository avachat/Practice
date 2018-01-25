package avachat.common.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper to generate primes.
 *
 * Created by avachat on 8/18/15.
 */

public class PrimeGenerator {

  /**
   * Generates primes using the sieve method.
   *
   * @param N The highest composite number that should be used to generate primes, N >= 1
   * @param primes Array big enough to hold the primes.
   * @return number of primes that were put in the array
   */
  public static int generate(int N, int[] primes) {

    // consider all numbers as prime to begin with
    // init array of N flags to false, if explicit init needed for your language
    boolean[] is_non_prime = new boolean[N + 1]; // first index is 0

    is_non_prime[0] = is_non_prime[1] = true; // 0 and 1 are not considered primes

    @SuppressWarnings("unused")
    int K = 0; // iterations

    for (int candidate = 2; candidate * candidate <= N; candidate++) {
      // there is no need to find composites of a non-prime number
      // as they would have been already marked by now by their prime factor
      if (is_non_prime[candidate]) {
        continue;
      }

      for (int composite = candidate * candidate; composite <= N; composite += candidate) {
        K++;
        is_non_prime[composite] = true;
      } //
    } //

    int num_primes = 0;
    for (int i = 2; i <= N; i++) {
      if (!is_non_prime[i]) {
        primes[num_primes] = i;
        ++num_primes;
      } //
    } //

    //System.out.println ("Iterations = " + K) ;
    return num_primes;
  }


  /**
   * Find prime factors of all numbers from 2 to N, both inclusive.
   *
   * The result is defined as follows.
   *
   * The factors will include all primes k that are a factor of N, such that 2 < k < N
   *
   * This means, 1, and the number N itself will NOT be in the list of factors, because they are trivial factors.
   *
   * This also means, for a prime number, the list of factors will be empty. That fact can be used to check primeness.
   *
   * To keep this definition consistent, for 0 and 1, the list of factors will be empty - as in undefined.
   *
   * @param N largest number to find factors for
   * @return list of factors for every integer from 2 up to N, will null list for 0 and 1
   */
  public static List<List<Integer>> generatePrimeFactors(int N) {

    if ( N <= 1 ) {
      throw new IllegalArgumentException("N must be greater than 1");
    }

    List<List<Integer>> allPrimeFactors = new ArrayList<>(N + 1); // first index is 0

    // Initialize the list
    allPrimeFactors.add(null); // ignore 0
    allPrimeFactors.add(null); // ignore 1
    // initialize from 2 onwards
    for (int i = 2; i <= N; i++){
      allPrimeFactors.add(new ArrayList<>());
    }

    // Start the sieve from 2 onwards
    //
    for (int candidate = 2; candidate <= N; candidate++) {

      //
      // there is no need to find composites of a non-prime number
      // as they would have been already marked by now by their prime factor
      //
      // To check for "prime-ness" just check if the number has any factors
      //
      if (!allPrimeFactors.get(candidate).isEmpty()) {
        // factors exist, so candidate is not a prime
        continue;
      }

      // NOTE : Now we know that candidate is a prime

      // The optimization for pure sieve can initialize composite = candidate*candidate
      // But here we want to find all candidate factors of all numbers.
      // We must mark what composites have the candidate as a factor.
      // So we must start from candidate*2
      // NOTE : We are NOT putting the number as it's own factor, even for primes
      // Only prime numbers k such that 1 < k < N are added to the factors list
      //
      for (int composite = 2 * candidate; composite <= N; composite += candidate) {
        // candidate is a factor of the composite
        allPrimeFactors.get(composite).add(candidate);
      } //
    }

    return allPrimeFactors;
  }


  public static List<Integer> generateRelativePrimes (int N, List<Integer> primeFactorsOfN) {

    if ( N <= 1) {
      throw new IllegalArgumentException("N must be > 1");
    }

    List<Integer> relativePrimes = new ArrayList<>();

    // Assume no number from 0 to N-1 shares common factor with N
    // Default init of boolean is false
    // Then perform a sieve operation to mark true for numbers with common factors
    //
    boolean[] hasCommonFactors = new boolean[N]; // first index is 0, last is N-1 : perfect

    // For every factor of N, start marking numbers at 'factor' intervals from N down to 0
    for (int factor : primeFactorsOfN) {
      // start with the first number : subtract factor from N
      // stop when relatedComposite becomes smaller than 2
      // NOTE : This also leaves indices 0 and 1 to remain false
      for (int relatedComposite = N - factor; relatedComposite > 1 ; relatedComposite -= factor) {
        hasCommonFactors [relatedComposite] = true;
      }
    }

    // now collect all the composite numbers that were marked true
    // Ignore 0 and 1, start with 2, and stop at N-1
    for (int i = 2; i < N ; i++) {
      if (!hasCommonFactors[i]) {
        relativePrimes.add(i);
      }
    }

    return relativePrimes;
  }


  public static void main(@SuppressWarnings("unused") String[] args) {

    int N = Integer.parseInt(args[0]);
    int[] primes = new int[N];

    int numPrimes = generate(N, primes);

    System.out.println(numPrimes);
    for (int i = 0; i < numPrimes; i++) {
      System.out.print(primes[i]);
      if (i != (numPrimes - 1)) {
        System.out.print(",");
      }
    }
    System.out.println();
  }
}
