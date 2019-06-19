package avachat.hired;

import java.util.HashMap;
import java.util.Map;

public class Assessment2 {

    // find the length of the substring with no repeated chars

    public static long solution(String s) {
        // Type your solution here

        if ( (null == s) || (s.isEmpty())) {
            return 0;
        }


        // Keep a map of the last position seen for the char
        Map<Character, Integer> lastPosition = new HashMap<>(s.length());

        int maxSubstrLen = 0 ; // max substring length seen so far
        int currentSubstrLen = 0; // length of the current substring under consideration
        int startsAt = 0 ; // current substring starts at

        for (int i = 0; i < s.toCharArray().length; i++) {

            // if this is the first time we have seen this char
            // then record its position
            // It also means, current substring can be extended
            char c = s.charAt(i);
            if (! lastPosition.containsKey(c)) {
                lastPosition.put(c, i);
                currentSubstrLen ++; // we are still in a substring without repetition
                if (currentSubstrLen > maxSubstrLen) {
                    maxSubstrLen = currentSubstrLen;
                }
                continue;
            }

            // if this char was already seen
            // then was it seen before the current substring started?
            // if yes then we are still in a valid substring
            // if not, we need to start a new substring
            if ( lastPosition.get(c) < startsAt) {
                currentSubstrLen ++;
                if (currentSubstrLen > maxSubstrLen) {
                    maxSubstrLen = currentSubstrLen;
                }
            } else {
                currentSubstrLen = 1;
                startsAt = i;
            }

            // we need to update the last position
            lastPosition.put(c, i);
        }

        return maxSubstrLen;
    }


}
