package avachat.hired;

public class Sample2 {

    // Array is a level representation of the tree : just like leetcode exercises
    public static String solution(long[] arr) {
        // Type your solution here

        if ( (null == arr) || (arr.length == 0) || (arr[0] == -1)) {
            return "";
        }

        long leftSize = getSizeForRootAt(arr, 1) ;
        long rightSize = getSizeForRootAt(arr, 2) ;

        if (leftSize == rightSize) {
            return "";
        }

        return (leftSize > rightSize) ? "Left" : "Right";

    }

    private static long getSizeForRootAt(long arr[], int i) {
        if ((i >= arr.length) || (arr[i] == -1)) {
            return 0;
        }
        long leftSize = getSizeForRootAt(arr, (2*i + 1)) ;
        long rightSize = getSizeForRootAt(arr, (2*i + 2)) ;
        return ( arr[i] + leftSize + rightSize);
    }

}
