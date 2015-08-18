package avachat.euler;

import java.math.* ;
import java.io.* ;
import java.util.* ;



/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

*/

public class Prob3LargestFactor
{

public static long InputNumber = 1 ;

public static Deque<Long> AllPrimeFactors = null ;
public static Set<Long> UniquePrimeFactors = null ;

public static List<Long> AllPrimes = null ;



/**
 * Is the number divisibale by all known primes ?
 */
public static boolean isDivisible (long num, List<Long> all_primes)
{

     //System.out.println ("\t\tisDivisible ? " + num) ;

     for (Long prime : all_primes) {
	//System.out.println ("\t\t... dividing by " + prime) ;
     	if ( (num % prime) == 0 ) {
	    return true ;
	} // 
     } // 

     return false ;
} // 


/**
 * find the next number, not divisible by any known primes till now
 */
public static long getNextPrime (List<Long> all_primes) {

    if (all_primes.size() == 0 ) {
    	all_primes.add (2L) ;
	return 2L ;
    } // 

    // get the last known prime and increment it
    long candidate = all_primes.get (all_primes.size() - 1) + 1 ;

    while ( isDivisible (candidate, all_primes) ) {
    	candidate ++ ;
    } // 

    //System.out.println ("\tnext prime = " + candidate) ;
    all_primes.add (candidate) ;
    return candidate ;
} // 


public static void main (String argv[]) throws Exception
{

    if (argv.length != 1) {
    	System.out.println ("Usage :: java Euler.Prob <num>") ;
	System.exit (1) ;
    } // 

    InputNumber = Long.parseLong (argv[0]) ;

    if ( InputNumber <= 3 ) {
    	System.out.println ("Largest Prime Factor = " + InputNumber) ;
	return ;
    } // 

    // Initialize the list of examined primes to contain only 1
    List<Long> AllPrimes = new ArrayList<Long> () ;
    //AllPrimes.add (1L) ;
    //AllPrimes.add (2L) ;
    //AllPrimes.add (3L) ;

    // Initialize the list of factors to 1 amd the number itself
    Deque<Long> AllPrimeFactors = new ArrayDeque<Long> () ;
    AllPrimeFactors.addLast (1L) ;
    //AllPrimeFactors.addLast (InputNumber) ; // assuming it to be prime
    //
    Set<Long> UniquePrimeFactors = new TreeSet<Long> () ;
    UniquePrimeFactors.add (1L) ;

    // ==========
    // Main algorithm
    //
    // 1. Get the next prime P.
    // 2. Peek at the last number N in AllPrimeFactors.
    // 3. while P divides N
    //	  a. Pop out N from AllPrimeFactors
    //	  b. Push P, N/P on AllPrimeFactors
    //	  c. Add P, N/P to UniqueFactors
    //    d. Set N/P to be the new value of N
    // 4. Repeat this till P > (InputNumber/2)

    long prime = getNextPrime (AllPrimes) ;
    long num = InputNumber ;

    while ( prime <= ( ((num)/2) + 1) ) {

	// is num is not divisible by prime, get next prime
	if ( (num % prime) != 0 ) {
	    prime = getNextPrime (AllPrimes) ;
	    continue ;
	} // 

	// we found a prime factor
	UniquePrimeFactors.add (prime) ;

	// keep dividing by this prime factor
	while ( (num % prime) == 0 ) {
	    long num_by_prime = num / prime ;
	    AllPrimeFactors.addLast (prime) ;
	    num = num_by_prime ;
	} // 

	// now num is no longer divisible by prime, so get next prime
	prime = getNextPrime (AllPrimes) ;

    } // 

    // The last number remaining needs to be added as a factor as well
    if ( num != 1 ) {
	AllPrimeFactors.addLast (num) ;
	UniquePrimeFactors.add (num) ;
    } // 

    /*
    if ( num != 1 ) {
	System.out.println ("Largest Prime Factor = " + num) ;
    } else {
	System.out.println ("Largest Prime Factor = " + AllPrimeFactors.peekLast()) ;
    } // 
    */

    System.out.println ("##### Primes found " + AllPrimes.size()) ;
    System.out.println (AllPrimes) ;
    System.out.println ("\n##### VERIFICATION") ;
    System.out.println ("### UniquePrimeFactors ") ;
    System.out.println (UniquePrimeFactors) ;
    System.out.println ("### AllPrimeFactors ") ;
    System.out.println (AllPrimeFactors) ;

    long verification_num = 1 ;
    for (Long prime_factor : AllPrimeFactors) {
    	verification_num *= prime_factor ;
    } // 

    System.out.println ("Result of multiplying all prime factors = " + verification_num) ;
    System.out.println ("Correct factorization ? " + ((InputNumber == verification_num) ? "YES" : "NO") ) ;

    System.out.println ("Largest Prime Factor = " + AllPrimeFactors.peekLast()) ;


} // main

} // class

