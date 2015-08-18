package avachat.euler;

public class PrimeBrut
{

public static void main (String args[]) throws Exception
{

    int N = Integer.parseInt (args[0]) ;
    //System.out.println ("Finding primes till " + N) ;
    
    // consider all numbers as non-primes to begin with
    // init array of N flags to false, if explicit init needed for your language
    boolean is_prime[] = new boolean [N+1] ; // first index is 0

    is_prime [0] = is_prime [1] = false ; // 0 and 1 are not considered primes
    is_prime [2] = is_prime[3] = true ; // initialization

    int K = 0 ; // iterations

    // consider only odd numbers
    for (int candidate = 3 ; candidate <= N ; candidate += 2) {

	// check divisibility with previous primes
	// assume it's prime, and mark as composite if a factor is found
	is_prime [candidate] = true ;
	for ( int prime = 3 ; prime <= Math.sqrt(candidate) ; prime += 2 ) {
	    if ( ! is_prime[prime] )
	    	continue ;
	    K++ ;
	    if ( candidate % prime == 0 ) {
	    	is_prime [candidate] = false ;
		break ;
	    } // 
	} // 
    } // 

    for (int i = 2 ; i <= N ; i++) {
    	if ( is_prime[i] ) {
	    System.out.println (i) ;
	} // 
    } // 

    System.out.println ("Iterations = " + K) ;


} // 
	
} // 

