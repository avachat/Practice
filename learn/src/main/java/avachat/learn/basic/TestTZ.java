package avachat.learn.basic;

import java.util.Date;
import java.util.TimeZone;

public class TestTZ {

    public static void main(String args[]) {
        System.out.println("Current time: " + (new Date()).toString());
        System.out.println("Current time zone: " + TimeZone.getDefault().getID());
    }
}
