package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/13/15.
 */

/*
Problem Statement

You are given a square matrix of size N×N. Calculate the absolute difference of the sums across the two main diagonals
 */
public class DiagonalDiff {

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int matrixSize = scanner.nextInt();

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int num = scanner.nextInt();
                if (i == j) {
                    sum1 += num;
                }
                if ( (i + j) == (matrixSize - 1)) {
                    sum2 += num;
                }
            }
        }

        System.out.println (Math.abs(sum1 - sum2));
    }

}
