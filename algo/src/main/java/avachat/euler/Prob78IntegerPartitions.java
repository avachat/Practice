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

public class Prob78IntegerPartitions {

public static final int MAX_N = Integer.MAX_VALUE / 2 ;
//public static final int MAX_N = 20000 ;
public static final int MOD = 1000000 ;
public static long[][] PPvalues = new long[100][100] ;
public static List<long[]> PPvaluesList ;
public static long DirectPPValues [] = new long [MAX_N] ;
//public static long DirectPPValues [] = new long [MAX_N] ;

public static void init (int N) {

    System.out.println ("N = " + N) ;
    // use N as is, so PP(N) is NOT at N-1 index, it is at exactly N index
    PPvaluesList = new ArrayList <long[]> (N+1) ;

    // PPValues[0] wil never be accessed
    PPvaluesList.add (new long[1]) ; // index 0
    for (int i = 1 ; i <= N ; i++) {
    	//PPvaluesList.add (i, new long[i+1]) ;
    	PPvaluesList.add (new long[i+1]) ;
	//System.out.println ("Allocated array for i = " + i) ;
	if ( i % (N/10) == 0 ) {
	    System.out.println ("Allocated array for i = " + i) ;
	} // 
    } // 

} // 

public static long recursivePP (int N, int k) {

    if ( N <= 0 ) return 0 ;
    if ( N < k) return 0 ;
    if ( N == k) return 1 ;

    return ( recursivePP (N-k, k) + recursivePP (N, k+1) ) ;

} // 


public static long iterativePP (int N) {

    if ( N <= 0 ) return 0 ;

    // init P(N,k) for N=1, k=1
    PPvalues[1] [1] = 1 ;

    for (int i = 1 ; i <= N ; i++) {
    	
	// mark PP(N,k) where N == k
	PPvalues[i][i] = 1 ;

	for (int j = i-1 ; j >0 ; j--) {

	    //System.out.println ("i = " + i + " , j = " + j) ;
	    long pp1 = ((i-j) < j) ? 0 : PPvalues[i-j][j] ;
	    long pp2 = (i < (j+1)) ? 0 : PPvalues[i][j+1] ;
	    PPvalues[i][j] = ( pp1 + pp2 ) % MOD ;
	    //PPvalues[i][j] = PPvalues[i-j][j] + PPvalues[i][j+1] ;

	} // 

	//if ( (PPvalues.get(i][1] % 10L) == 0 ) {
	if ( PPvalues[i][1] == 0 ) {
	    System.out.println ("P(" + i + ") = " + PPvalues[i][1] ) ;
	} // 

    } // 

    /*
    for (int i = 0 ; i <= N ; i++) {
	System.out.print ("[" + (i) + "] ") ;
    	for (int j = 0 ; j <= i ; j++) {
	    System.out.print (PPvalue[i)[j]) ;
	    System.out.print (", ") ;
	} // 
	System.out.println ("") ;
    } // 
    */

    return PPvalues[N][1] ;
    //return 0 ;

}



public static long iterativePPList (int N) {

    if ( N <= 0 ) return 0 ;

    // init P(N,k) for N=1, k=1
    PPvaluesList.get(1) [1] = 1 ;

    for (int i = 1 ; i <= N ; i++) {
    	
	// mark PP(N,k) where N == k
	PPvaluesList.get(i)[i] = 1 ;

	for (int j = i-1 ; j >0 ; j--) {

	    //System.out.println ("i = " + i + " , j = " + j) ;
	    long pp1 = ((i-j) < j) ? 0 : PPvaluesList.get(i-j)[j] ;
	    long pp2 = (i < (j+1)) ? 0 : PPvaluesList.get(i)[j+1] ;
	    PPvaluesList.get(i)[j] = ( pp1 + pp2 ) % MOD ;
	    //PPvaluesList.get(i)[j] = PPvaluesList.get(i-j)[j] + PPvaluesList.get(i)[j+1] ;

	} // 

	//if ( (PPvaluesList.get(i)[1] % 10L) == 0 ) {
	if ( PPvaluesList.get(i)[1] == 0 ) {
	    System.out.println ("P(" + i + ") = " + PPvaluesList.get(i)[1] ) ;
	} // 

    } // 

    /*
    for (int i = 0 ; i <= N ; i++) {
	System.out.print ("[" + (i) + "] ") ;
    	for (int j = 0 ; j <= i ; j++) {
	    System.out.print (PPvaluesList.get(i)[j]) ;
	    System.out.print (", ") ;
	} // 
	System.out.println ("") ;
    } // 
    */

    return PPvaluesList.get(N)[1] ;
    //return 0 ;

}


public static long optimizedPP (int N) {

    DirectPPValues [0] = 1 ;
    DirectPPValues [1] = 1 ;
    DirectPPValues [2] = 2 ;

    if ( N <= 2 ) {
	return DirectPPValues[N] ;
    } // 

    for (int i = 3 ; i <= N ; i++ ) {
    
	long pn = 0 ;

	int sign = 0 ;
	long m1val = 0 ;
	long m2val = 0 ;

	int j ;
	for (j = 1 ; j <= i ; j++) {
	    int m1 = i - (j * (3*j - 1) / 2) ;
	    int m2 = i - ((-1*j) * (3*(-1*j) - 1) / 2) ;
	    if ( m1 < 0 ) {
	    	//if (i == 58) System.out.println ("For i = " + i + " done at " + j) ;
		break ;
	    } // 
	    sign = (j%2 == 0) ? -1 : 1 ;
	    m1val = (m1 < 0) ? 0 : (DirectPPValues[m1] % MOD) ;
	    m2val = (m2 < 0) ? 0 : (DirectPPValues[m2] % MOD) ;
	    //if (i == 58) System.out.println ("\tAdding " + sign + " * " + m1val) ;
	    //if (i == 58) System.out.println ("\tAdding " + sign + " * " + m2val) ;
	    pn = (pn + (sign * m1val) + (sign * m2val) ) % MOD ;
	    //if (i == 58) System.out.println ("\tpn = " + pn) ;
	    if ( pn < 0 ) {
	    	pn += MOD ;
	    } // 
	}

	/*
	if (pn < 0) {
	    System.out.println ("i = " + i) ;
	    System.out.println ("j = " + j) ;
	    System.out.println ("prev pn = " + pn) ;
	    System.out.println ("\tAdding " + sign + " * " + m1val) ;
	    System.out.println ("\tAdding " + sign + " * " + m2val) ;
	    return -1 ;
	} // 
	*/

	DirectPPValues[i] = pn ;
	/*
	if ( PPvaluesList.get(i)[1] != DirectPPValues[i]) {
	    System.out.println ("Mistmatch at i = " + i) ;
	    System.out.println ("iterative = " + PPvaluesList.get(i)[1]) ;
	    System.out.println ("optimized = " + DirectPPValues[i]) ;
	} // 
	*/
	if ( pn == 0 ) {
	    System.out.println ("P(" + i + ") = " + pn) ;
	    return i ;
	}
    }

    if ( 55374 == N) {
    	System.out.println ("For 55374 P = " + DirectPPValues[N]) ;
    } // 

    return DirectPPValues[N] ;

} // 

public static void main (String argv[]) throws Exception {

    long t1, t2 ;
    int N = Integer.parseInt (argv[0]) ;

    /*
    init (N) ;
    */

    /*
    t1 = System.currentTimeMillis () ;
    System.out.println ("Num of integer partitions of " + N + " = " + iterativePP(N)) ;
    t2 = System.currentTimeMillis () ;
    System.out.println ("Time taken for iterativePP = " + (t2-t1) ) ;
    */

    t1 = System.currentTimeMillis () ;
    System.out.println ("Num of integer partitions of " + N + " = " + optimizedPP(N)) ;
    t2 = System.currentTimeMillis () ;
    System.out.println ("Time taken for optimizedPP = " + (t2-t1) ) ;

    /*
    t1 = System.currentTimeMillis () ;
    System.out.println ("Num of integer partitions of " + N + " = " + recursivePP(N, 1)) ;
    t2 = System.currentTimeMillis () ;
    System.out.println ("Time taken for recursivePP = " + (t2-t1) ) ;
    */

} // 

}

