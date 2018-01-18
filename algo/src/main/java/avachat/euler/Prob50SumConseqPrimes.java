package avachat.euler;

import java.math.* ;
import java.io.* ;
import java.util.* ;



/*
*/

public class Prob50SumConseqPrimes
{

public static long InputNumber = 1000000 ;
public static final int NumberOfDigits = (int) (Math.ceil(Math.log10(InputNumber))) ;
public static final long Floor = (long) (Math.pow(10,NumberOfDigits-1)) ;
public static final long Ceiling = (long) (Math.pow(10,NumberOfDigits)) ;
public static final long PowerOf2 [] ;

//public static List<Long> AllPrimes = null ;
public static TreeSet<Long> AllPrimes = null ;
public static Map<Long,List<List<Long>>> SumOfPrimes = null ;
public static Map<Long,List<List<Long>>> PrimeOfPrimes = null ;



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




/**
 * Eratosthenes Sieve
 */
public static void GenPrimesTillN (int N)
{

    //System.out.println ("Finding primes till " + N) ;

    // consider all numbers as prime to begin with
    // init array of N flags to false, if explicit init needed for your language
    boolean is_non_prime[] = new boolean [N+1] ; // first index is 0
    System.out.println ("Allocated " + (N+1) + " booleans");

    is_non_prime [0] = is_non_prime [1] = true ; // 0 and 1 are not considered primes

    int K = 0 ; // iterations

    long milepost = 10 ;
    long multiplier = 1 ;
    for (int candidate = 2 ; candidate < Math.sqrt(N) ; candidate ++) {
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
    // count number of primes and allocate space
    for (int i = 2 ; i <= N ; i++) {
    	if ( ! is_non_prime[i] ) {
	    ++ num_primes ;
	} //
    } //

    System.out.println ("Allocating " + num_primes + " primes");
    AllPrimes = new TreeSet<Long> () ;

    for (int i = 2 ; i <= N ; i++) {
    	if ( ! is_non_prime[i] ) {
	    //System.out.println (i) ;
	    AllPrimes.add (new Long(i)) ;
	    if ( i > (milepost * multiplier) ) {
	    	System.out.print (i + "...");
		multiplier++;
		if ( multiplier == 10) {
		    milepost *= 10;
		    multiplier = 1;
		} //
	    }
	} //
    } //

    System.out.println ("Iterations = " + K) ;
    System.out.println ("Num of primes = " + num_primes) ;


} //



public static void FindPrimesWithSumOfConsequtivePrimes (TreeSet<Long> all_primes) {

    // start from the first element
    // iterate over next elements
    // add while going forward
    // if sum is prime : add it to the result set
    // sequence of primes till then is what we need
    for (long p1 : all_primes) {
	//System.out.println ("\tp1 = " + p1) ;
	List<Long> members = new ArrayList <Long> (1000) ;
	long sum = p1 ;
	members.add (p1) ;
    	SortedSet<Long> next_primes = all_primes.tailSet (new Long(p1), false) ;
	boolean found_prime_sum = false ;
	for (long p : next_primes) {
	    sum += p ; // get the new sum
	    members.add (p) ; // p is part of the list of consequtive primes that add up to a prime
	    //System.out.println ("\tgot new sum " + sum + " = " + members) ;
	    // check is the sum is prime
	    // if yes, then break out of the loop
	    if ( all_primes.contains (sum) ) {
		found_prime_sum = true ;
		if ( ! SumOfPrimes.containsKey(sum) ) {
		    SumOfPrimes.put (sum, new ArrayList<List<Long>>()) ;
		} //
		List<List<Long>> members_list = SumOfPrimes.get(sum) ;
		members_list.add (new ArrayList<Long> (members)) ;
		//System.out.println (sum + " = " + members_list) ;
	    } //
	} //
    } //

} //



private static String ToString (List<List<Long>> members_list) {

    StringBuilder buf = new StringBuilder () ;

    for (List<Long> members : members_list) {
    	buf.append ("(") ;
	buf.append (members.size()) ;
	buf.append (")") ;
	buf.append (members) ;
	buf.append (" ") ;
    } //

    return buf.toString() ;

} //


public static void PrintLongestSequence(Map<Long,List<List<Long>>> result) {

    long prime = -1 ;
    List<Long> sequence = new ArrayList<Long>(0) ;

    long max_prime_ways = -1 ;
    long prime_with_max_prime_ways = -1 ;
    List<List<Long>> prime_ways = null;

    for (long sum : result.keySet()) {
	List<List<Long>> members_list = result.get (sum) ;
	System.out.println (members_list.size() + " :: " + sum + " = " + ToString (members_list)) ;
	for (List<Long> members : members_list) {
	    if (members.size() > sequence.size()) {
	    	prime = sum ;
		sequence = members ;
	    } //
	} //

	if ( members_list.size() >= max_prime_ways ) {
	    max_prime_ways = members_list.size();
	    prime_with_max_prime_ways = sum;
	    prime_ways = members_list;
	} //
    } //


    System.out.println ("\nLongest sequence is for " + prime + " = " + "(" + sequence.size() + ")" + sequence) ;


    System.out.println ("\nMost prime ways are for " + prime_with_max_prime_ways + ":" + prime_ways.size() + " = " + ToString(prime_ways)) ;

} //


/**
* Print sequencs whole length is prime
*/
public static void PrintPrimeLengthSequences() {


    long prime = -1 ;
    List<Long> sequence = new ArrayList<Long>(0) ;

    long max_prime_ways = -1 ;
    long prime_with_max_prime_ways = -1 ;
    List<List<Long>> prime_ways = null;

    long sum1=-1;
    for (long sum : SumOfPrimes.keySet()) {
	List<List<Long>> members_list = SumOfPrimes.get (sum) ;
	for (List<Long> member : members_list) {
	    if (AllPrimes.contains(new Long(member.size()))) {
		System.out.println ("Found sequence of prime length " + sum + " = " +  "(" + member.size() + ")" + member) ;
		if (member.size() >= sequence.size()) {
		    prime = sum ;
		    sequence = member ;
		} //
	    } //
	} //

	if ( members_list.size() >= max_prime_ways ) {
	    max_prime_ways = members_list.size();
	    prime_with_max_prime_ways = sum;
	    prime_ways = members_list;
	} //

	sum1 = sum;
    } //

    System.out.println ("\nAAA: Longest sequence of prime length is for " + prime + " = " + "(" + sequence.size() + ")" + sequence) ;

    System.out.println ("\nAAA: Most prime ways are for " + prime_with_max_prime_ways + ":" + prime_ways.size() + " = " + ToString(prime_ways)) ;

    System.out.println ("\nAAA: Highest prime is " + sum1) ;
}



/**
* Print sequencs whole length is prime
*/
public static void PrintWithOnlyPrimeLengthSequences() {


    long prime = -1 ;
    List<Long> sequence = new ArrayList<Long>(0) ;

    long max_prime_ways = -1 ;
    long prime_with_max_prime_ways = -1 ;
    List<List<Long>> prime_ways = null;

    long sum1=-1;
    for (long sum : SumOfPrimes.keySet()) {
	List<List<Long>> members_list = SumOfPrimes.get (sum) ;
	if ( members_list.size() <= 1 ) {
	    continue;
	} //
	boolean all_prime_lengths = true;
	for (List<Long> member : members_list) {
	    if (! AllPrimes.contains(new Long(member.size()))) {
		all_prime_lengths = false;
	    }
	}
	if ( ! all_prime_lengths) {
	    continue;
	}
	for (List<Long> member : members_list) {
	    if (AllPrimes.contains(new Long(member.size()))) {
		System.out.println ("Found sequence of prime length " + sum + " = " +  "(" + member.size() + ")" + member) ;
		if (member.size() >= sequence.size()) {
		    prime = sum ;
		    sequence = member ;
		} //
	    } //
	} //

	if ( members_list.size() >= max_prime_ways ) {
	    max_prime_ways = members_list.size();
	    prime_with_max_prime_ways = sum;
	    prime_ways = members_list;
	} //

	sum1 = sum;
    } //

    System.out.println ("\nBBB: Longest sequence of prime length is for " + prime + " = " + "(" + sequence.size() + ")" + sequence) ;

    System.out.println ("\nBBB: Most prime ways are for " + prime_with_max_prime_ways + ":" + prime_ways.size() + " = " + ToString(prime_ways)) ;

    System.out.println ("\nBBB: Highest prime is " + sum1) ;

}



/**
* Which of the primes can be written in N different ways such that N is a prime?
*/
public static void PrintPrimeNumberOfSequences() {


    long prime = -1 ;
    List<Long> longest_equence = new ArrayList<Long>(0) ;

    long max_prime_ways = -1 ;
    long prime_with_max_prime_ways = -1 ;
    List<List<Long>> prime_ways = null;

    long sum1=-1;
    for (long sum : SumOfPrimes.keySet()) {
	List<List<Long>> members_list = SumOfPrimes.get (sum) ;
	if ( members_list.size() <= 1 ) {
	    continue;
	} //
	if ( ! AllPrimes.contains (new Long(members_list.size()))) {
	    continue;
	} //
	System.out.println (members_list.size() + " :: " + sum + " = " + ToString (members_list)) ;
	for (List<Long> members : members_list) {
	    if (members.size() >= longest_equence.size()) {
	    	prime = sum ;
		longest_equence = members ;
	    } //
	} //

	if ( members_list.size() >= max_prime_ways ) {
	    max_prime_ways = members_list.size();
	    prime_with_max_prime_ways = sum;
	    prime_ways = members_list;
	} //

	sum1 = sum;
    } //


    System.out.println ("\nCCC: Longest sequence is for " + prime + " = " + "(" + longest_equence.size() + ")" + longest_equence) ;

    System.out.println ("\nCCC: Most prime ways are for " + prime_with_max_prime_ways + ":" + prime_ways.size() + " = " + ToString(prime_ways)) ;

    System.out.println ("\nCCC: Highest prime is " + sum1) ;
}



/**
* Which of the primes can be written in N different ways such that N is a prime?
*/
public static void PrintPrimeOfPrimes() {


    long prime = -1 ;
    List<Long> longest_equence = new ArrayList<Long>(0) ;

    long max_prime_ways = -1 ;
    long prime_with_max_prime_ways = -1 ;
    List<List<Long>> prime_ways = null;

    long sum1=-1;
    for (long sum : SumOfPrimes.keySet()) {
	List<List<Long>> members_list = SumOfPrimes.get (sum) ;
	if ( members_list.size() <= 1 ) {
	    continue;
	} //
	if ( ! AllPrimes.contains (new Long(members_list.size()))) {
	    continue;
	} //

	boolean found = true;
	for (List<Long> member : members_list) {
	    if ( ! AllPrimes.contains (new Long(member.size()))) {
		found = false;
		break;
	    } //
	} //

	if (!found) {
	    continue;
	} //

	for (List<Long> member : members_list) {
	    if (member.size() >= longest_equence.size()) {
	    	prime = sum ;
		longest_equence = member ;
	    } //

	    if ( members_list.size() >= max_prime_ways ) {
		max_prime_ways = members_list.size();
		prime_with_max_prime_ways = sum;
		prime_ways = members_list;
	    } //

	}

	System.out.println (members_list.size() + " :: " + sum + " = " + ToString (members_list)) ;

	sum1 = sum;
    } //


    System.out.println ("\nDDD: Longest sequence is for " + prime + " = " + "(" + longest_equence.size() + ")" + longest_equence) ;

    System.out.println ("\nDDD: Most prime ways are for " + prime_with_max_prime_ways + ":" + prime_ways.size() + " = " + ToString(prime_ways)) ;

    System.out.println ("\nDDD: Highest prime is " + sum1) ;
}


public static void SelectPrimeOfPrimes () {


    for (long sum : SumOfPrimes.keySet()) {
	List<List<Long>> members_list = SumOfPrimes.get (sum) ;
	if ( ! AllPrimes.contains (new Long(members_list.size()))) {
	    continue;
	} //

	boolean found = true;
	for (List<Long> member : members_list) {
	    if ( ! AllPrimes.contains (new Long(member.size()))) {
		found = false;
		break;
	    } //
	} //

	if ( found ) {
	    PrimeOfPrimes.put (sum, members_list);
	}

    } //

}



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

    System.out.println ("Input number = " + InputNumber);

    // Initialize the list of examined primes to contain only 1
    //AllPrimes = new ArrayList<Long> (100000) ;
    AllPrimes = new TreeSet<Long> () ;
    SumOfPrimes = new TreeMap <Long,List<List<Long>>> () ;
    PrimeOfPrimes = new TreeMap <Long,List<List<Long>>> () ;

    long last_prime = 1 ;
    /*
    for (long i = 2 ; last_prime <= InputNumber ; i++ ) {
    	last_prime = getNextPrime (AllPrimes) ;
    } //
    */

    GenPrimesTillN ((int)InputNumber) ;

    System.out.println (InputNumber + " Prime Number = " + last_prime) ;
    System.out.println ("##### Primes found " + AllPrimes.size()) ;
    //System.out.println (AllPrimes) ;

    /*
    long sum = 0 ;
    for (long prime : AllPrimes) {
    	sum += prime ;
    }
    System.out.println ("\nSum of primes = " + sum) ;
    */

    FindPrimesWithSumOfConsequtivePrimes (AllPrimes) ;

    PrintLongestSequence (SumOfPrimes) ;

    //System.out.println ("\n\nAAA:\nPrinting squences with prime length") ;
    //PrintPrimeLengthSequences();

    //System.out.println ("\n\nBBB:\nPrinting squences with only prime length") ;
    //PrintWithOnlyPrimeLengthSequences();

    //System.out.println ("\n\nCCC:\nPrinting squences with prime ways") ;
    //PrintPrimeNumberOfSequences() ;



    //System.out.println ("\n\nDDD:\nPrinting prime of primes") ;
    //PrintPrimeOfPrimes();
    //SelectPrimeOfPrimes();
    //PrintLongestSequence (PrimeOfPrimes) ;

} // main

} // class

