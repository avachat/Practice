package avachat.hackrank.algorithm.sorting;

import java.util.Scanner;

/**
 * Created by avachat on 9/17/15.
 */
public class QuickSortInPlace {

    public static void swap (int[] ar, int i, int j) {
        if ( i == j ) {
            return;
        }
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }

    public static int partition (int[] ar, int from, int to) {

        if ( from >= to ) {
            throw new IllegalArgumentException ("Cannot partition when left = " + from + " and right = " + to);
        }

        // leftmost element is pivot
        int left = from;
        int right = to;
        int pivot = ar[left];

        // start partitioning from first element to the right of pivot
        left++;

        while ( left < right ) {

            // keep advancing left till an element greater than pivot is found
            if ( ar[left] <= pivot ) {
                left++;
                continue;
            }

            // keep decrementing right till an element less than pivot is found
            if ( ar[right] > pivot ) {
                right--;
                continue;
            }

            // found a pair that needs to be swapped
            swap (ar, left, right);
            left++;
            right--;

        }

        // while loop stopped, because left == right, or left = right+1
        // if left == right, then element at that index has not been compared yet
        if ( left == right ) {
            if ( ar[left] <= pivot) {
                // need to place pivot here
                swap(ar, from, left);
                return left; // pivot was placed here
            }
        }

        // now, element at left is definitely higher than pivot
        // and element at left-1 is definitely <= pivot
        // so swal pivot with left-1
        swap (ar, from, left-1);
        return left-1;

    }

    public static void recursiveQuickSort(int[] ar, int left, int right) {

        if ( left > right ) {
            throw new IllegalArgumentException("left " + left + " < right = " + right);
        }

        if ( left == right ) {
            return;
        }

        // partition
        int pivotAt = partition(ar, left, right);

        // sort left side
        if ( pivotAt > left) recursiveQuickSort(ar, left, pivotAt-1);
        // sort right side
        if ( pivotAt < right) recursiveQuickSort(ar, pivotAt+1, right);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

}
