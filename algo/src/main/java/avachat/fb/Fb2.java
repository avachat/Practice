package avachat.fb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Fb2 {


    static List<Long> InputNumbersList1;
    static List<Long> InputNumbersList2;
    static List<Long> OutputNumbersList;

    static {
        InputNumbersList1 = null;
        InputNumbersList2 = null;
        OutputNumbersList = null;
    } //


    public static void printInputNumbersList1() {
        Iterator<Long> iter = InputNumbersList1.iterator();
        while (iter.hasNext()) {
            Long l = iter.next();
            System.out.print(l);
            System.out.print(" ");
        } //

        System.out.println("");

    } //


    public static void createInputNumberList1(String[] str_array) {
        InputNumbersList1 = null;
        InputNumbersList1 = new ArrayList<Long>(str_array.length);
        //for (int i = 0 ; i < str_array.length ; i++) {
        for (int i = str_array.length - 1; i > 0; i--) {
            Long l = Long.parseLong(str_array[i]);
            InputNumbersList1.add(new Long(l));
        } //
        //printInputNumbersList1 () ;

    } //


    public static void printInputNumbersList2() {
        Iterator<Long> iter = InputNumbersList2.iterator();
        while (iter.hasNext()) {
            Long l = iter.next();
            System.out.print(l);
            System.out.print(" ");
        } //

        System.out.println("");

    } //


    public static void createInputNumberList2(String[] str_array) {
        InputNumbersList2 = null;
        InputNumbersList2 = new ArrayList<Long>(str_array.length);
        //for (int i = 0 ; i < str_array.length ; i++) {
        for (int i = str_array.length - 1; i > 0; i--) {
            Long l = Long.parseLong(str_array[i]);
            InputNumbersList2.add(new Long(l));
        } //
        for (int j = InputNumbersList2.size(); j < InputNumbersList1.size(); j++) {
            InputNumbersList2.add(new Long(0));
        } //

        //printInputNumbersList2 () ;

    } //


    public static void printOutputNumbersList() {
        Collections.reverse(OutputNumbersList);

        boolean found_nonzero = false;

        Iterator<Long> iter = OutputNumbersList.iterator();
        while (iter.hasNext()) {
            Long ll = iter.next();
            long l = ll.longValue();
            if (l != 0) {
                found_nonzero = true;
            } //
            if (!found_nonzero) {
                continue;
            } //

            System.out.print(l);
            System.out.print(" ");
        } //

        System.out.println("");

    } //


    public static boolean checkResult() {

        int max_degree = InputNumbersList2.size() + OutputNumbersList.size();
        for (int k = 1; k < max_degree; k++) {
            int i = k;
            int j = 0;
            long known_coeff_sum = 0;
            while (i >= 0) {
        /*
        System.out.println ("\tfor i=" + i
	    		+ " looking at input = " + InputNumbersList2.get(i)
			+ " and output = " + OutputNumbersList.get(j) ) ;
	    */
                long coeff1 = (InputNumbersList2.size() > i) ? InputNumbersList2.get(i) : 0;
                long coeff2 = (OutputNumbersList.size() > j) ? OutputNumbersList.get(j) : 0;
                known_coeff_sum += coeff1 * coeff2;
                //System.out.println ("\ttill now known_sum = " + known_coeff_sum ) ;
                --i;
                ++j;
            } //
            long expected = (InputNumbersList1.size() > k) ? InputNumbersList1.get(k) : 0;
            if (known_coeff_sum != expected) {
                //System.out.println ("known_sum = " + known_coeff_sum + " != expected " + expected) ;
                return false;
            } //
        } //

        return true;

    } //


    public static void solveForInputNumbersList() {
        OutputNumbersList = new ArrayList<Long>();
        for (int i = 0; i < InputNumbersList1.size(); i++) {
            OutputNumbersList.add(new Long(0));
        } //

        long mult = InputNumbersList1.get(0);
        long known_coeff_sum = 0;
        long remaining_coeff_sum = mult - known_coeff_sum;
        long last_coeff = InputNumbersList2.get(0);
        long missing_coeff = 0;

        if (remaining_coeff_sum == 0) {
            missing_coeff = 0;
        } //
        else {
            if (remaining_coeff_sum % last_coeff != 0) {
                System.out.println("no solution");
                return;
            } //
            else {
                missing_coeff = remaining_coeff_sum / last_coeff;
            } //
        }

        // degree 0
        OutputNumbersList.set(0, new Long(missing_coeff));
        //System.out.println ("for degree 0 " + missing_coeff) ;

        for (int k = 1; k < InputNumbersList1.size(); k++) {
            //System.out.println ("Figuring out coeef for degree " + k ) ;
            int i = k; // list1
            int j = 0; // list 2
            known_coeff_sum = 0;
            while (i > 0) {
                //System.out.println ("\tLooking at degree i=" + i + " and j=" + j) ;
                //System.out.println ( " \t" + InputNumbersList2.get(i)  + " " + OutputNumbersList.get(j) ) ;
                known_coeff_sum += (InputNumbersList2.get(i)) * (OutputNumbersList.get(j));
                //System.out.println ("\t\tknown_coeff_sum = " + known_coeff_sum ) ;
                --i;
                ++j;
            } //

            mult = InputNumbersList1.get(k);
            remaining_coeff_sum = mult - known_coeff_sum;

            last_coeff = InputNumbersList2.get(0);

            if (remaining_coeff_sum == 0) {
                missing_coeff = 0;
            } //
            else {
                if (remaining_coeff_sum % last_coeff != 0) {
                    System.out.println("no solution");
                    return;
                } //
                else {
                    missing_coeff = remaining_coeff_sum / last_coeff;
                } //
            }

            OutputNumbersList.set(k, new Long(missing_coeff));
            //System.out.println (missing_coeff) ;

        } //

        if (!checkResult()) {
            System.out.println("no solution");
            return;
        } //

        printOutputNumbersList();

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

        InputNumbersList1 = new ArrayList<Long>(num_lines);

        for (int ll = 0; ll < num_lines; ll++) {

            String line = input_file.readLine().trim();
            String[] str_array1 = line.split(" ");

            line = input_file.readLine().trim();
            String[] str_array2 = line.split(" ");

            if (str_array1.length < str_array2.length) {
                System.out.println("no solution");
                continue;
            } //

            createInputNumberList1(str_array1);
            createInputNumberList2(str_array2);
            solveForInputNumbersList();
        }

        input_file.close();


    } //


}
