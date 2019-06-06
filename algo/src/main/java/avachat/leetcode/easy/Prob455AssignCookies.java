package avachat.leetcode.easy;

import java.util.Arrays;

public class Prob455AssignCookies {


    /*

    Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.

Note:
You may assume the greed factor is always positive.
You cannot assign more than one cookie to one child.

Example 1:
Input: [1,2,3], [1,1]

Output: 1

Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
Example 2:
Input: [1,2], [1,2,3]

Output: 2

Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
You have 3 cookies and their sizes are big enough to gratify all of the children,
You need to output 2.

     */


    public int findContentChildren(int[] needs, int[] cookies) {

        /*
        GOOD : Got the algorithm quickly, implemented it quickly, succeeded in 1 attempt and a FAST solution.
         */


        // Only whole cookie can be given.
        // Only need to maximize the number of children.
        // So the answer can be at max - number of cookies
        //
        // Use the biggest cookie to satisfy the mose needy child

        if ( (needs==null) || (cookies == null) || (needs.length == 0) || (cookies.length == 0) ) {
            return 0;
        }

        Arrays.sort(needs);
        Arrays.sort(cookies);

        int count = 0 ;

        int n = needs.length - 1;
        int c = cookies.length - 1;

        while ( (c >= 0) && (n >= 0) ) {

            if (cookies[c] >= needs[n]) {
                c--;
                n--;
                count++;
            } else {
                n--;
            }
        }

        return count;

    }



}
