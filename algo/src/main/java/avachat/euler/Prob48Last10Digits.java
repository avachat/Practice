package avachat.euler;

import java.io.* ;
import java.util.* ;



/*

The series, 11 + 22 + 33 + ... + 1010 = 10405071317.

Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.

*/

public class Prob48Last10Digits
{




public static void main (String argv[]) throws Exception
{

    if (argv.length != 2) {
    	System.out.println ("Usage :: java Prob48Last10Digits <MaxNum> <LastNDigits>") ;
	System.exit (1) ;
    } // 

    long max = Long.parseLong (argv[0]) ;
    long ndigits = Long.parseLong (argv[1]) ;

    System.out.println ("Will calculate last " + ndigits + " digits of powers through " + max) ;

    long modulo_base = (new Double (Math.pow (10, ndigits))).longValue() ;
    System.out.println ("Modulo base = " + modulo_base) ;

    long mod_sum = 0 ;
    for (long num = 1 ; num <= max ; num++) {
    	// compute power
	long mod_power = 1 ;
	for (int exp = 1 ; exp <= num ; exp++) {
	    long raw_power = mod_power * num ;
	    mod_power = raw_power % modulo_base ;
	} // 
	long raw_sum = mod_sum + mod_power ;
	mod_sum = raw_sum % modulo_base ;
    } // 

    System.out.println ("Modulo sum = " + mod_sum) ;

} // 



}
