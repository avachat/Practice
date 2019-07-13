package avachat.leetcode.medium;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class Prob0046PermutationsTest {

    @Test
    public void testIt() {

        Prob0046Permutations testObj = new Prob0046Permutations();

        List<List<Integer>> result = null;

        result = testObj.permute(new int[] {});

        result = testObj.permute(new int[] {1});
        System.out.println(result.size() + " = " + String.valueOf(result));

        result = testObj.permute(new int[] {1, 2});
        System.out.println(result.size() + " = " + String.valueOf(result));

        result = testObj.permute(new int[] {1, 2, 3});
        System.out.println(result.size() + " = " + String.valueOf(result));

        result = testObj.permute(new int[] {1, 2, 3, 4});
        System.out.println(result.size() + " = " + String.valueOf(result));

        result = testObj.permute(new int[] {1, 2, 3, 4, 5});
        System.out.println(result.size() + " = " + String.valueOf(result));


    }

}