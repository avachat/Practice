package avachat.leetcode.easy;


/*

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

 */

public class Prob0014LongestCommonPrefix {


    /*
    NOTE : The extra stringbuilder wasn't necessary. A substring would be better.
     */

    public String longestCommonPrefix(String[] allStrings) {

        if ( allStrings.length == 0 ) {
            return "";
        }

        String baseStr = allStrings[0];
        if ( (null == baseStr) || baseStr.isEmpty()) {
            return "";
        }

        StringBuilder prefix = new StringBuilder("");

        for ( int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);
            if ( ! matchCharAtPosition(allStrings, baseStr.charAt(i), i)) {
                break;
            }
            prefix.append(c);
        }

        return prefix.toString();
    }

    private boolean matchCharAtPosition (String[] allStrings, char c, int position) {

        for (int s = 1; s < allStrings.length ; s++) {
            if (allStrings[s] == null) {
                return false;
            }
            if (allStrings[s].length() <= position) {
                return false;
            }
            if ( c != allStrings[s].charAt(position)) {
                return false;
            }
        }

        return true;
    }
}
