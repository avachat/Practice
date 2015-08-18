package avachat.euler;

import java.math.* ;
import java.io.* ;
import java.util.* ;



/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

*/

public class Prob7NthPrime
{

public static long InputNumber = 1 ;

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

    if ( InputNumber <= 1 ) {
    	System.out.println ("1 Prime Number = " + 1) ;
	return ;
    } // 

    // Initialize the list of examined primes to contain only 1
    List<Long> AllPrimes = new ArrayList<Long> () ;

    long prime = 1 ;
    for (long i = 2 ; i <= InputNumber ; i++ ) {
    	prime = getNextPrime (AllPrimes) ;
    } // 

    System.out.println (InputNumber + " Prime Number = " + prime) ;
    System.out.println ("##### Primes found " + AllPrimes.size()) ;
    System.out.println (AllPrimes) ;

} // main

} // class

