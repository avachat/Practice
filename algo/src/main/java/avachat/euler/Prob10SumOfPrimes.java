package avachat.euler;

import java.util.ArrayList;
import java.util.List;



/*
*/

public class Prob10SumOfPrimes
{

public static long InputNumber = 2000000 ;
public static final int NumberOfDigits = (int) (Math.ceil(Math.log10(InputNumber))) ;
public static final long Floor = (long) (Math.pow(10,NumberOfDigits-1)) ;
public static final long Ceiling = (long) (Math.pow(10,NumberOfDigits)) ;
public static final long PowerOf2 [] ;

public static List<Long> AllPrimes = null ;



static {
    PowerOf2 = new long [NumberOfDigits] ;
    for (int i = 0, pow2 = 1 ; i < NumberOfDigits ; i++) {
    	PowerOf2 [i] = pow2*2 ;
    } //
} //


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
    List<Long> AllPrimes = new ArrayList<Long> (100000) ;

    long last_prime = 1 ;
    for (long i = 2 ; last_prime <= InputNumber ; i++ ) {
    	last_prime = getNextPrime (AllPrimes) ;
    } //

    System.out.println (InputNumber + " Prime Number = " + last_prime) ;
    System.out.println ("##### Primes found " + AllPrimes.size()) ;
    //System.out.println (AllPrimes) ;

    long sum = 0 ;
    for (long prime : AllPrimes) {
    	sum += prime ;
    }
    sum -= AllPrimes.get(AllPrimes.size());
    System.out.println ("\nSum of primes = " + sum) ;


} // main

} // class


