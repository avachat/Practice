package avachat.common.math;

/**
 * Created by avachat on 8/18/15.
 */

public class PrimeGenerator {

    /**
     *  Generates primes using the sieve method.
     *
     * @param N The highest composite number that should be used to generate primes, N >= 1
     * @param primes Array big enough to hold the primes.
     * @return number of primes that were put in the array
     */
    public static int generate (int N, int[] primes) {

        // consider all numbers as prime to begin with
        // init array of N flags to false, if explicit init needed for your language
        boolean is_non_prime[] = new boolean [N+1] ; // first index is 0

        is_non_prime [0] = is_non_prime [1] = true ; // 0 and 1 are not considered primes

        int K = 0 ; // iterations

        for (int candidate = 2 ; candidate*candidate <= N ; candidate ++) {
            // there is no need to find composites of a non-prime number
            // as they would have been already marked by now by their prime factor
            if ( is_non_prime[candidate] )
                continue ;

            for ( int composite = candidate * candidate ; composite <= N ; composite += candidate ) {
                K++ ;
                is_non_prime [composite] = true ;
            } //
        } //

        int num_primes = 0 ;
        for (int i = 2 ; i <= N ; i++) {
            if ( ! is_non_prime[i] ) {
                primes [num_primes] = i;
                ++ num_primes ;
            } //
        } //

        //System.out.println ("Iterations = " + K) ;
        return num_primes;
    }

    public static void main (String args[]) {

        int N = Integer.parseInt (args[0]) ;
        int[] primes = new int[N];

        int numPrimes = generate(N, primes);

        System.out.println (numPrimes);
        for (int i = 0; i < numPrimes; i++) {
            System.out.print(primes[i]);
            if ( i != (numPrimes - 1)) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
