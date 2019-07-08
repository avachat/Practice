package avachat.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Prob1030MatrixCellsInDistanceOrder {

    /*

    We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)



Example 1:

Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (r0, c0) to other cells are: [0,1]
Example 2:

Input: R = 2, C = 2, r0 = 0, c0 = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
Example 3:

Input: R = 2, C = 3, r0 = 1, c0 = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].


Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C

     */


    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        int result[][] = new int [R*C][2];
        int index = 0;

        // init a matrix to represent, visited state
        boolean[][] visited = new boolean[R][C];

        // create a BFS Queue for coordinates
        Deque<int[]> bfsQueue = new ArrayDeque<>(R*C);

        // add the starting point to the queue
        visited[r0][c0] = true;
        bfsQueue.offerLast(new int[]{r0, c0});

        while (!bfsQueue.isEmpty()) {

            // take out the first element
            // add to the result
            // then add all 4 neighbours to the queue, if not already visited
            int[] coordinates = bfsQueue.pollFirst();
            result[index++] = coordinates;

            // see if neighbors need to be added
            int row = coordinates[0];
            int col = coordinates[1];
            int northRow = row - 1;
            int southRow = row + 1;
            int eastCol = col - 1;
            int westCol = col + 1;
            // north
            if ( (northRow >= 0) && ( ! visited[northRow][col]) ) {
                visited[northRow][col] = true;
                bfsQueue.offerLast(new int[]{northRow, col});
            }
            // south
            if ( (southRow < R) && (! visited[southRow][col])) {
                visited[southRow][col] = true;
                bfsQueue.offerLast(new int[] {southRow, col});
            }
            // east
            if ( (eastCol >= 0) && (! visited[row][eastCol])) {
                visited[row][eastCol] = true;
                bfsQueue.offerLast(new int[] {row, eastCol});
            }
            // west
            if ( (westCol < C) && (! visited[row][westCol])) {
                visited[row][westCol] = true;
                bfsQueue.offerLast(new int[]{row, westCol});
            }
        }

        return result;
    }
}
