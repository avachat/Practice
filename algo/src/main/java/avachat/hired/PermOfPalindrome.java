package avachat.hired;

import java.util.HashMap;
import java.util.Map;

public class PermOfPalindrome {

    public static boolean solution(String s) {
        // Type your solution here

        if ( (null == s) || (s.isEmpty())) {
            return true;
        }

        // permutation of palindrome can contain
        // at most one character that occurs odd number of time

        // count all chars
        Map<Character, Integer> counts = new HashMap<>(s.length());
        for (char c : s.toCharArray()) {
            counts.put (c, counts.getOrDefault(c, 0) + 1);
        }

        // check of the counts contain more than one odd counts
        boolean oddFound = false;
        for (int n : counts.values()) {
            if ( (n % 2) == 0) {
                continue;
            }
            if (oddFound) {
                return false;
            }
            oddFound = true;
        }
        return true;
    }
}
