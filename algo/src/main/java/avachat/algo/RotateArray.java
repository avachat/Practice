package avachat.algo;

import java.util.Arrays;

/**
 * Created by avachat on 6/10/15.
 */
public class RotateArray {

    // empty constructor
    public RotateArray () {

    }

    public int[] rotateWithExtraArray (int[] inputArray, int numTimes) {

        if ( (inputArray == null) || (inputArray.length == 0) ) {
            throw new IllegalArgumentException ("Empty array");
        }

        // rotate modulo
        int actualNumTmes = numTimes % inputArray.length;

        if (  actualNumTmes == 0 ) {
            return Arrays.copyOf(inputArray, inputArray.length);
        }

        int[] outputArray = new int[inputArray.length];

        // 5 items
        // move left 2 times
        // item at index 0 should go to 3
        // which is 5 - 2
        //0 1 2 3 4
        //2 3 4 0 1

        // copy first actualnumTimes elements to the end of the array
        for ( int i = 0 ; i < actualNumTmes ; i++) {
            outputArray [i + inputArray.length - actualNumTmes] = inputArray [i];
        }

        // copy remaining elements to begining of the array
        for ( int j = actualNumTmes ; j < inputArray.length ; j++) {
            outputArray [j - actualNumTmes] = inputArray[j];
        }

        return outputArray;
    }

}
