package avachat.fb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SortStrings {


    public static int getNumLines(BufferedReader input_file) throws IOException {

        String line = input_file.readLine();
        return Integer.parseInt(line.trim());
    } //


    public static void main(String argv[]) throws IOException {

        if (argv.length != 1) {
            System.out.println("Usage :: java test.hack.SortStrings" + " <file_name>");
            System.exit(1);
        } //

        String file_name = argv[0];
        //System.out.println ("Opening file " + file_name) ;

        BufferedReader input_file = new BufferedReader(new FileReader(file_name));

        int num_lines = getNumLines(input_file);

        for (int ll = 0; ll < num_lines; ll++) {

            String line = input_file.readLine().trim();
            //System.out.println ("Creating string array from " + line) ;

            String[] str_array_with_size = line.split(" ");
            //System.out.println (Arrays.toString(str_array_with_size)) ;

            int num_strings = Integer.parseInt(str_array_with_size[0]);
            if (num_strings <= 0) {
                System.out.println("Incorrect number of strings " + ll + " = " + line);
                continue;
            } //

            // move elements 1 place up
            //ArrayList<Object> str_list = new ArrayList<Object> (Arrays.asList (str_array_with_size)) ;
            //str_list.remove (0) ;
            //System.out.println ("Just strings as they were ordered " + str_list) ;

            String[] str_array = new String[num_strings];
            for (int i = 1, j = 0; i < str_array_with_size.length; i++) {
                String s = str_array_with_size[i].trim();
                if (s.length() <= 0) {
                    continue;
                } //
                str_array[j] = s;
                j++;
            } //

            //System.out.println ("Just the stings = " + Arrays.toString(str_array)) ;

            if (num_strings != str_array.length) {
                System.out.println("Incorrect line at " + ll + " = " + line);
                continue;
            } //

            Arrays.sort(str_array);

            //System.out.println ("Sorted stings = " + Arrays.toString(str_array)) ;

            // print output
            StringBuilder strbuf = new StringBuilder(10000);
            for (int i = 0; i < str_array.length; i++) {
                strbuf.append(str_array[i]);
            } //

            System.out.println(strbuf.toString());

        } //

        input_file.close();

    } //


} //
