package avachat.learn.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Factorial {

    static List<Long> InputNumbersList;
    static char[] Alphabet_AZ;
    static char[] Alphabet_az;
    static long[] AllFactorialNumbers;
    static long[] AllDerangementNumbers;
    static long[][] AllPermutationNumbers;
    static long[][] AllCombinationNumbers;

    static boolean[] FlagReserved;
    static int CurrentUnreservedLetter;
    static int NumLettersToUse;

    static char[][] AllArrangements_1;

    static {
        InputNumbersList = null;

        Alphabet_AZ = new char[26];
        Alphabet_AZ[0] = 'A';
        for (int i = 1; i < 26; i++) {
            Alphabet_AZ[i] = (char) (i + (int) Alphabet_AZ[0]);
        } //

        Alphabet_az = new char[26];
        Alphabet_az[0] = 'a';
        for (int i = 1; i < 26; i++) {
            Alphabet_az[i] = (char) (i + (int) Alphabet_az[0]);
        } //
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


    public static long getFactorial(int n) {
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


    public static long getNumDerange(int n) {
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

        //System.out.println ( "\treturning !" + n + " = " + answer) ;
        return answer;
    } //


    public static long getNumPermutations(int n, int k) {

    /* ASSUMPTIONS : 1.n > k   2.n > 0  3. k >= 0 */

        if (n < k) {
            System.out.println("Error n = " + n + " < k = " + k);
            System.exit(1);
        } //

        long answer = 1;
        for (long i = 0; i < k; i++) {
            answer *= (n - i);
        } //

        //System.out.println ("\tpermutations for <" + n + " ," + k + "> = " + answer) ;
        return answer;
    } //


    public static long getNumCombinations(int n, int k) {

        if (n < k) {
            System.out.println("Error n = " + n + " < k = " + k);
            System.exit(1);
        } //

        long denom = n;
        for (long i = 1; i < k; i++) {
            denom *= (n - i);
        } //

        long answer = denom / getFactorial(k);

        //System.out.println ("\tcombinations for <" + n + " ," + k + "> = " + answer) ;
        return answer;
    } //


    public static void buildFactorialNumbers(int n) {

        AllFactorialNumbers = new long[n + 1];
        AllFactorialNumbers[0] = 1;

        for (int i = 1; i <= n; i++) {
            AllFactorialNumbers[i] = i * AllFactorialNumbers[i - 1];
        } //

    } //


    public static void buildDerangementNumbers(int n) {
    
    /* ASSUMPTION n > 1 */

        AllDerangementNumbers = new long[n + 1];
        AllDerangementNumbers[0] = 1;
        AllDerangementNumbers[1] = 0;

        for (int i = 2; i <= n; i++) {
            AllDerangementNumbers[i] = (i - 1) * (AllDerangementNumbers[i - 1] + AllDerangementNumbers[i - 2]);
        } //

    } //


    public static void buildAllPermutationNumbers(int n) {

        AllPermutationNumbers = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    AllPermutationNumbers[i][j] = 1;
                } //
                else {
                    AllPermutationNumbers[i][j] = (AllPermutationNumbers[i][j - 1]) * (i - j + 1);
                } //
            } //
        } //

    } //


    public static void checkAllPermutationNumbers() {

        for (int i = 0; i < AllPermutationNumbers.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (AllPermutationNumbers[i][j] != getNumPermutations(i, j)) {
                    System.out.println("Mismatch permutations for <" + i + "," + j + "> "
                                       + AllPermutationNumbers[i][j] + " != " + getNumPermutations(i, j));
                } //
            } //
        } //

    } //


    public static void incrementCurrentUnreservedLetter() {
        ++CurrentUnreservedLetter;
        if (CurrentUnreservedLetter == NumLettersToUse) {
            CurrentUnreservedLetter = 0;
        } //
        //System.out.println ("\tincremented index to = " + CurrentUnreservedLetter) ;
    } //


    public static int getNextUnreservedLetter() {
        int current_index = CurrentUnreservedLetter;

        // position index to next free letter
        incrementCurrentUnreservedLetter();
        while (FlagReserved[CurrentUnreservedLetter]) {
            incrementCurrentUnreservedLetter();
        } //

        return current_index;
    } //

    public static int generateArrangements_1(int num, int starting_row) {
        // ASSUMPTION num > 0

        // things to track
        // how many letters have been reserved
        // which letters have been reserved
        // which row is to be used to start adding the letters
        // which column is to be used to place the letter in question
        ///
        // return how many rows have been partially filled

        // terminating condition num == 1
        if (num == 1) {
            AllArrangements_1[starting_row][0] = Alphabet_AZ[CurrentUnreservedLetter];
            //System.out.println ("Last letter for row [" + starting_row + "] = " + Alphabet_AZ [CurrentUnreservedLetter]) ;
            return 1;
        } //

        // find next unreserved letter
        // reserve it
        // call recursively to arrange remaining letters
        // for all rows that were arranged, add current letter

        int num_arrangements = 0;
        for (int choice = 1; choice <= num; choice++) {

            int current_letter = getNextUnreservedLetter();
            FlagReserved[current_letter] = true;
            //System.out.println ("  RESERVED letter for row [" + starting_row + "] choice [" + choice + "] of " + num + " = "
            //+ Alphabet_AZ [current_letter]) ;

            int num_sub_arrangements = generateArrangements_1(num - 1, starting_row);
            num_arrangements += num_sub_arrangements;

            for (int i = 0; i < num_sub_arrangements; i++) {
                AllArrangements_1[starting_row + i][num - 1] = Alphabet_AZ[current_letter];
                //System.out.println ("Adding letter for [" + (starting_row + i) + "]"
                //+ "[" + (num-1) + "] = " + Alphabet_AZ [current_letter] ) ;
            } //

            starting_row += num_sub_arrangements;

            FlagReserved[current_letter] = false;

        }

        return num_arrangements;

    } //


    public static int generateAllArrangements_1(int num) {
        // setup
        FlagReserved = new boolean[Alphabet_AZ.length];
        AllArrangements_1 = new char[(int) getFactorial(num)][num];
        CurrentUnreservedLetter = 0;
        NumLettersToUse = num;
        return generateArrangements_1(num, 0);
    } //


    public static void printAllArrangements_1() {

        for (int i = 0; i < AllArrangements_1.length; i++) {
            for (int j = AllArrangements_1[i].length - 1; j >= 0; j--) {
                System.out.print(AllArrangements_1[i][j]);
            } //
            System.out.println("");
        } //

    } //





