package avachat.fb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


class IntPair {

    public IntPair(int ii, int jj) {
        i = ii;
        j = jj;
    }

    public int i, j;

    public String toString() {
        return "<" + i + "," + j + ">";
    }
} // 

public class DoubleSquares {


    static final int ARRAY_SIZE = 47000;
    static int NumOfAllSquares;
    static long[] AllSquares;
    static Map<Long, List<IntPair>> DoubleSquarePairs;
    static Map<Long, Integer> DoubleSquareCounts;
    static List<Long> InputNumbersList;

    static {

        AllSquares = new long[ARRAY_SIZE];

        //AllSquares [0] = 0 ;

        NumOfAllSquares = 0;

        //IntPair pair = new IntPair (0,1) ;
        //List<IntPair> list = new ArrayList<IntPair> () ;
        //list.add (pair) ;

        DoubleSquarePairs = new HashMap<Long, List<IntPair>>();
        //DoubleSquarePairs.put (new Long(1), list) ;

        DoubleSquareCounts = new HashMap<Long, Integer>();
        //DoubleSquareCounts.put (new Long(1), new Integer(1)) ;

        InputNumbersList = new ArrayList<Long>();
    } //

    public static void PrintAllSquares() {
        for (int i = 0; i < NumOfAllSquares; i++) {
            System.out.println("AllSquares [" + i + "] = " + AllSquares[i]);
        } //
    } //


    public static void PrintAllDoubleSquares() {

        //Iterator<Long> map_iter = DoubleSquarePairs.keySet().iterator() ;
        Iterator<Long> iter = InputNumbersList.iterator();
        while (iter.hasNext()) {
            Long l = iter.next();
            List<IntPair> list = DoubleSquarePairs.get(l);
            System.out.println("Pairs for " + l + " = " + list);
            //System.out.println (list.size()) ;
        } //

    } //

    public static void PrintAllDoubleSquareCounts() {

        //Iterator<Long> map_iter = DoubleSquareCounts.keySet().iterator() ;
        Iterator<Long> iter = InputNumbersList.iterator();
        while (iter.hasNext()) {
            Long l = iter.next();
            System.out.println("" + l + " = " + DoubleSquareCounts.get(l));
            //System.out.println ( DoubleSquareCounts.get (l) ) ;
        } //

    } //


    public static void AddDoubleSquarePair(long dbl_square_param, int n1, int n2) {
        Long dbl_square = new Long(dbl_square_param);

        List<IntPair> list = DoubleSquarePairs.get(dbl_square);
        if (list == null) {
            //System.out.println ("\t\tFirst entry for " + dbl_square) ;
            //list = new ArrayList<IntPair> () ;
            //DoubleSquarePairs.put (dbl_square, list) ;
            return;
        } //

        //System.out.println ("\tadding pair <" + n1 + "," + n2 + "> = " + dbl_square) ;
        IntPair pair = new IntPair(n1, n2);
        list.add(pair);
        //System.out.println ("\tList for " + dbl_square + " = " + list) ;

        Integer count = DoubleSquareCounts.get(dbl_square);
        if (count == null) {
            count = new Integer(0);
        } //

        count = new Integer(count.intValue() + 1);
        DoubleSquareCounts.put(dbl_square, count);
        //System.out.println ("num of db squares = " + DoubleSquareCounts.size()) ;

    } //

    public static void GeneratePairsForLastSquare() {
        //int latest_number = NumOfAllSquares ;
        for (int i = 0; i < NumOfAllSquares; i++) {
            long dbl_square = AllSquares[i] + AllSquares[NumOfAllSquares - 1];
            if (dbl_square > Integer.MAX_VALUE) {
                continue;
            } //
            AddDoubleSquarePair(dbl_square, i, NumOfAllSquares - 1);
        } //
    } //

    public static void AnalyzeSquaresUpto(long num) {
        long num2 = Math.round(Math.ceil(Math.sqrt((double) num))) + 1;
        if (num2 > ARRAY_SIZE) {
            System.out.println("#### Limiting " + num2 + " to " + ARRAY_SIZE);
            num2 = ARRAY_SIZE;
        } //
        //System.out.println ("Generating squares upto " + (num2-1)) ;

        if (NumOfAllSquares >= num2) {
            System.out.println("No need to generate any more squares");
            return;
        } //

        for (int i = NumOfAllSquares; i < num2; i++) {
            AllSquares[i] = (long) i * (long) i;
            //System.out.println ("\tgenerated [" + i + "] = " + AllSquares[i] ) ;
            ++NumOfAllSquares;
            GeneratePairsForLastSquare();
        } //

        //PrintAllSquares () ;
        //PrintAllDoubleSquares () ;
        //PrintAllDoubleSquareCounts () ;
    } //

    public static void CheckDoubleSquareness(long num) {

        //System.out.println ("Checking " + num) ;

        AnalyzeSquaresUpto(num);
    } //

    public static void main(String argv[]) throws IOException {

        if (argv.length != 1) {
            System.out.println("Usage :: java test.hack.DoubleSquares <file_name>");
            System.exit(1);
        } //

        String file_name = argv[0];
        //System.out.println ("Opening file " + file_name) ;

        BufferedReader input_file = new BufferedReader(new FileReader(file_name));

        String first_line = input_file.readLine();
        int num_lines = Integer.parseInt(first_line.trim());

        long[] input_numbers = new long[num_lines];
        InputNumbersList = new ArrayList<Long>(num_lines);

        for (int ll = 0; ll < num_lines; ll++) {

            String line = input_file.readLine().trim();
            long num = Long.parseLong(line);
            input_numbers[ll] = num;
            InputNumbersList.add(num);
            DoubleSquareCounts.put(new Long(num), new Integer(0));
            DoubleSquarePairs.put(new Long(num), new ArrayList<IntPair>());
        }

        input_file.close();

        Arrays.sort(input_numbers);

        CheckDoubleSquareness(input_numbers[num_lines - 1]);

        PrintAllDoubleSquares();
        PrintAllDoubleSquareCounts();

        Iterator<Long> iter = InputNumbersList.iterator();
        while (iter.hasNext()) {
            Long num = iter.next();
            System.out.println(DoubleSquareCounts.get(num));
        } //

    } //

} // 
