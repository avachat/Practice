package avachat.hackrank.algorithm.sorting;

import java.util.Scanner;

/**
 * Binary search
 *
 * MISTAKES made
 * 1. Initially, left/right was being set to middle for next iteration
 * but left should be set to middle+1 and right to middle-1
 *
 * 2. When that happens, left==right might be the last valid iteration
 * So terminating condition must be left > right
 *
 * Created by avachat on 9/3/15.
 */
public class Intro {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        int target = scanner.nextInt();
        int size = scanner.nextInt();
        int[] nums = new int[size];

        for (int i = 0; i< size; i++) {
            nums[i] = scanner.nextInt();
        }

        int left = 0 ;
        int right = size -1 ;
        int middle = 0;

        while ( left <= right ) {

            middle = (left + right) / 2;
            if ( nums[middle] == target) {
                break;
            }

            if (target > nums[middle]) {
                left = middle+1;
            }
            else {
                right = middle-1;
            }

        }
        // target is guranteed by problem definition to be present
        System.out.println (middle);
    }
}
