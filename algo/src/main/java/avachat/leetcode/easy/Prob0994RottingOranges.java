package avachat.leetcode.easy;

public class Prob0994RottingOranges {

    /*

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.



Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.

     */



    public int orangesRotting (int[][] grid) {

        /*

        GOOD : Faster than 100%, else not much. Not sure why it takes more memory

        BAD : Completely missed how and where to start the BFS :-( :-( :-(
            That's really BAD (See the wrong version below)

            Also made many silly mistakes
         */

        // Start a BFS queue
        int n2 = grid.length * grid[0].length;
        int[] rowQueue = new int[n2];
        int[] colQueue = new int[n2];
        int queueLength = 0;


        // add all the rotten oranges to the queue
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    rowQueue[queueLength] = i;
                    colQueue[queueLength] = j;
                    queueLength++;
                }
            }
        }

        // now keep processing items from the queue
        int maxLevel = 0;
        int queuePosition = 0;
        while (queuePosition < queueLength) {

            int r = rowQueue[queuePosition];
            int c = colQueue[queuePosition];
            queuePosition++;

            int currLevel = grid[r][c];
            maxLevel = Math.max(maxLevel, currLevel);

            // see if any of the neighbors need to be added to the queue
            // top
            if ( ((r-1) >= 0) && (grid[r-1][c] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r-1][c] = currLevel + 1;
                rowQueue[queueLength] = r-1;
                colQueue[queueLength] = c;
                queueLength++;
            }
            // bottom
            if ( ((r+1) < grid.length) && (grid[r+1][c] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r+1][c] = currLevel + 1;
                rowQueue[queueLength] = r+1;
                colQueue[queueLength] = c;
                queueLength++;
            }
            // left
            if ( ((c-1) >= 0) && (grid[r][c-1] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r][c-1] = currLevel + 1;
                rowQueue[queueLength] = r;
                colQueue[queueLength] = c-1;
                queueLength++;
            }
            // right
            if ( ((c+1) < grid[0].length) && (grid[r][c+1] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r][c+1] = currLevel + 1;
                rowQueue[queueLength] = r;
                colQueue[queueLength] = c+1;
                queueLength++;
            }

        }

        // is there still any fresh orange left
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        if (maxLevel == 0) {
            return 0;
        }

        return maxLevel - 2;

    }


    /*
    public int orangesRottingWrong(int[][] grid) {

        // You cannot do BFS from just one node :-( :-(
        // YOu cannon start from a rotten orange and keep spreading the rot in a BFS :-( :-(

        // do a breadth first search from a rotten orange
        // 1. You cannot do DFS
        // 2. You cannot start from fresh orange

        int maxMinutes = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    // this is an unexamined rotten orange
                    // start a BFS from here
                    maxMinutes = Math.max(maxMinutes, bfsLevel(grid, i, j));
                }
            }
        }

            // is there still any fresh orange left
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }


        return maxMinutes;

    }

    private int bfsLevel(int[][] grid, int i, int j) {

        // grid[i][j] is a rotten orange that has not been examined yet
        // start a BFS queue
        int n2 = grid.length * grid[0].length;
        int[] rowQueue = new int[n2];
        int[] colQueue = new int[n2];

        // put this location in the queue
        rowQueue[0] = i;
        colQueue[0] = j;
        int queuePosition = 0;
        int queueLength = 1;

        int maxLevel = 2; // the number that indicates rotten orange at minute 0
        while (queuePosition < queueLength) {

            int r = rowQueue[queuePosition];
            int c = colQueue[queuePosition];
            queuePosition++;

            int currLevel = grid[r][c];
            maxLevel = Math.max(maxLevel, currLevel);

            // see if any of the neighbors need to be added to the queue
            // top
            if ( ((r-1) >= 0) && (grid[r-1][c] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r-1][c] = currLevel + 1;
                rowQueue[queueLength] = r-1;
                colQueue[queueLength] = c;
                queueLength++;
            }
            // bottom
            if ( ((r+1) < grid.length) && (grid[r+1][c] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r+1][c] = currLevel + 1;
                rowQueue[queueLength] = r+1;
                colQueue[queueLength] = c;
                queueLength++;
            }
            // left
            if ( ((c-1) >= 0) && (grid[r][c-1] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r][c-1] = currLevel + 1;
                rowQueue[queueLength] = r;
                colQueue[queueLength] = c-1;
                queueLength++;
            }
            // right
            if ( ((c+1) < grid[0].length) && (grid[r][c+1] == 1)) {
                // found a neighboring clean orange
                // set it's level and put it on the queue
                grid[r][c+1] = currLevel + 1;
                rowQueue[queueLength] = r;
                colQueue[queueLength] = c+1;
                queueLength++;
            }

        }

        return maxLevel - 2; // steps needed, as we start from 3 as the first step

    }
    */





}
