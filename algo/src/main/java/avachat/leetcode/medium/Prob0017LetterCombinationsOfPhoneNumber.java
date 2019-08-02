package avachat.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prob0017LetterCombinationsOfPhoneNumber {

    /*

    Given a string containing digits from 2-9 inclusive,
    return all possible letter combinations that the number could represent.

A mapping of digit to charsForDigit (just like on the telephone buttons) is given below.
Note that 1 does not map to any charsForDigit.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

     */

    private char[][] charsForDigit;

    public List<String> letterCombinations(String digits) {


        /*

        GOOD : Better in space and time than 100% leetcode

        BAD : The algorithm itself was known before this impl

         */

        if ( (null == digits) || (digits.isEmpty())) {
            return Collections.emptyList();
        }

        charsForDigit = new char[10][4];
        charsForDigit[0] = new char[] {' ', ' ', ' ', ' '};
        charsForDigit[1] = new char[] {' ', ' ', ' ', ' '};
        charsForDigit[2] = new char[] {'a', 'b', 'c', ' '};
        charsForDigit[3] = new char[] {'d', 'e', 'f', ' '};
        charsForDigit[4] = new char[] {'g', 'h', 'i', ' '};
        charsForDigit[5] = new char[] {'j', 'k', 'l', ' '};
        charsForDigit[6] = new char[] {'m', 'n', 'o', ' '};
        charsForDigit[7] = new char[] {'p', 'q', 'r', 's'};
        charsForDigit[8] = new char[] {'t', 'u', 'v', ' '};
        charsForDigit[9] = new char[] {'w', 'x', 'y', 'z'};

        List<String> result = new ArrayList<>(digits.length()*digits.length()*digits.length()*digits.length());

        letterCombinationHelper(digits.toCharArray(), 0, new char[digits.length()], result);

        return result;

    }

    private void letterCombinationHelper(char[] digits, int index, char[] strSoFar, List<String> result) {

        // if we have reached end of digits print the string
        if (index >= digits.length) {
            result.add(new String(strSoFar));
            return;
        }

        // get letters for this digit
        char[] letters = charsForDigit[digits[index] - '0'];

        for (int i = 0; i < 4; i++) {

            // not all digits have 4 letters
            if (letters[i] == ' ') {
                break;
            }

            strSoFar[index] = letters[i];

            // go to next level
            letterCombinationHelper(digits, index+1, strSoFar, result);
        }

    }

}
