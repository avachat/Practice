package avachat.leetcode.easy;

import java.util.Arrays;

public class Prob475Heaters {


    /*
    Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:

Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.


Example 1:

Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.


Example 2:

Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

     */


    private int minRadius = Integer.MIN_VALUE;
    private int heaterIndex = 0;

    public int findRadius(int[] houses, int[] heaters) {


        /*

        GOOD : Got the algorithm very quickly
            Faster than 100% java programs on leetcode

        BAD : Took a LONG time to write the simplified code
            Took very long to debug - the initialization was WRONG - see below

         */

        Arrays.sort(houses);
        Arrays.sort(heaters);

        // find the best heater for house 1 : the nearest
        // start with the first heater
        //minRadius = Math.abs(houses[0] - heaters[0]); // -------!!!!!!!!!!! THIS WAS NOT NEEDED

        for (int houseIndex = 0;  houseIndex < houses.length; houseIndex++) {
            findClosestHeater(heaters, houses, houseIndex);
        }

        return minRadius;
    }

    private void findClosestHeater (int[] heaters, int houses[], int houseIndex) {

        int housePosition = houses[houseIndex];

        int candidateHeater = heaterIndex;
        int minDistance = Math.abs(housePosition - heaters[candidateHeater]);

        while ( (candidateHeater < heaters.length) && (heaters[candidateHeater] < housePosition)) {
            // new min radius found for houses[0]
            minDistance = Math.abs(housePosition - heaters[candidateHeater]);
            heaterIndex = candidateHeater; // the last best heater index
            candidateHeater++;
        }

        // Now it's possible that the current position might be better
        if ( candidateHeater < heaters.length) {
            int distance = Math.abs(housePosition - heaters[candidateHeater]);
            if (minDistance >= distance) {
                minDistance = distance;
                heaterIndex = candidateHeater; // update the last best heater index
            }
        }

        if (minRadius < minDistance) {
            // prev min radius is not enough
            minRadius = minDistance;
        }

    }


}
