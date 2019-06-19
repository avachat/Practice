package avachat.hired;

import java.util.HashMap;
import java.util.Map;

public class Assessment1 {

    // Return the index of the first char that is not repeated
    public static long solution(String s) {
        // Type your solution here

        if ( (null == s) || (s.isEmpty())) {
            return -1;
        }

        Map<Character, Integer> counts = new HashMap<>(s.length());

        char[] str = s.toCharArray();

        for (char c : str) {
            int prevCount = counts.getOrDefault(c, 0);
            counts.put(c, prevCount+1);
        }

        for (int i = 0; i < str.length; i++) {
            if (counts.get(str[i]) == 1) {
                return i;
            }
        }

        return -1;
    }


}
