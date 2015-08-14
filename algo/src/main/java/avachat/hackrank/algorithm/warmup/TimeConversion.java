package avachat.hackrank.algorithm.warmup;

import java.util.Scanner;

/**
 * Created by avachat on 8/14/15.
 */

/*

Problem Statement

You are given time in AM/PM format. Convert this into a 24 hour format.

Note Midnight is 12:00:00AM or 00:00:00 and 12 Noon is 12:00:00PM.

Input Format

Input consists of time in the AM/PM format i.e. hh:mm:ssAM or hh:mm:ssPM
where
01≤hh≤12
00≤mm≤59
00≤ss≤59

Output Format

You need to print the time in 24 hour format i.e. hh:mm:ss
where
00≤hh≤23
00≤mm≤59
00≤ss≤59

Sample Input

07:05:45PM
Sample Output

19:05:45


 */
public class TimeConversion {

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        // input str
        String timeStr = scanner.next();

        String hhmmssStr = timeStr.substring(0, timeStr.length() - 2);

        boolean isPM = (timeStr.charAt(timeStr.length()-2) == 'P');

        String[] hhmmssStrArray = hhmmssStr.split(":");

        int hour = Integer.parseInt(hhmmssStrArray[0]);
        int mins = Integer.parseInt(hhmmssStrArray[1]);
        int secs = Integer.parseInt(hhmmssStrArray[2]);

        if (isPM && (hour != 12)) {
            hour += 12;
        } else if ( (!isPM) && (hour == 12)) {
            hour = 0;
        }

        System.out.format("%02d:%02d:%02d%n", hour, mins, secs);

    }
}
