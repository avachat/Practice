package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/17/15.
 */
/*

Problem Statement

The Head Librarian at a library wants you to make a program that calculates the fine for returning the book after the return date. You are given the actual and the expected return dates. Calculate the fine as follows:

If the book is returned on or before the expected return date, no fine will be charged, in other words fine is 0.
If the book is returned in the same month as the expected return date, Fine = 15 Hackos × Number of late days
If the book is not returned in the same month but in the same year as the expected return date, Fine = 500 Hackos × Number of late months
If the book is not returned in the same year, the fine is fixed at 10000 Hackos.
Input Format

You are given the actual and the expected return dates in D M Y format respectively. There are two lines of input. The first line contains the D M Y values for the actual return date and the next line contains the D M Y values for the expected return date.

Constraints
1≤D≤31
1≤M≤12
1≤Y≤3000
Output Format

Output a single value equal to the fine.

Sample Input

9 6 2015
6 6 2015
Sample Output

45
Explanation

Since the actual date is 3 days late than expected, fine is calculated as 15×3=45 Hackos.


 */
public class LibraryFine {

    /**
     * NOTE :
     * Return date may be earlier than actual date
     * @return
     */
    public static int calculateFine() {

        Scanner scanner = new Scanner(System.in);

        int actualDate = scanner.nextInt();
        int actualMonth = scanner.nextInt();
        int actualyear = scanner.nextInt();
        int expectedDate = scanner.nextInt();
        int expectedMonth = scanner.nextInt();
        int expectedyear = scanner.nextInt();

        if ( actualyear < expectedyear ) {
            return 0;
        }
        if ( expectedyear < actualyear ) {
            return 10000;
        }

        if ( actualMonth < expectedMonth ) {
            return 0;
        }
        if ( expectedMonth < actualMonth ) {
            return ( 500 * (actualMonth - expectedMonth));
        }

        if ( actualDate <= expectedDate ) {
            return 0;
        }
        if ( expectedDate < actualDate ) {
            return ( 15 * (actualDate - expectedDate));
        }

        return 0;
    }

    public static void main (String args[]) {

        System.out.println (calculateFine());
    }

}