/*
public static void solveForInputNumbersList ()
{


} // 
*/


    public static void main(String argv[]) throws IOException {

        if (argv.length != 1) {
            System.out.println("Usage :: java test.hack.FbWine <number>");
            System.exit(1);
        } //

    /*
    String file_name = argv[0] ;
    //System.out.println ("Opening file " + file_name) ;


    BufferedReader input_file = new BufferedReader (new FileReader (file_name)) ;

    String first_line = input_file.readLine() ;
    int num_lines = Integer.parseInt (first_line.trim()) ;

    InputNumbersList = new ArrayList<Long> (num_lines) ;

    for (int ll = 0 ; ll < num_lines ; ll++) {
    	
	String line = input_file.readLine().trim() ;
	String [] str_array = line.split (" ") ;
	createInputNumberList (str_array) ;
	solveForInputNumbersList () ;
    }

    input_file.close() ;
    */

        //System.out.println ("Alphabet_AZ = " + Arrays.toString(Alphabet_AZ) ) ;
        //System.out.println ("Alphabet_az = " + Arrays.toString(Alphabet_az) ) ;

        int num = Integer.parseInt(argv[0]);

        long num_factorial = getFactorial(num);
        long num_derange = getNumDerange(num);
        System.out.println("Factorial " + num + " ! = " + num_factorial);
        System.out.println("Derangement ! " + num + " = " + num_derange);
        System.out.println("Factorial / Derangement ratio = " + num_factorial * (double) 1.0 / num_derange);

        buildFactorialNumbers(num);
        System.out.println("Correctly built factorial for " + num + " ? "
                           + ((AllFactorialNumbers[num] == num_factorial) ? "Yes" : "No"));

        buildDerangementNumbers(num);
        System.out.println("Correctly built derangement for " + num + " ? "
                           + ((AllDerangementNumbers[num] == num_derange) ? "Yes" : "No"));

        buildAllPermutationNumbers(num);

        checkAllPermutationNumbers();

        int num_arrangements = generateAllArrangements_1(num);

        printAllArrangements_1();

        System.out.println("Correctly generated number of arrangements ? "
                           + ((num_arrangements == num_factorial) ? "Yes" : "No"));
    } //


}
