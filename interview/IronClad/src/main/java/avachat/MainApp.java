package avachat;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {

    private static final char OPEN = '.';
    private static final char MISS = '?';
    private static final char HIT = 'x';
    private static final char SUNK = '*';

    private final char[][] inputGrid;
    private final int[][] finalScores;
    private final int[][] verticalScores;
    private final int[][] horizontalScores;
    private final int[][] feedScores;
    private final int[][] probabilityScores;
    private final int numRows;
    private final int numCols;
    private int maxScore;
    private int maxVerticalScore;
    private int maxHorizontalScore;
    private int maxProbabilityScore;

    private MainApp(char[][] inputGrid) {
        this.maxScore = Integer.MIN_VALUE;
        this.maxVerticalScore = Integer.MIN_VALUE;
        this.maxHorizontalScore = Integer.MIN_VALUE;
        this.maxProbabilityScore = Integer.MIN_VALUE;
        this.inputGrid = inputGrid;
        this.numRows = inputGrid.length;
        this.numCols = inputGrid[0].length;
        this.finalScores = new int[inputGrid.length][inputGrid[0].length];
        this.verticalScores = new int[inputGrid.length][inputGrid[0].length];
        this.horizontalScores = new int[inputGrid.length][inputGrid[0].length];
        this.feedScores = new int[inputGrid.length][inputGrid[0].length];
        this.probabilityScores = new int[inputGrid.length][inputGrid[0].length];
        init();
    }

    private void init() {

        for (int i = 0; i < inputGrid.length; i++) {
            Arrays.fill(finalScores[i], 0);
            Arrays.fill(verticalScores[i], 0);
            Arrays.fill(horizontalScores[i], 0);
        }
    }

    // process empty spaces
    private void doPass1() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (OPEN == inputGrid[i][j]) {
                    finalScores[i][j]++;
                    if (finalScores[i][j] > maxScore) {
                        maxScore = finalScores[i][j];
                    }
                }
            }
        }
    }

    // process neighbors of X
    private void doVerticalPass() {

        for (int j = 0; j < numCols; j++) {
            for (int i = 0; i < numRows; i++) {

                // has this cell already been processed
                if (verticalScores[i][j] > 0) {
                    continue;
                }

                if (inputGrid[i][j] != HIT) {
                    continue;
                }

                // find how many contiguous south neighbours this cell has
                int southernNeighbors = findSouthernNeighbors(i, j);

                int score = southernNeighbors + 1; // 1 for self
                // give extra score if this is a top row
                if (i == 0) {
                    score++;
                } else {
                    if ( (inputGrid[i-1][j] == MISS) || (inputGrid[i-1][j] == SUNK)) {
                        score++;
                    }
                }

                // set all southern neighbors to this score
                setVerticalScore(i, j, score);

                if (score > maxVerticalScore) {
                    maxVerticalScore = score;
                }
            }
        }
    }

    private void setVerticalScore (int startRow, int col, int score) {

        int row = startRow;
        while (row < numRows && inputGrid[row][col] == HIT) {
            verticalScores[row][col] = score;
            row++;
        }
    }

    private int findSouthernNeighbors(int startRow, int col) {

        //System.out.println("Starting findSouthernNeighbors at " + startRow + "," + col);
        // find count of southern neighbours that are marked X
        int row = startRow + 1;
        int count = 0;
        while ( (row < numRows) && inputGrid[row][col] == HIT) {
            count++;
            row++;
        }

        // have we reached the last row
        if (row == numRows) {
            count++ ; // giving extra weight to X in last row
        } else {
            if (inputGrid[row][col] == SUNK) {
                count++;
            }
        }

        //System.out.println("Found " + count);
        return count;
    }


    private void applyHorizontalScore() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (horizontalScores[i][j] != maxHorizontalScore) {
                    continue;
                }
                // apply the score the horizontal neighbour
                // to left
                if (j > 0) {
                    if (horizontalScores[i][j-1] == 0) {
                        // check if this is an open neighbor
                        if (inputGrid[i][j-1] == OPEN) {
                            finalScores[i][j-1] = horizontalScores[i][j];
                            if (finalScores[i][j-1] > maxScore) {
                                maxScore = finalScores[i][j-1];
                            }
                        }
                    }
                }
                // to right
                if ( j < numCols-1) {
                    if (horizontalScores[i][j+1] == 0) {
                        // check if this is an open neighbor
                        if (inputGrid[i][j+1] == OPEN) {
                            finalScores[i][j+1] = horizontalScores[i][j];
                            if (finalScores[i][j+1] > maxScore) {
                                maxScore = finalScores[i][j+1];
                            }
                        }
                    }
                }
            }
        }
    }




    // process neighbors of X
    private void doHorizontalPass() {

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {

                // has this cell already been processed
                if (horizontalScores[i][j] > 0) {
                    continue;
                }

                if (inputGrid[i][j] != HIT) {
                    continue;
                }

                // find how many contiguous south neighbours this cell has
                int westernNeighbours = findWesternNeighbors(i, j);

                int score = westernNeighbours + 1; // 1 for self
                // give extra score if this is a left col
                if (j == 0) {
                    score++;
                } else {
                    if ( (inputGrid[i][j-1] == MISS) || (inputGrid[i][j-1] == SUNK) ) {
                        score ++;
                    }
                }

                // set all southern neighbors to this score
                setHorizontalScore(i, j, score);

                if (score > maxHorizontalScore) {
                    maxHorizontalScore = score;
                }
            }
        }
    }

    private void setHorizontalScore (int row, int startCol, int score) {

        int col = startCol;
        while (col < numCols && inputGrid[row][col] == HIT) {
            horizontalScores[row][col] = score;
            col++;
        }
    }

    private int findWesternNeighbors(int row, int startCol) {

        //System.out.println("Starting findWesternNeighbors at " + row + "," + startCol);
        // find count of western neighbours that are marked X
        int col = startCol + 1;
        int count = 0;
        while ( (col < numCols) && inputGrid[row][col] == HIT) {
            count++;
            col++;
        }

        // have we reached the last row
        if (col == numCols) {
            count++ ; // giving extra weight to X in last row
        } else {
            if (inputGrid[row][col] == SUNK) {
                count++;
            }
        }

        //System.out.println("Found " + count);
        return count;
    }


    private void applyVerticalScores() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j< numCols; j++) {

                if (verticalScores[i][j] != maxVerticalScore) {
                    continue;
                }

                // apply the score to the top
                if ( i > 0 ) {
                    if (verticalScores [i-1][j] == 0) {
                        if (inputGrid[i-1][j] == OPEN) {
                            // add to horizontal score
                            finalScores[i-1][j] = verticalScores[i][j];
                            if (finalScores[i-1][j] > maxScore) {
                                maxScore = finalScores[i-1][j];
                            }
                        }
                    }
                }

                // apply to bottom
                if ( i < numRows-1 ) {
                    if (verticalScores [i+1][j] == 0) {
                        if (inputGrid[i+1][j] == OPEN) {
                            // add to horizontal score
                            finalScores[i+1][j] = verticalScores[i][j];
                            if (finalScores[i+1][j] > maxScore) {
                                maxScore = finalScores[i+1][j];
                            }
                        }
                    }
                }
            }
        }
    }


    /*
    private void findFeedScores() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {

                if (inputGrid[i][j] != HIT) {
                    continue;
                }

                int score = 1;
                if

            }
        }
    }
    */



    private void debug() {

        System.out.println("\nPrinting horizontal scores");
        System.out.println(maxHorizontalScore);
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(horizontalScores[i]));
        }


        System.out.println("\nPrinting horizontal scores");
        System.out.println(maxHorizontalScore);
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(horizontalScores[i]));
        }


        System.out.println("\nPrinting feed scores");
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(feedScores[i]));
        }


        System.out.println("\nPrinting vertical scores");
        System.out.println(maxProbabilityScore);
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(probabilityScores[i]));
        }


        System.out.println("\nPrinting final scores");
        System.out.println(maxScore);
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(finalScores[i]));
        }
    }

    private void printGuesses() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(finalScores[i][j]== maxScore ?'+':'-');
            }
            System.out.println();
        }
    }

    public static void main (String args[]) {

        System.out.println("Reading input");

        Scanner scanner = new Scanner(System.in);

        int cols = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());

        System.out.println("Will read " + rows + " rows " + cols + " cols ");

       char[][] inputGrid = new char[rows][cols];

       for (int i = 0; i < rows; i++) {
           String line = scanner.nextLine();
           //System.out.println("Read line " + line);
           for (int j = 0; j < cols; j++) {
               inputGrid[i][j] = line.charAt(j);
           }
       }

       System.out.println("Read the input grid as ");
       for (int i = 0; i < rows; i++) {
           System.out.println(Arrays.toString(inputGrid[i]));
       }

       MainApp solver = new MainApp(inputGrid);
       solver.doPass1();
       solver.doVerticalPass();
       solver.doHorizontalPass();
       solver.applyVerticalScores();
       solver.applyHorizontalScore();
       solver.debug();

       solver.printGuesses();
    }


}
