package avachat.leetcode.easy;


/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

 */



/*
Need to write this without stack
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prob20ValidParentheses {

    private final Set<Character> OPEN_PAREN = Stream.of('(', '[', '{').collect(Collectors.toSet());
    private final Set<Character> CLOSE_PAREN = Stream.of(')', ']', '}').collect(Collectors.toSet());
    private final Map<Character, Character> MATCH_PAREN =  new HashMap<Character, Character>() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
    }};

    public boolean isValid(String str) {

        Deque<Character> stack = new ArrayDeque<>(str.length());

        for (int i = 0 ; i < str.length(); i++) {
            char nextChar = str.charAt(i);
            if ( OPEN_PAREN.contains(nextChar)) {
                stack.addFirst(nextChar);
                continue;
            }
            if ( CLOSE_PAREN.contains(nextChar)) {
                if ( stack.isEmpty() ) {
                    return false;
                }
                char prevChar = stack.removeFirst();
                if ( ! MATCH_PAREN.get(prevChar).equals(nextChar) ) {
                    return false;
                }
                continue;
            }
            return false; // invalid char
        }

        return stack.isEmpty();
    }
}
