package avachat.leetcode.easy;

public class Prob883ProjectionArea {


    /*

    On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.

Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).

Now we view the projection of these cubes onto the xy, yz, and zx planes.

A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.

Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.

Return the total area of all three projections.



Example 1:

Input: [[2]]
Output: 5
Example 2:

Input: [[1,2],[3,4]]
Output: 17
Explanation:
Here are the three projections ("shadows") of the shape made with each axis-aligned plane.

Example 3:

Input: [[1,0],[0,2]]
Output: 8
Example 4:

Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: 14
Example 5:

Input: [[2,2,2],[2,1,2],[2,2,2]]
Output: 21


Note:

1 <= grid.length = grid[0].length <= 50
0 <= grid[i][j] <= 50



     */


    /*
    NOTE :

    In spite of the complicated description, and confusing diagram at leetcode, this is what it means.

    Each tower is on a 1x1 space. The towers can be of different heights, but the length and width of each tower is 1.
    That means, looking from top, every grid square that has a tower looks same.
        Each tower would look like a square of 1x1 looking from top.
    Then on the other axis, doesn't matter x or y, the tallest tower over-shadows all other towers.
        So height of that tallest tower would be the height of the shadow.
        And the area, since width/length is only 1

    So find how many grids have towers = that's area looking from top : z area
    Find the max value in every row = add them up : that's the shadow height on Y axis
    Find the max value in every row = add them up : that's the shadow height on X axis

    Sum it up.
     */


    public int projectionArea(int[][] grid) {

        /*


        GOOD : Got it quickly, and at the first run, and faster than 100%  !!! :-) :-)


         */

        // grid is neither null nor empty

        int filled = 0;
        int xTotalShadow = 0;
        int yTotalShadow = 0;

        int rows = grid.length; // grid has at least 1 row
        //int cols = grid[0].length; // it's an NxN grid
        int cols = rows; // it's an NxN grid

        // first find the max in each row
        // also count how many are filled
        for (int i = 0; i < rows; i++) {
            int max = 0;
            for (int j = 0; j < cols; j++) {
                int val = grid[i][j];
                if (val > 0) {
                    filled++;
                }
                if (val > max) {
                    max = grid[i][j];
                }
            }
            yTotalShadow += max;
        }

        // don't count filled this time
        for (int j = 0; j < cols; j++) {
            int max = 0;
            for (int i = 0; i < rows; i++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            xTotalShadow += max;
        }

        return filled + xTotalShadow + yTotalShadow;

    }



}
