package avachat.hackrank.algorithm.dynamic_programming;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Problem Statement
 * <p/>
 * A series is defined in the following manner:
 * <p/>
 * Given the nth and (n+1)th terms, the (n+2)th can be computed by the following relation
 * Tn+2 = (Tn+1)2 + Tn
 * <p/>
 * So, if the first two terms of the series are 0 and 1:
 * the third term = 12 + 0 = 1
 * fourth term = 12 + 1 = 2
 * fifth term = 22 + 1 = 5
 * ... And so on.
 * <p/>
 * Given three integers A, B and N, such that the first two terms of the series (1st and 2nd terms) are A and B respectively, compute the Nth term of the series.
 * <p/>
 * Input Format
 * <p/>
 * You are given three space separated integers A, B and N on one line.
 * <p/>
 * Input Constraints
 * 0 <= A,B <= 2
 * 3 <= N <= 20
 * <p/>
 * Output Format
 * <p/>
 * One integer.
 * This integer is the Nth term of the given series when the first two terms are A and B respectively.
 * <p/>
 * Note
 * <p/>
 * Some output may even exceed the range of 64 bit integer.
 * Sample Input
 * <p/>
 * 0 1 5
 * Sample Output
 * <p/>
 * 5
 * Explanation
 * <p/>
 * The first two terms of the series are 0 and 1. The fifth term is 5. How we arrive at the fifth term, is explained step by step in the introductory sections.
 * <p/>
 * Created by avachat on 9/3/15.
 */
public class ModifiedFibonacci {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BigInteger fibN = new BigInteger(String.valueOf(scanner.nextInt()));
        BigInteger fibN1 = new BigInteger(String.valueOf(scanner.nextInt()));

        int n = scanner.nextInt();

        BigInteger fib = BigInteger.ZERO ; // init

        for (int i = 3; i <= n; i++) {
            fib = fibN.add(fibN1.multiply(fibN1));
            fibN = fibN1;
            fibN1 = fib;
        }

        System.out.println(fib);

    }

}
