package avachat.leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Prob1091ShortestPathBinaryMatrix {

    /*

    In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.



Example 1:

Input: [[0,1],[1,0]]
Output: 2
Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4


Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1

     */

    /*

    Strategy :

    Start BFS from 0,0
    Maintain a queue
    Mark 0,0 with 1 : Add to the queue
    While queue is not empty
       Take item from queue
       Get it's eligible neighbors
            If the neighbor is destination : we are done at length+1
        If the neighbor is eligible : set it's value to length+1, add to the queue

     */


    private class Coordinates {
        int row;
        int col;
        Coordinates(int r, int c) {
            this.row = r;
            this.col = c;
        }

        Coordinates east() {
            return ( (col > 0) && (matrix[row][col-1] == 0)) ? new Coordinates(row, col-1) : null;
        }

        Coordinates northEast() {
            return ( (row > 0) && (col > 0) && (matrix[row-1][col-1] == 0))
                    ? new Coordinates(row-1, col-1) : null;
        }

        Coordinates north() {
            return ( (row > 0) && (matrix[row-1][col] == 0)) ? new Coordinates(row-1, col) : null;
        }

        Coordinates northWest() {
            return ( (row > 0) && (col < numCols-1) && (matrix[row-1][col+1] == 0))
                    ? new Coordinates(row-1, col+1) : null;
        }

        Coordinates west() {
            return ( (col < numCols-1) && (matrix[row][col+1] == 0)) ? new Coordinates(row, col+1) : null;
        }

        Coordinates southWest() {
            return ( (row < numRows-1) && (col < numCols-1) && (matrix[row+1][col+1] == 0))
                    ? new Coordinates(row+1, col+1) : null;
        }

        Coordinates south() {
            return ( (row < numRows-1) && (matrix[row+1][col] == 0)) ? new Coordinates(row+1, col) : null;
        }

        Coordinates southEast() {
            return ( (row < numRows-1) && (col > 0) && (matrix[row+1][col-1] == 0))
                    ? new Coordinates(row+1, col-1) : null;
        }
    }


    private int[][] matrix = null;
    private Deque<Coordinates> bfsQueue = new LinkedList<>();
    private int numRows = -1;
    private int numCols = -1;

    public int shortestPathBinaryMatrix(int[][] grid) {

        if ( (null == grid) || (grid.length == 0)) {
            return -1;
        }

        numRows = grid.length;
        numCols = grid[0].length;

        if (numCols == 0) {
            return -1;
        }

        if ( (numRows == 1) && (numCols == 1)) {
            // 1x1 matrix
            return (grid[0][0] == 0) ? 1 : -1;
        }

        // if the start or end are blocked, return -1
        if ( (grid[0][0] == 1) || (grid[numRows-1][numCols-1] == 1)) {
            return -1;
        }

        // now edge conditions have been taken care of
        // grid has more than 1 cell
        this.matrix = grid;

        // note that we are counting number of cells in the path : min is 1
        matrix[0][0] = 1; // mark the cell as visited with count of steps

        // start a queue

        // add the start to the queue
        bfsQueue.offerLast(new Coordinates(0,0));

        while (! bfsQueue.isEmpty()) {

            Coordinates cell = bfsQueue.pollFirst();
            int steps = matrix[cell.row][cell.col]; // note that we are counting number of cells in the path : min is 1

            List<Coordinates> neighbors = gatherEligibleNeighbors(cell);

            for (Coordinates neighbor : neighbors) {

                if ((neighbor.row == (numRows - 1)) && (neighbor.col == (numCols - 1))) {
                    return steps + 1;
                }

                matrix[neighbor.row][neighbor.col] = steps + 1;
                bfsQueue.offerLast(neighbor);
            }

        }

        // dest not found and no more cells on the queue
        return -1;
    }

    private List<Coordinates> gatherEligibleNeighbors (Coordinates cell) {

        List<Coordinates> neighbors = new ArrayList<>(8);

        Coordinates east = cell.east();
        if (east != null) {
            neighbors.add(east);
        }

        Coordinates northEast = cell.northEast();
        if (northEast != null) {
            neighbors.add(northEast);
        }

        Coordinates north = cell.north();
        if (north != null) {
            neighbors.add(north);
        }

        Coordinates northWest = cell.northWest();
        if (northWest != null) {
            neighbors.add(northWest);
        }

        Coordinates west = cell.west();
        if (west != null) {
            neighbors.add(west);
        }

        Coordinates southWest = cell.southWest();
        if (southWest != null) {
            neighbors.add(southWest);
        }

        Coordinates south = cell.south();
        if (south != null) {
            neighbors.add(south);
        }

        Coordinates southEast = cell.southEast();
        if (southEast != null) {
            neighbors.add(southEast);
        }

        return neighbors;
    }

}
