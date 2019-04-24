package avachat.leetcode.easy;

public class Prob58LengthOfLastWord {

    /*
    Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
     */




    /*
    ============ NOTE ==========
    Abhay : The problem statement above does NOT specify what to do if there is no space char
    Assuming : The length of the whole string is to be returned in such cases

    AND ..... it indeed threw an exception
    WELL ..... the problem statement can be interpreted as for 0 length string, the answer is 0
     */


    /*
    ----------------
    BAD BAD BAD BAD
    ----------------
    The problem was completely mis understood :-( :-(
    First code (see at bottom) assumed that whatever word is seen after the last space is last word !!
     */



    public int lengthOfLastWord(String str) {

        if ((null == str) || (str.length() == 0)) {
            return 0;
        }

        int lastWordLength = 0;
        boolean startNewWord = true;

        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                startNewWord = true;
            } else {
                if (startNewWord) {
                    lastWordLength = 1;
                    startNewWord = false;
                } else {
                    lastWordLength++;
                }
            }
        }

        return lastWordLength;
    }


    public int lengthOfLastWord_VERY_WRONG(String str) {

        if ( (null==str) || (str.length() == 0)) {
            return 0;
        }

        int lastSpaceAt = -1;

        for (int i =0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                lastSpaceAt = i;
            }
        }

        return (str.length() - (lastSpaceAt + 1) );
    }



}
