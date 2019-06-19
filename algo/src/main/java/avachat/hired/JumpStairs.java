package avachat.hired;

public class JumpStairs {

    public static long solution(long n) {
        // Type your solution here

        // you start at step 0
        // you have to go to step n
        // from the problem definition it seems like you cannot go beyond n
        // you can jump 1, 2 or 3 steps

        if (n <= 0) {
            return 0;
        }

        // from n-1 steps, there is only 1 way to reach n
        // if n is 1, there is only one way to go
        if (n == 1) {
            return 1;
        }
        long n1 = 1;


        // from n-2 steps there are 2 ways to reach n, (2 or 1+1)
        if (n == 2) {
            return 2;
        }
        long n2 = 2;

        // from n-3 steps there are 4 ways to reach (1+1+1, 1+2, 2+1, 3)
        if (n == 3) {
            return 4;
        }
        long n3 = 4;

        long prevCount = 6;
        long count = 7;
        long step = 4;
        while (step <= n) {
            long temp = count + prevCount;
            prevCount = count;
            count = temp;
            step++;
        }

        return count;

        /*
        // Now, for n > 3
        // we start at n-4 step (NOTE n-4 >= 0) because n > 3 at this point
        // from this step we can go n3 or n2 or n1
        // we go to n3 in 1 way by one jump - so we have one part of the answer (1 + n3)
        // we can go to n2 in 2 ways (1+1, 2) - so we have another part (2+n2)
        // we can go to n1 in 4 (1+1+1, 1+2, 2+1, 3) - so we have the last part (4+n1)

        long count = 0;
        long step = n - 4;
        while (step >= 0) {

            count = count + (n3 + 1) + (n2 + 2) + (n1 + 4);

            // now rotate all numbers
           n1 = n2;
           n2 = n3;
           n3 = count;

           step--;

        }

        return count;
        */

    }
}


