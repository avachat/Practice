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
        int actualNumTimes = numTimes % inputArray.length;

        if (  actualNumTimes == 0 ) {
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
        for ( int i = 0 ; i < actualNumTimes ; i++) {
            outputArray [i + inputArray.length - actualNumTimes] = inputArray [i];
        }

        // copy remaining elements to begining of the array
        for ( int j = actualNumTimes ; j < inputArray.length ; j++) {
            outputArray [j - actualNumTimes] = inputArray[j];
        }

        return outputArray;
    }

    public void rotateInPlaceWithTempSpace (int[] inputArray, int numTimes, int tempSpaceLength) {

        if ( (inputArray == null) || (inputArray.length == 0) ) {
            throw new IllegalArgumentException ("Empty array");
        }

        // rotate modulo
        int actualNumTimes = numTimes % inputArray.length;

        if (  actualNumTimes == 0 ) {
            return;
        }

        int[] tempSpace = new int[tempSpaceLength];

        int rotationCount = 0 ;
        int remaining = actualNumTimes;

        while (remaining > 0) {

            int numTemps = (remaining < tempSpaceLength) ? remaining : tempSpaceLength ;

            // move numTemps items from left of array to temp space
            for (int i = 0; i < numTemps; i++) {
                tempSpace[i] = inputArray[i];
            }

            // move array left numTemps times
            for (int i = numTemps ; i< inputArray.length ; i++) {
                inputArray [i - numTemps] = inputArray[i];
            }

            // copy tempSpace to the right of input array
            int startAt = inputArray.length - numTemps ;
            for (int i = 0; i < numTemps ; i++) {
                inputArray [startAt + i] = tempSpace[i];
            }

            remaining -= numTemps;

        }


    }
}
