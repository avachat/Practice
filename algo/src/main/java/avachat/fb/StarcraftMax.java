package avachat.fb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StarcraftMax {

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


    public static void solveForInputNumbersList() {
        double costG = InputNumbersList.get(0);
        double costW = InputNumbersList.get(1);
        double money = InputNumbersList.get(2);

        double ratio = costW / costG;
        double zeroPoint = money - (ratio * costW);

        //long halfPointG = (long) ((money/2) / costG ) ;
        //long halfPointW

        double optimalW = (money / 2) / costW;

        //System.out.println ( "zeroPoint = " + (long) zeroPoint ) ;
        System.out.println((long) (Math.floor(optimalW)));
    } //


    public static void main(String argv[]) throws IOException {

        if (argv.length != 1) {
            System.out.println("Usage :: java test.hack.StarcraftMAx <file_name>");
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
