package avachat.leetcode.easy;

public class Prob1021RemoveOutermostParentheses {

    /*

    A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.



Example 1:

Input: "(()())(())"
Output: "()()()"
Explanation:
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation:
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: "()()"
Output: ""
Explanation:
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".


Note:

S.length <= 10000
S[i] is "(" or ")"
S is a valid parentheses string


     */


    public String removeOuterParentheses(String S) {

        /*
        GOOD : Quick impl, ran the first time, better space time than 100%

        BAD : Nothing :-)

         */

        if ( (null == S) || (S.isEmpty())) {
            return "";
        }

        char[] str = S.toCharArray();
        char[] ans = new char[str.length];
        int len = 0; // answer length

        int nesting = 0;
        for (char c : str) {
            if ( c == '(') {
                if (nesting > 0) {
                    ans[len++] = c;
                }
                nesting++;
            } else {
                nesting--;
                if (nesting > 0) {
                    ans[len++] = c;
                }
            }
        }

        return new String(ans, 0, len);

    }


}
