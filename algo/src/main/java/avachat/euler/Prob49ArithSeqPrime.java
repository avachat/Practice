package avachat.euler;

import java.math.* ;
import java.io.* ;
import java.util.* ;



/*
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
*/

public class Prob49ArithSeqPrime
{

public static final long InputNumber = 9999 ;
public static final int NumberOfDigits = (int) (Math.ceil(Math.log10(InputNumber))) ;
public static final long Floor = (long) (Math.pow(10,NumberOfDigits-1)) ;
public static final long Ceiling = (long) (Math.pow(10,NumberOfDigits)) ;
public static final long PowerOf2 [] ;

public static List<Long> AllPrimes = null ;
public static Map<String, List<Long>> BucketedPrimes = new HashMap<String, List<Long>> () ;
public static SortedMap<Long, Collection<SortedSet<Long>>> ArithSequences = new TreeMap <Long, Collection<SortedSet<Long>>> () ;



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



public static String getHashValue (long prime)
{

    int digits_count [] = new int[10] ;

    //System.out.println ("Hashing " + prime) ;

    long pow10 = Floor ;
    while (pow10 != 1) {
	long digit = prime / pow10 ;
	//System.out.println ("digit = " + digit) ;
	digits_count[(int)digit] ++ ;
	prime -= digit*pow10 ;
    	pow10 /= 10 ;
    } // 
    //System.out.println ("last digit = " + prime) ;
    digits_count[(int)prime] ++ ;

    //System.out.println ("Digits count = " + Arrays.toString(digits_count)) ;
    return Arrays.toString(digits_count) ;

} // 

/**
 * Put the prime into the proper bucket
 *
 * Bucket is a list of primes, hashed based on the digits
 */
public static void insertIntoBucket (String hash_value, long prime)
{
    if ( ! BucketedPrimes.containsKey (hash_value) ) {
    	BucketedPrimes.put (hash_value, new ArrayList<Long>()) ;
    } // 

    List<Long> hashed_primes = BucketedPrimes.get (hash_value) ;
    hashed_primes.add (prime) ;
} // 



/**
 * For all primes of a certain number of digits, put them in buckets based on a hash number
 */
public static void createBuckets (int num_digits, List<Long> all_primes) {

    for (Long p : all_primes) {
	long prime = p.longValue() ;
    	if ( prime < Floor ) continue ;
	if ( prime > Ceiling ) break ;
	String hash_val = getHashValue (prime) ;

	insertIntoBucket (hash_val, prime) ;
    } // 

}


/**
 *  Finds an arithmatic sequence within a list of sorted primes
 */
public static void findSequence (List<Long> primes)
{

    // iterate over the list
    // starting from first element, till second last element
    // for every element, iterate over the list again to find all pairs
    // for every pair, find the difference, and hash the numbers according to the difference
    //
    // There can be multiple arithmatic sequences for the same difference.
    // See if the lower number is already part of any sequence for that diff.
    // If yes, then add the higher number to that set.
    // Else, create a new set, add both numbers to it and 
    // 
    // any list that has hashed to a difference, and has more than 2 elements represent a sequence
    //

    for (int i = 0 ; i < primes.size() - 1 ; i++ ) {
    	for ( int j = i+1 ; j < primes.size() ; j++ ) {
	    long diff = primes.get(j) - primes.get(i) ;
	    if ( ! ArithSequences.containsKey (diff) ) {
		//System.out.println ("Adding a new collection of sequence for diff " + diff) ;
		ArithSequences.put (diff, new ArrayList<SortedSet<Long>>()) ;
	    } // 
	    Collection<SortedSet<Long>> sequences_for_diff = ArithSequences.get (diff) ;
	    boolean found_set = false ;
	    for (SortedSet<Long> sequence : sequences_for_diff) {
		if ( sequence.contains (primes.get(i)) ) {
		    found_set = true ;
		    sequence.add (primes.get(j)) ;
		    //System.out.println ("Added to existing set " + primes.get(j)) ;
		    break ;
		} // 
	    } // 
	    if ( ! found_set ) {
		SortedSet<Long> sequence = new TreeSet<Long> () ;
		sequence.add (primes.get(i)) ;
		sequence.add (primes.get(j)) ;
		sequences_for_diff.add (sequence) ;
		//System.out.println ("Added to new set " + primes.get(i) + " and " + primes.get(j)) ;
	    } // 
	} // 
    } // 

    //System.out.println (ArithSequences) ;

} // 




public static void printArithSequences ()
{

    for (Long diff : ArithSequences.keySet()) {
	boolean is_label_printed = false ;
	Collection<SortedSet<Long>> sequences_for_diff = ArithSequences.get (diff) ;
	for (SortedSet<Long> sequence : sequences_for_diff) {
	    if (sequence.size() <= 2) {
	    	continue ;
	    } // 
	    if ( ! is_label_printed ) {
		System.out.print (diff + ": ") ;
		is_label_printed = true ;
	    } // 
	    System.out.print ("[") ;
	    boolean is_first_num = true ;
	    for (Long prime : sequence) {
		if (! is_first_num ) {
		    System.out.print (", ") ;
		} // 
	    	System.out.print (prime) ;
		is_first_num = false ;
	    } // 
	    System.out.print ("]    ") ;
	}
	if ( is_label_printed ) {
	    System.out.println ("") ;
	}
    } // 

} // 



public static void main (String argv[]) throws Exception
{

    /*
    if (argv.length != 1) {
    	System.out.println ("Usage :: java Euler.Prob <num>") ;
	System.exit (1) ;
    } // 

    InputNumber = Long.parseLong (argv[0]) ;

    if ( InputNumber <= 1 ) {
    	System.out.println ("1 Prime Number = " + 1) ;
	return ;
    } // 
    */

    // Initialize the list of examined primes to contain only 1
    List<Long> AllPrimes = new ArrayList<Long> () ;

    long prime = 1 ;
    for (long i = 2 ; prime <= InputNumber ; i++ ) {
    	prime = getNextPrime (AllPrimes) ;
    } // 

    //System.out.println (InputNumber + " Prime Number = " + prime) ;
    //System.out.println ("##### Primes found " + AllPrimes.size()) ;
    //System.out.println (AllPrimes) ;

    createBuckets (4, AllPrimes) ;

    for (List<Long> hashed_primes : BucketedPrimes.values()) {
	if ( hashed_primes.size() < 3 ) {
	    continue ;
	} // 
	Collections.sort (hashed_primes) ;
	System.out.println (hashed_primes) ;
	findSequence (hashed_primes) ;
    } // 

    /*
    // TEST
    long test_data[] = new long[] {2, 3, 4, 6, 8, 9, 10, 12, 14, 16, 18, 21, 24} ;
    List<Long> test_data_list = new ArrayList<Long> () ;
    for (long l : test_data) {
    	test_data_list.add (l) ;
    } // 
    findSequence (test_data_list) ;
    */

    //System.out.println ("ArithSequences size = " + ArithSequences.size()) ;
    printArithSequences () ;

} // main

} // class


