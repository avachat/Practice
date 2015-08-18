package avachat.euler;

import java.io.* ;
import java.util.* ;



/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

*/

public class Prob3LargestFactor_Try1
{


static List<Long> InputNumbersList ;

static long[] AllPrimes ;
static long[] LastNumCounted ;
static long TheLargestFactor ;

static {
    InputNumbersList = null ;
} // 


public static void printInputNumbersList ()
{
    Iterator<Long> iter = InputNumbersList.iterator () ;
    while ( iter.hasNext() ) {
	Long l = iter.next() ;
	System.out.print (l) ;
	System.out.print (" ") ;
    } // 

    System.out.println ("") ;

} // 


public static void createInputNumberList (String[] str_array)
{
    InputNumbersList = null ;
    InputNumbersList = new ArrayList<Long> (str_array.length) ;
    for (int i = 0 ; i < str_array.length ; i++) {
    	Long l = Long.parseLong (str_array[i]) ;
	InputNumbersList.add (new Long(l)) ;
    } // 
    //printInputNumbersList () ;

} // 



public static void solveForInputNumbersList ()
{
} // 


/**
 * See if n is a prime, based on all known primes so far
 * All known primes are in AllPrimes up to (and including) index i
 */
public static boolean isItPrime (long n, int i) {
    return false ;
} // 

public static void main (String argv[]) throws IOException
{

    if (argv.length != 1) {
    	System.out.println ("Usage :: java Euler.Prob <num>") ;
	System.exit (1) ;
    } // 

    /*
    String file_name = argv[0] ;
    //System.out.println ("Opening file " + file_name) ;


    BufferedReader input_file = new BufferedReader (new FileReader (file_name)) ;

    String first_line = input_file.readLine() ;
    int num_lines = Integer.parseInt (first_line.trim()) ;

    InputNumbersList = new ArrayList<Long> (num_lines) ;

    for (int ll = 0 ; ll < num_lines ; ll++) {
    	
	String line = input_file.readLine().trim() ;
	String [] str_array = line.split (" ") ;
	createInputNumberList (str_array) ;
	solveForInputNumbersList () ;
    }

    input_file.close() ;
    */

    long input_num = Long.parseLong (argv[0]) ;

    // if the number is < 3, then it's largest prime factor is itself
    if ( input_num <= 3 ) {
    	System.out.println ("Largest prime factor = 3") ;
	return ;
    } // 

    System.out.println ("(For N=" + input_num + ")" + "Diff = " + (Long.MAX_VALUE - input_num)) ;

    // natural log
    double log_num = Math.log (input_num) ;

    // very rough estimate of how many primes till num
    double num_primes_approx_estimate = input_num / log_num ;

    // the estimate is generally off by a factor of 1.1
    // so multiplying it by 1.2 makes it even safe
    double prime_counting_function_max = 1.2 * num_primes_approx_estimate ;

    long max_primes = (long) Math.ceil (prime_counting_function_max) ;

    if ( max_primes < 10 ) {
    	// force it to 10, so we have a minimum array size, just to avoid edge conditions
	max_primes = 10 ;
    } // 

    System.out.println ("(For N=" + input_num + ")" + "Estimated prime numbers till given num = " + max_primes) ;

    System.out.println ("(For N=" + input_num + ")" + "Int diff = " + (Integer.MAX_VALUE - max_primes)) ;

    //LastNumCounted = new long[max_primes];

    AllPrimes = new long[(int)max_primes] ;
    AllPrimes [0] = 1 ;
    AllPrimes [1] = 2 ;
    AllPrimes [2] = 3 ;

    TheLargestFactor = 1 ; // any number is definitely divisible by 1 :-)

    // keep generating primes
    long n = 4 ; // start inspecting numbers from 4 (we have already saved primes upto 3)
    int i = 3 ; // index for storing next prime
    while ( n <= input_num ) {
	if ( isItPrime (n, i) ) {
	    i ++ ; // go to next location
	    AllPrimes [i] = n ; // store the newly found prime
	    // is it a factor ?
	    if ( (input_num % n) == 0 ) {
	    	TheLargestFactor = n ;
	    } // 
	} // 
    	n ++ ;
    } // 

} // 



}
