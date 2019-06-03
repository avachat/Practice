package avachat.algo;

public class BinarySearchIndexValues {

    public int printSearchProgress (int low, int high, int target) {

        int mid = (int) ( ((long)low + high)/2);
        System.out.println (low + " : " + mid + " : " + high + " for " + target);

        while (mid != target) {
            if (mid > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
            mid = (int) ( ((long)low + high)/2);
            System.out.println (low + " : " + mid + " : " + high + " for " + target);
        }

        return mid;
    }
}
