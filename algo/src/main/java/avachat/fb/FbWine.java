package avachat.fb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FbWine {

    static List<Long> InputNumbersList;

    static {
        InputNumbersList = null;
    } //


    public static void printInputNumbersList() {
        Iterator<Long> iter = InputNumbersList.iterator();
        while (iter.hasNext()) {
            Long l = iter.next();
            System.out.print(l);
            System.out.print(" ");
        } //

        System.out.println("");

    } //


    public static void createInputNumberList(String[] str_array) {
        InputNumbersList = null;
        InputNumbersList = new ArrayList<Long>(str_array.length);
        for (int i = 0; i < str_array.length; i++) {
            Long l = Long.parseLong(str_array[i]);
            InputNumbersList.add(new Long(l));
        } //
        //printInputNumbersList () ;

    } //


    public static long getFactorial(long n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        long answer = 1;
        for (long i = 2; i <= n; i++) {
            answer *= i;
        } //

        //System.out.println ( "\treturning " + n + "! = " + answer) ;
        return answer;
    } //


    public static long getNumDerange(long n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 0;
        }

        long dn1 = 0;
        long dn2 = 1;

        //if (n == 2) return 1 ;

        long answer = 1;
        for (long i = 2; i <= n; i++) {
            answer = (i - 1) * (dn1 + dn2);
            dn2 = dn1;
            dn1 = answer;
        } //

        System.out.println("\treturning !" + n + " = " + answer);
        return answer;
    } //


    public static long getNumCombinations(long n, long k) {

        if (n < k) {
            System.out.println("Error n = " + n + " < k = " + k);
            System.exit(1);
        } //

        long denom = n;
        for (long i = 1; i < k; i++) {
            denom *= (n - i);
        } //

        long answer = denom / getFactorial(k);

        System.out.println("\tcombinations for <" + n + " ," + k + "> = " + answer);
        return answer;
    } //


    public static long solveForPair(long num_glasses, long num_correct) {

        long comb = getNumCombinations(num_glasses, num_correct);
        long derange = getNumDerange(num_glasses - num_correct);
        long answer = comb * derange;
        System.out.println("    comb * derange for " + num_glasses + " " + num_correct + " = " + answer);
        return answer;
    } //


    public static void solveForInputNumbersList() {
        long num_glasses = InputNumbersList.get(0);
        long num_correct = InputNumbersList.get(1);

        long answer = 0;

        for (long i = num_correct; i <= num_glasses; i++) {
            answer += solveForPair(num_glasses, i);
        } //

        System.out.println(num_glasses + " " + num_correct + " = " + answer);
    } //


    public static void main(String argv[]) throws IOException {

        if (argv.length != 1) {
            System.out.println("Usage :: java test.hack.FbWine <file_name>");
            System.exit(1);
        } //

        String file_name = argv[0];
        //System.out.println ("Opening file " + file_name) ;

        BufferedReader input_file = new BufferedReader(new FileReader(file_name));

        String first_line = input_file.readLine();
        int num_lines = Integer.parseInt(first_line.trim());

        InputNumbersList = new ArrayList<Long>(num_lines);

        for (int ll = 0; ll < num_lines; ll++) {

            String line = input_file.readLine().trim();
            String[] str_array = line.split(" ");
            createInputNumberList(str_array);
            solveForInputNumbersList();
        }

        input_file.close();


    } //


}
