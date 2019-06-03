package avachat.algo;

import org.junit.Test;

import static org.testng.Assert.*;

public class BinarySearchIndexValuesTest {

    private static BinarySearchIndexValues testObj = new BinarySearchIndexValues();

    @Test
    public void testIt () {
        //assertEquals(testObj.printSearchProgress(1, 10, 4), 5);
        //assertEquals(testObj.printSearchProgress(1, 2126753390, (2126753390/2) - 1), 1063376694);
        //assertEquals(testObj.printSearchProgress(1, 2126753390, (2126753390/2) + 1), 1063376696);
        //assertEquals(testObj.printSearchProgress(1, 1702766719, (1702766719/2) - 1), 851383358);
        //assertEquals(testObj.printSearchProgress(1, 1702766719, (1702766719/2)), 851383359);
        //assertEquals(testObj.printSearchProgress(1, 1702766719, (1702766719/2) + 1), 851383360);
        assertEquals(testObj.printSearchProgress(1, 2126753390, 1702766719), 1702766719);
    }

}