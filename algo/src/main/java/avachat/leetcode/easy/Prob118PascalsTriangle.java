package avachat.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Prob118PascalsTriangle {

    /*
    Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
     */


    public List<List<Integer>> generate(int numRows) {

        /*
           GOOD : Decided to use arrays just to make the loop implementation a bit harder

           BAD : Took a long time to figure our array to list conversions
         */

        if ( numRows <= 0 ) {
            return new ArrayList<>(0);
        }

        int[][] pascal = new int[numRows][];
        pascal[0] = new int[]{1};

        if ( numRows == 1 ) {
            return convert2dArray(pascal);
        }

        pascal[1] = new int[]{1,1};

        for (int i = 2; i < numRows; i++) {
            int[] prevRow = pascal[i-1];
            int[] row = new int[prevRow.length + 1];
            row[0] = 1;
            for (int j = 0; j < prevRow.length - 1; j++) {
                row[j+1] = prevRow[j] + prevRow[j+1];
            }
            row[row.length - 1] = 1;
            pascal[i] = row;
        }

        return convert2dArray(pascal);
    }

    private List<List<Integer>> convert2dArray(int[][] pascal) {

        return Arrays.stream(pascal).map(na -> Arrays.stream(na).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }


}
