package avachat.hired;

public class LongestCommonPrefix {

    public static String solution(String[] strings) {
        // Type your solution here

        // eliminate the possibility of null and empty
        if ( (null == strings) || (strings.length == 0)) {
            return "";
        }

        // at least we have one string
        for (String str : strings) {
            if ( (null == str) || (str.isEmpty())) {
                return "";
            }
        }

        // another boundary condition check to make the rest of the code readable
        if (strings.length == 1) {
            return strings[0];
        }

        // now we have at least two strings and
        // every string has at least one character
        // start with 1st string
        // and see how many of it's initial characters are in all strings
        String first = strings[0];
        int prefixEnd = 0;
        boolean matched = true;
        while (matched && (prefixEnd < first.toCharArray().length)) {

            // let's check if this character is in every other string
            for (int i = 1; matched && (i < strings.length); i++) {

                // get the string
                String str = strings[i];

                // does this string have enough chars
                // does this string have the same chat at current
                matched = (str.length() > prefixEnd)
                        && (str.charAt(prefixEnd) == first.charAt(prefixEnd));
            }

            if (matched) {
                prefixEnd++;
            }

        }

        return first.substring(0, prefixEnd);

    }


}
