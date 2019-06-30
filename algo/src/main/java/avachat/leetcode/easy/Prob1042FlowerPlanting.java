package avachat.leetcode.easy;

import java.util.Arrays;

public class Prob1042FlowerPlanting {

    /*

    You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.



Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]


Note:

1 <= N <= 10000
0 <= paths.size <= 20000
No garden has 4 or more paths coming into or leaving it.
It is guaranteed an answer exists.

     */



    private static class Colors {
        boolean visited = false;
        int chosen = -1; // which color is chosen
        boolean[] available = new boolean[] {true, true, true, true};
    }

    public int[] gardenNoAdj(int N, int[][] paths) {

        // This is a map coloring problem
        // We can use a graph data structure
        // but simple array sorting is sufficient for this problem
        // because the gardens are already numbered
        // and we can start from any arbitrary garden, we can start with 1
        // since the paths are bi directional, we can convert each path to [lower, higher]
        // then sort the array based on first index
        // The iterating over the array is same as BFS

        if ( (paths == null) || (paths.length == 0) ) {
            int[] result = new int[N];
            for (int i = 0; i < N; i++) {
                result[i] = 1;
            }
            return result;
        }

        for (int[] path : paths) {
            // swap if needed
            if (path[0] > path[1]) {
                int tmp = path[0];
                path[0] = path[1];
                path[1] = tmp;
            }
        }

        // now sort the array
        Arrays.sort(paths, (i, j) -> i[0] - j[0]);

        // create an array of available colors
        // all colors are available in the beginning
        N = N + 1; // the in problem, description, garden numbers start from 1
        Colors[] gardenColors = new Colors[N];
        for (int i = 0; i < N; i++) {
            gardenColors[i] = new Colors();
        }

        // take the first path, and set color of the "from" garden to 1
        // paths is not empty here

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            if (!gardenColors[from].visited) {
                // first time visiting
                // that means, no lower numbered node has a path to this
                // The above statement is important
                // choose first available color
                // mark others as unavailable
                gardenColors[from].visited = true;
                gardenColors[from].chosen = 0;
                gardenColors[from].available = new boolean[] {true, false, false, false};
            } else {
                // this node was "to" of some path
                // so will have some colors unavailable

                // was a color already chosen for this?
                // if not nothing to do
                if (gardenColors[from].chosen == -1) {
                    // choose the first color that's available
                    for (int c = 0; c < 4; c++) {
                        if (gardenColors[from].available[c]) {
                            gardenColors[from].chosen = c;
                            break;
                        }
                    }
                }
            }

            // mark to as visited
            gardenColors[to].visited = true;
            // remove availability of the color 'chosen' for 'from'
            gardenColors[to].available[gardenColors[from].chosen] = false;
            // note that, 'to' is higher numbered than this node, so it's chosen would be -1
        }

        // now create result based on what color was chosen
        int[] result = new int[N-1];
        // the in problem, description, garden numbers start from 1
        for (int i = 1; i < N; i++) {
            // if nothing is chosen yet for this
            // select the next available color
            if (gardenColors[i].chosen >= 0) {
                result[i-1] = gardenColors[i].chosen + 1;
            } else {
                for (int c = 0; c < 4; c++) {
                    if (gardenColors[i].available[c]) {
                        result[i-1] = c + 1;
                        break;
                    }
                }
            }
        }

        return result;
    }

}
