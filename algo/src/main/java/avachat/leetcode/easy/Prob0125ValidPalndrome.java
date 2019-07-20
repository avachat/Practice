package avachat.leetcode.easy;

public class Prob125ValidPalndrome {

    /*

    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false


     */


    public boolean isPalindrome(String s) {

        /*
        GOOD : Got in one try
         */

        if ( (null == s) || (s.isEmpty())) {
            return true;
        }

        char[] chars = s.toCharArray();

        int i = 0;
        int j = chars.length - 1;

        while (i < j) {

            if ( !isAlphaNumeric(chars[i])) {
                i++;
                continue;
            }

            if ( !isAlphaNumeric(chars[j])) {
                j--;
                continue;
            }

            if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                return false;
            }

            i++;
            j--;
        }

        return true;


    }

    private boolean isAlphaNumeric(char c) {
        return (Character.isLetter(c) || Character.isDigit(c));
    }


}
