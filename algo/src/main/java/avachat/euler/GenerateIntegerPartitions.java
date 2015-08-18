package avachat.euler;

import java.util.* ;


/*
Let p(n) represent the number of different ways in which n coins can be separated into piles. For example, five coins can separated into piles in exactly seven different ways, so p(5)=7.
OOOOO
OOOO   O
OOO   OO
OOO   O   O
OO   OO   O
OO   O   O   O
O   O   O   O   O

Find the least value of n for which p(n) is divisible by one million.
*/

public class GenerateIntegerPartitions {

public static final int MAX = 100 ;
private static Set<String> AllPartitionStrings = new HashSet<String> (MAX) ;



public static long recursivePP (int N, int k) {

    if ( N <= 0 ) return 0 ;
    if ( N < k) return 0 ;
    if ( N == k) return 1 ;

    return ( recursivePP (N-k, k) + recursivePP (N, k+1) ) ;

} // 



/**
 * This will generate partitions 

 * for example, for N = 3, it will generate
 * 0, 0, 3
 * 0, 1, 2
 * 1, 1, 1
 *
 * For 4, it will generate
 * 0, 0, 0, 4
 * 0, 0, 1, 3
 * 0, 0, 2, 2
 * 0, 1, 1, 2
 * 1, 1, 1, 1
 *
 * For 5, it will generate
 * 0, 0, 0, 0, 5
 * 0, 0, 0, 1, 4
 * 0, 0, 0, 2, 3
 * 0, 0, 1, 1, 3
 * 0, 0, 1, 2, 2
 * 0, 1, 1, 1, 2
 * 1, 1, 1, 1, 1
 *
 * 5, 0, 0, 0, 0
 * 4, 1, 0, 0, 0
 */
public static void generatePartitions (int N) {

    long t1, t2 ;
    t1 = System.currentTimeMillis () ;
    System.out.println ("Generating integer partitions of " + N ) ;

    //final int MAX_PARTITIONS = 10000 ;
    //final int NUM_PARTITIONS = recursivePP (N, 1) ;

    int [] partitions = new int[N] ;

    partitions[N-1] = N ; // initialize
    String str_partition = Arrays.toString (partitions) ;

    System.out.println ("Found partition " + str_partition) ;
    AllPartitionStrings.add (str_partition) ;

    int num_partitions = 1 ; // so far, we have 1 partition

    boolean check_again = true ;
    while (check_again) {

	check_again = false ; // set to true later, if we changesometing

	// start with the leftmost partition that is greater than 1
	// proceed left, till you find a partition that is atleast 2 less than this
	// if found increment the left partition, and decrement the right partition
	// if not found, move right more time and keep trying

	// starting the loop from 1, cannot decrement 
	int remove_from = -1 ;
	for (int i = 1 ; i < N ; i++) {
		if (partitions [i] >= 2) {
		    remove_from = i ;
		    break ; // found the leftmost partition > 2
		} // 
	} // 

	if (remove_from == -1) {
	    // could not find anything to decrement
	    break ; // from while check_again
	} // 

	int add_to = -1 ;
	for (int i = remove_from -1 ; i >= 0 ; i-- ) {
	    if ( (partitions[remove_from] - partitions[i]) >= 2 ) {
		// diff is more than 2
		add_to = i ;
		break ;
	    } // 
	} // 

	if (add_to == -1) {
	    // could not find anything to increment
	    System.out.println ("ERROR:Could not find anything to increment") ;
	    return ;
	} // 

	partitions[remove_from] = partitions[remove_from] - 1 ;
	partitions[add_to] = partitions[add_to] + 1 ;

	++ num_partitions ;

        // assert
	// the current partition does not exist in the set
	str_partition = Arrays.toString (partitions) ;
	if ( AllPartitionStrings.contains (str_partition) ) {
	    System.out.println ("ERROR : duplicate partition " + str_partition) ;
	    return ;
	} // 
    	
    	System.out.println ("Found partition " + str_partition) ;
	AllPartitionStrings.add (str_partition) ;

	check_again = true ;
    } // 


    t2 = System.currentTimeMillis () ;
    System.out.println ("Time taken for generating partitions = " + (t2-t1) ) ;


    // assert
    // the last partition generated is all 1s

    // assert number of partitions generated is equal to the formula
    System.out.println ("Number of partitions generated = " + num_partitions) ;
    System.out.println ("Number of partitions expected = " + recursivePP (N, 1)) ;

} // 



public static void main (String argv[]) throws Exception {

    int N = Integer.parseInt (argv[0]) ;

    generatePartitions (N) ;

} // 

}

