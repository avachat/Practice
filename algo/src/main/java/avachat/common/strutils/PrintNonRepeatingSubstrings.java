package avachat.common.strutils;

import java.util.HashSet;
import java.util.Set;

public class PrintNonRepeatingSubstrings {

    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.println("Need one argument");
        }
        printAll(args[0]);
    }

    public static void printAll (String s) {

        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            printValidSubstringStartingAt(str, i);
        }
    }

    private static void printValidSubstringStartingAt(char[] str, int startAt) {

        Set<Character> seenSoFar = new HashSet<>(str.length);
        StringBuilder buf = new StringBuilder(str.length);

        int endAt = startAt;
        char duplicate = '_';
        while (endAt < str.length) {
            char c = str[endAt];
            if (seenSoFar.contains(c)) {
                duplicate = c;
                break;
            }
            buf.append(c);
            seenSoFar.add(c);
            endAt++;
        }

        String substr = buf.toString();
        int len = endAt - startAt;

        System.out.println(len +"," + startAt + "," + endAt +"," + duplicate + "," + substr);
    }
}
