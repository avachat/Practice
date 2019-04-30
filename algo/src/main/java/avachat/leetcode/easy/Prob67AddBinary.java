package avachat.leetcode.easy;

public class Prob67AddBinary {


    /*
    Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
     */

    /*
    NOTE : Assumption - the string does NOT contain leading zeroes
     */


    public String addBinary(String a, String b) {

        // handle edge case
        if ("0".equals(a)) {
            return b;
        }
        if ( "0".equals(b)) {
            return a;
        }

        int len = Math.max(a.length(), b.length()) + 1;

        char[] result = new char[len];

        int ai = a.length() -1;
        int bi = b.length() -1;
        int ri = len - 1;
        int carryover = 0;

        while ( (ai >= 0) || (bi >=0)) {

            char ca =  (ai >= 0) ? a.charAt(ai) : '0';
            char cb =  (bi >= 0) ? b.charAt(bi) : '0';

            int an = (ca == '0') ? 0 : 1;
            int bn = (cb == '0') ? 0 : 1;

            int rn = an + bn + carryover;
            switch (rn) {
                case 0 : result[ri] = '0'; carryover = 0; break;
                case 1 : result[ri] = '1'; carryover = 0; break;
                case 2 : result[ri] = '0'; carryover = 1; break;
                case 3 : result[ri] = '1'; carryover = 1; break;
            }

            ai--;
            bi--;
            ri--;

        }

        if ( carryover == 1) {
            result[0] = '1';
            return new String(result);
        } else {
            return new String(result, 1, result.length-1);
        }


    }




}
