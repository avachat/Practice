package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/13/15.
 */
public class VeryBigSum {

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numInts = scanner.nextInt();

        long sum = 0;

        for (int i = 0; i < numInts; i++) {
            sum += scanner.nextInt();
        }

        System.out.println(sum);
    }

}
