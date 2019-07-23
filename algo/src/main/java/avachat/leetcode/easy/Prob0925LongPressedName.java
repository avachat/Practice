package avachat.leetcode.easy;

public class Prob0925LongPressedName {

    /*

    Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.


Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.


     */


    public boolean isLongPressedName(String name, String typed) {

        if ( (null == name) || (name.isEmpty()) ) {
            return (null == typed) || (typed.isEmpty());
        }

        if ( (typed == null) || (typed.isEmpty()) ) {
            return false;
        }

        // now both name and typed are not empty

        if (typed.length() < name.length()) {
            return false;
        }

        // now typed has at least as much chars as name
        char[] nameStr = name.toCharArray();
        char[] typedStr = typed.toCharArray();

        // compare all chars except the final char
        int n = 0; // index for name
        int t = 0; // index of typed

        while ( (n < (nameStr.length -1)) && (t < typedStr.length)) {

            if ( nameStr[n] != typedStr[t]) {
                return false;
            }

            // now both chars are same

            // if this char in name is not repeated, we need to consume all repeating chars in typed
            if (nameStr[n] != nameStr[n+1]) {
                while ( (t < typedStr.length) && (typedStr[t] == nameStr[n]) ) {
                    t++;
                }
            } else {
                t++; // we can only consume one char in typed
            }
            n++;
        }

        // at this point the last char in name is still left to compare
        // typed must have chars remaining, and all these chars must be same
        if (t == typedStr.length) {
            return false;
        }

        while ( (t < typedStr.length) && (typedStr[t] == nameStr[n]) ) {
            t++;
        }

        // we must have reached the end of typed
        return (t == typedStr.length);

    }


}
