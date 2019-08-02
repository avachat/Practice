package avachat;

import org.junit.Test;

import static org.junit.Assert.*;

public class StorageStructureTest {



    @Test
    public void testBasic() {

        StorageStructure storageStructure = new StorageStructure();

        //---------------------------------------------
        // add some keys and retrieve values
        storageStructure.store("a", "1");
        storageStructure.store("b", "2");
        storageStructure.store("c", "3");
        assertEquals("1", storageStructure.retrieve("a"));
        assertEquals("2", storageStructure.retrieve("b"));
        assertEquals("3", storageStructure.retrieve("c"));


        //---------------------------------------------
        // add some keys with same values
        storageStructure.store("aa", "1");
        assertEquals(2, storageStructure.count("1"));
        assertEquals(1, storageStructure.count("3"));

        //---------------------------------------------
        // remove some keys and check count
        storageStructure.delete("c");
        assertEquals("NULL", storageStructure.retrieve("c"));
        assertEquals(0, storageStructure.count("3"));
        // reduce count
        storageStructure.delete("aa");
        assertEquals(1, storageStructure.count("1"));
        // unmodified key
        assertEquals(1, storageStructure.count("2"));

        //---------------------------------------------
        // non existent keys
        assertEquals("NULL", storageStructure.retrieve("d"));
        assertEquals(0, storageStructure.count("9"));
        // delete non existent key
        storageStructure.delete("d");
        // delete completely and count
        storageStructure.delete("a");
        storageStructure.delete("b");
        assertEquals(0, storageStructure.count("1"));
        assertEquals(0, storageStructure.count("2"));


    }

}