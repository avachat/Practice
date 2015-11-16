package avachat.algo;

import java.util.Random;

public class MaxSumSubsetMain {


    static void fillRandomValues(int input_array[]) {

        System.out.println("Filling " + input_array.length + " random values");

        Random random = new Random();
        for (int i = 0; i < input_array.length; i++) {
            input_array[i] = random.nextInt(10) - 5;
        } //

    } //


    static void printInputArray(int input_array[]) {
        System.out.println("Using input array :");
        for (int i = 0; i < input_array.length; i++) {
            System.out.println("\t[" + i + "] = " + input_array[i]);
        } //
        System.out.println("");
    } //


    static void calculateAllSums(int input_array[], int sum_table[][]) {
        // init first cell
        sum_table[0][0] = input_array[0];

        // start from next row - (row number 1)
        for (int i = 1; i < input_array.length; i++) {
            for (int j = 0; j < i; j++) {
                sum_table[i][j] = sum_table[i - 1][j] + input_array[i];
                sum_table[j][i] = sum_table[i][j]; // store at diagonal cell as well
            } //
            sum_table[i][i] = input_array[i];
        } //
    } //


    static void printAllSums(int sum_table[][]) {

        System.out.print("\t");
        for (int i = 0; i < sum_table.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        for (int i = 0; i < sum_table.length; i++) {
            System.out.print(i + "\t");
            int start_at = -1;
            int end_at = -1;
            int max_upto_i = Integer.MIN_VALUE;
            int max_from_i = Integer.MIN_VALUE;
            for (int j = 0; j < (sum_table[i]).length; j++) {
                System.out.print(sum_table[i][j] + "\t");
                if (i == j) {
                    // update both the sums
                    if (max_upto_i < sum_table[i][j]) {
                        start_at = j;
                        max_upto_i = sum_table[i][j];
                    } //
                    if (max_from_i < sum_table[i][j]) {
                        end_at = j;
                        max_from_i = sum_table[i][j];
                    } //
                } //
                else if (i < j) {
                    if (max_from_i < sum_table[i][j]) {
                        end_at = j;
                        max_from_i = sum_table[i][j];
                    } //
                } //
                else {
                    if (max_upto_i < sum_table[i][j]) {
                        start_at = j;
                        max_upto_i = sum_table[i][j];
                    } //
                } //
            } //
            System.out.print("s" + start_at + "=" + max_upto_i + " " + "e" + end_at + "=" + max_from_i);
            System.out.println("");
        } //

    } //


    static void findMaxSumsubset(int input_array[]) {

        // subset with global max sum
        // init to just use the first element
        int global_max_subset_sum = input_array[0];
        int global_max_subset_starts_at = 0;
        int global_max_subset_ends_at = 0;

        // subset that ends in prev element
        // init to just use the first element
        int current_max_subset_sum = input_array[0];
        int current_max_subset_starts_at = 0;
        int current_max_subset_ends_at = 0;

        for (int i = 1; i < input_array.length; i++) {
            // add it to best subset ending at prev cell
            int new_subset_sum = current_max_subset_sum + input_array[i];
            // is it better than just the cell itself ?
            if (new_subset_sum > input_array[i]) {
                // extend the sequence to include this cell
                current_max_subset_sum = new_subset_sum;
                current_max_subset_ends_at = i;
            } //
            else {
                // this cell by itself is better than extending the subset
                // reset the prev subset info
                current_max_subset_sum = input_array[i];
                current_max_subset_starts_at = i;
                current_max_subset_ends_at = i;
            } //

            // now we know the best subsequence that ends at this cell
            // is it better than global subset ?
            if (global_max_subset_sum < current_max_subset_sum) {
                // we have a new global subset
                global_max_subset_sum = current_max_subset_sum;
                global_max_subset_starts_at = current_max_subset_starts_at;
                global_max_subset_ends_at = current_max_subset_ends_at;
            } //

        } //

        System.out.println("\nMaxSumSubset ["
                           + global_max_subset_starts_at + "," + global_max_subset_ends_at + "] = "
                           + global_max_subset_sum);

    } //


    public static void main(String[] argv) {

        if (argv.length <= 0) {
            System.out.println("Usage :: MaxSubsetMain array-size");
            return;
        } //

        int input_array_size = Integer.parseInt(argv[0]);

        int input_array[] = new int[input_array_size];
        int sum_table[][] = new int[input_array_size][input_array_size];

        fillRandomValues(input_array);
        printInputArray(input_array);
        calculateAllSums(input_array, sum_table);
        printAllSums(sum_table);
        findMaxSumsubset(input_array);

        System.out.println("\n-----------------------------\n\n\n");
        input_array = new int[]{3, 4, 5, -7, 2};
        printInputArray(input_array);
        sum_table = new int[input_array.length][input_array.length];
        calculateAllSums(input_array, sum_table);
        printAllSums(sum_table);
        findMaxSumsubset(input_array);

        System.out.println("\n-----------------------------\n\n\n");
        input_array = new int[]{3, 4, 5, -7, 2, 4, 3};
        printInputArray(input_array);
        sum_table = new int[input_array.length][input_array.length];
        calculateAllSums(input_array, sum_table);
        printAllSums(sum_table);
        findMaxSumsubset(input_array);


    } //


}
