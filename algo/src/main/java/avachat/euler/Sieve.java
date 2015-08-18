package avachat.euler;

public class Sieve
{

public static void main (String args[]) throws Exception
{

    int N = Integer.parseInt (args[0]) ;
    //System.out.println ("Finding primes till " + N) ;
    
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
	    ++ num_primes ;
	    System.out.println (i) ;
	} // 
    } // 

    System.out.println ("Iterations = " + K) ;
    System.out.println ("Num of primes = " + num_primes) ;


} // 
	
} // 

