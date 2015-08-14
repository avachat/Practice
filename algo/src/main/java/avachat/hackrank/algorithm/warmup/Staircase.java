package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/14/15.
 */
public class Staircase {

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int height = scanner.nextInt();

        for (int i = 0; i < height; i++) {
            int j = 0;
            for (; j < (height - i - 1); j++) {
                System.out.print(" ");
            }
            for (; j < height; j++) {
                System.out.print("#");
            }
            System.out.println("");
        }

    }
}
