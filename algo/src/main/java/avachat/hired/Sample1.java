package avachat.hired;

public class Sample1 {

    public static long solution (long[] numbers) {

        if ( (null == numbers) || (numbers.length == 0)) {
            return 0;
        }

        long max = numbers[0];
        for (long i : numbers) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }
}
