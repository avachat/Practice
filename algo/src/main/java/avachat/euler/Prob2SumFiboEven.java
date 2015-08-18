package avachat.euler;

import java.io.* ;
import java.util.* ;



/*
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
*/

public class Prob2SumFiboEven
{


static List<Long> InputNumbersList ;

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


public static void main (String argv[]) throws IOException
{

    if (argv.length != 1) {
    	System.out.println ("Usage :: java test.hack.FbWine <file_name>") ;
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

    long stop = Long.parseLong (argv[0]) ;

    long sum = 0 ;
    long fib1 = 1 ;
    long fib2 = 1 ;
    long fib3 = 2 ;

    while ( fib3 < stop ) {
    	sum += fib3 ;
	fib1 = fib2 + fib3 ;
	fib2 = fib3 + fib1 ;
	fib3 = fib1 + fib2 ;
    } // 

    System.out.println ("Sum = " + sum) ;

} // 



}
