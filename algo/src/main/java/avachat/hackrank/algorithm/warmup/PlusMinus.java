package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/13/15.
 */

/*
Problem Statement

You're given an array containing integer values. You need to print the fraction of count of positive numbers, negative numbers and zeroes to the total numbers. Print the value of the fractions correct to 3 decimal places.

Explanation

There are 3 positive numbers, 2 negative numbers and 1 zero in the array.
Fraction of the positive numbers, negative numbers and zeroes are 36=0.500, 26=0.333 and 16=0.167 respectively.

Note This challenge introduces precision problems. You can even print output to 4 decimal places and above but only the difference at 3rd decimal digit is noted. That is the reason you'll notice testcases have much higher precision (more decimal places) than required.
Answers with absolute error upto 10−4 will be accepted.

 */
public class PlusMinus {

    // assumption decimalPlaces > 0
    public static double roundDecimalPlaces (double num, int decimalPlaces) {

        // move the decimal point right
        double tenPower = Math.pow(10, decimalPlaces);
        double adjustedNum = num * tenPower;
        long roundAdjustedNum = Math.round(adjustedNum);
        double roundedNum = roundAdjustedNum / tenPower;

        return roundedNum;

    }

    public static void solve (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numInts = scanner.nextInt();

        int numPositive = 0;
        int numZeros = 0 ;
        int numNegatives = 0 ;

        for (int i = 0; i < numInts; i++) {

            int num = scanner.nextInt();

            if ( num == 0) {
                numZeros++;
            } else if ( num > 0) {
                numPositive++;
            } else {
                numNegatives++;
            }
        }

        System.out.printf ("%.3f%n", roundDecimalPlaces((numPositive*1.0/numInts), 3));
        System.out.printf ("%.3f%n", roundDecimalPlaces((numNegatives*1.0/numInts), 3));
        System.out.printf ("%.3f%n", roundDecimalPlaces((numZeros*1.0/numInts), 3));

    }

    public static void main (String args[]) {
        solve(args);
    }

}
