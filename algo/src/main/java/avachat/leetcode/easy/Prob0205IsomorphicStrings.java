package avachat.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class Prob0205IsomorphicStrings {

    /*
    Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.


     */

    public boolean isIsomorphic(String s, String t) {

        /*

        BAD : Forgot that there need to be 2 maps
              Many copy paste errors
         */

        if ( null == s) {
            return (t == null);
        } else if (t == null) {
            return false;
        }

        if ( s.length() != t.length()) {
            return false;
        }

        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();
        Map<Character, Character> map1 = new HashMap<>(s.length());
        Map<Character, Character> map2 = new HashMap<>(s.length());

        for (int i = 0; i< charArray1.length; i++) {

            Character ch1 = charArray1[i];
            Character ch2 = charArray2[i];

            if ( map1.containsKey(ch1)) {
                if (! ch2.equals(map1.get(ch1))) {
                    return false;
                }
            } else {
                map1.put(ch1, ch2);
            }

            if ( map2.containsKey(ch2)) {
                if (! ch1.equals(map2.get(ch2))) {
                    return false;
                }
            } else {
                map2.put(ch2, ch1);
            }


        }

        return true;

    }

}
