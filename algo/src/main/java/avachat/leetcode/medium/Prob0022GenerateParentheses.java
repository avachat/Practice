package avachat.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prob0022GenerateParentheses {

    /*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

     */

    /*

    GOOD : Got it running in first attempt, without test cases, and better space/time than almost 100%

     */

    public List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        generate(n, 0, 0, "", result);

        return result;
    }

    private void generate(int max, int open, int closed, String parenthesesStr, List<String> result) {

        if (closed == max) {
            result.add(parenthesesStr);
            return;
        }

        // first add a left parentheses and move forward
        if (open < max) {
            generate(max, open + 1, closed, parenthesesStr + "(", result);
        }

        // see if an open left can be closed and move forward
        if (closed < open) {
            generate(max, open, closed+1, parenthesesStr + ")", result);
        }

    }
}
