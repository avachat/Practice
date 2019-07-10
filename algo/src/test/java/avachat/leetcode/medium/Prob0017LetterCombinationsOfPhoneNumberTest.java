package avachat.leetcode.medium;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Prob0017LetterCombinationsOfPhoneNumberTest {


    @Test
    public void printIt() {
        Prob0017LetterCombinationsOfPhoneNumber testObj = new Prob0017LetterCombinationsOfPhoneNumber();

        List<String> list = testObj.letterCombinations("23");
        System.out.println(list.size() + " = " + list.toString());

        list = testObj.letterCombinations("234");
        System.out.println(list.size() + " = " + list.toString());

        list = testObj.letterCombinations("27");
        System.out.println(list.size() + " = " + list.toString());

        list = testObj.letterCombinations("279");
        System.out.println(list.size() + " = " + list.toString());

    }

}