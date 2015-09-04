package avachat.hackrank.algorithm.sorting;

import java.util.Scanner;

/**
 *
 Problem Statement

 Sorting
 One common task for computers is to sort data. For example, people might want to see all their files on a computer sorted by size. Since sorting is a simple problem with many different possible solutions, it is often used to introduce the study of algorithms.

 Insertion Sort
 These challenges will cover Insertion Sort, a simple and intuitive sorting algorithm. We will first start with an already sorted list.

 Insert element into sorted list
 Given a sorted list with an unsorted number V in the rightmost cell, can you write some simple code to insert V into the array so that it remains sorted?

 Print the array every time a value is shifted in the array until the array is fully sorted. The goal of this challenge is to follow the correct order of insertion sort.

 Guideline: You can copy the value of V to a variable and consider its cell "empty". Since this leaves an extra cell empty on the right, you can shift everything over until V can be inserted. This will create a duplicate of each value, but when you reach the right spot, you can replace it with V.

 Input Format
 There will be two lines of input:

 s - the size of the array
 ar - the sorted array of integers
 Output Format
 On each line, output the entire array every time an item is shifted in it.

 Constraints
 1≤s≤1000
 −10000≤V≤10000,V∈ar

 Sample Input

 5
 2 4 6 8 3
 Sample Output

 2 4 6 8 8
 2 4 6 6 8
 2 4 4 6 8
 2 3 4 6 8
 Explanation

 3 is removed from the end of the array.
 In the 1st line 8>3, so 8 is shifted one cell to the right.
 In the 2nd line 6>3, so 6 is shifted one cell to the right.
 In the 3rd line 4>3, so 4 is shifted one cell to the right.
 In the 4th line 2<3, so 3 is placed at position 2.

 Task

 Complete the method insertionSort which takes in one parameter:

 ar - an array with the value V in the right-most cell.



 * Created by avachat on 9/3/15.
 */

/*
NOTE :
Once again made mistake while handling boundary condition.
 */
public class InsertionSort1 {

    private static void printArray(int[] sortedNums) {
        for (int i = 0; i < sortedNums.length; i++) {
            System.out.print(sortedNums[i]);
        }
        System.out.println();
    }

    private static void performInsertionSort(int[] sortedNums) {

        // take out the last element
        int numToAdd = sortedNums [sortedNums.length -1];

        int i = sortedNums.length - 2;
        while ( i >= 0 ) {
            if ( numToAdd >= sortedNums[i] ) {
                // found the place insert
                break;
            }
            // shift right
            sortedNums[i+1] = sortedNums[i];
            // print
            printArray (sortedNums);
            i--;
        }

        // add the number to i+1 location
        sortedNums[i+1] = numToAdd;
        printArray (sortedNums);
    }

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] nums = new int[size];

        for (int i = 0; i< size; i++) {
            nums[i] = scanner.nextInt();
        }

        performInsertionSort (nums);

    }

}

