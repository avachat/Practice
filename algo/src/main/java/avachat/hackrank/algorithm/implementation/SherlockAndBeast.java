package avachat.hackrank.algorithm.implementation;

import java.util.Scanner;

/**
 * Created by avachat on 8/17/15.
 */
/*
Problem Statement

Sherlock Holmes is getting paranoid about Professor Moriarty, his arch-enemy. All his efforts to subdue Moriarty have been in vain. These days Sherlock is working on a problem with Dr. Watson. Watson mentioned that the CIA has been facing weird problems with their supercomputer, 'The Beast', recently.

This afternoon, Sherlock received a note from Moriarty, saying that he has infected 'The Beast' with a virus. Moreover, the note had the number N printed on it. After doing some calculations, Sherlock figured out that the key to remove the virus is the largest Decent Number having N digits.

A Decent Number has the following properties:

3, 5, or both as its digits. No other digit is allowed.
Number of times 3 appears is divisible by 5.
Number of times 5 appears is divisible by 3.
Meanwhile, the counter to the destruction of 'The Beast' is running very fast. Can you save 'The Beast', and find the key before Sherlock?

Input Format
The 1st line will contain an integer T, the number of test cases. This is followed by T lines, each containing an integer N. i.e. the number of digits in the number.

Output Format
Largest Decent Number having N digits. If no such number exists, tell Sherlock that he is wrong and print −1.

Constraints
1≤T≤20
1≤N≤100000


Sample Input

4
1
3
5
11
Sample Output

-1
555
33333
55555533333
Explanation
For N=1, there is no such number.
For N=3, 555 is the only possible number.
For N=5, 33333 is the only possible number.
For N=11, 55555533333 and all permutations of these digits are valid numbers; among them, the given number is the largest one.


 */
public class SherlockAndBeast {

    /**
     * NOTE
     * Not all possibilities should be tried. We need only the largest number.
     * N doesn't need to be divisible by 8.
     *
     * What needs to happen is
     * if N is divisible by 3, great.
     * Else if N-5 is divisible 3, great.
     * Can keep subtracting 3, till it's divisible by 5.
     * Or try what is done here.
     *
     * N divisible by 3, great.
     * Else N-5 should be divisible by 3.
     * Else N-10 should be divisible by 3
     * No need to check N-15, as if that's divisible then so is N
     * Point is, in between this, the subtraction should not be negative.
     *
     * NOTE :
     * Hackerrank times out if print is called within a loop.
     * So a buf is needed.
     *
     * @param args
     */
    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++) {

            StringBuilder buf = new StringBuilder(100001);
            int numDigits = scanner.nextInt();

            if ( (numDigits % 3) == 0 ) {
                // divisible by 3, print all 5
                for (int j = 0; j < numDigits; j++) {
                    buf.append('5');
                }
                System.out.println(buf.toString());
                continue;
            }

            if ( numDigits < 5 ) {
                System.out.println ("-1");
                continue;
            }

            if ( (numDigits - 5) % 3 == 0 ) {
                for (int j = 0; j < (numDigits - 5); j++) {
                    buf.append('5');
                }
                buf.append("33333");
                System.out.println(buf.toString());
                continue;
            }

            if ( numDigits < 10 ) {
                System.out.println ("-1");
                continue;
            }

            if ( (numDigits - 10) % 3 == 0 ) {
                for (int j = 0; j < (numDigits - 10); j++) {
                    buf.append('5');
                }
                buf.append("3333333333");
                System.out.println(buf.toString());
                continue;
            }

            System.out.println ("-1");

        }

    }
}
