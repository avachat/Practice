package avachat.udemy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WinProbability {


    /*


     */

    /*
     * Complete the 'pwin' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. DOUBLE_ARRAY x
     *  2. DOUBLE_ARRAY y
     *  3. DOUBLE tolerance
     */

    // Needs to return the probability for a random x to be greater than y

    public static double pwin(List<Double> x, List<Double> y, double tolerance) {
        // Write your code here

        long totalOutcomes = x.size() * y.size();

        // convert list to array
        double[] nums = new double[y.size()];
        int i = 0;
        for (double n : y) {
            nums[i++] = n;
        }

        // sort the array
        Arrays.sort(nums);

        long favourableOutcomes = calculateTotalFavourableCount(nums, x, tolerance);

        return (favourableOutcomes*1.0D/totalOutcomes);
    }


    public static long calculateTotalFavourableCount(double[] nums, List<Double> picks, double tolerance) {

        long result = 0;
        for (double pick : picks) {
            result += calculateFavourableCount(nums, pick, tolerance);
        }
        return result;
    }

    public static long calculateFavourableCount(double[] nums, double n, double tolerance) {
        return (long) findInsertIndex(nums, n, tolerance);
    }

    public static int findInsertIndex(double[] nums, double n, double tolerance) {

        int left = 0;
        int right = nums.length-1;
        while ( (left < right)) {
            int mid = left + ((right-left)/ 2);

            double absDiff = Math.abs(nums[mid]-n);
            if (absDiff < tolerance) {
                return mid;
            } else if ( nums[mid] >= n) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return (n <= nums[left]) ? left : left+1;

    }
}
