package avachat;

import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryDBTest {



    @Test
    public void testBasic() {

        InMemoryDB inMemoryDB = new InMemoryDB();

        //---------------------------------------------
        // add some keys and retrieve values
        inMemoryDB.store("a", "1");
        inMemoryDB.store("b", "2");
        inMemoryDB.store("c", "3");
        assertEquals("1", inMemoryDB.retrieve("a"));
        assertEquals("2", inMemoryDB.retrieve("b"));
        assertEquals("3", inMemoryDB.retrieve("c"));


        //---------------------------------------------
        // add some keys with same values
        inMemoryDB.store("aa", "1");
        assertEquals(2, inMemoryDB.count("1"));
        assertEquals(1, inMemoryDB.count("3"));

        //---------------------------------------------
        // remove some keys and check count
        inMemoryDB.delete("c");
        assertEquals("NULL", inMemoryDB.retrieve("c"));
        assertEquals(0, inMemoryDB.count("3"));
        // reduce count
        inMemoryDB.delete("aa");
        assertEquals(1, inMemoryDB.count("1"));
        // unmodified key
        assertEquals(1, inMemoryDB.count("2"));

        //---------------------------------------------
        // non existent keys
        assertEquals("NULL", inMemoryDB.retrieve("d"));
        assertEquals(0, inMemoryDB.count("9"));
        // delete non existent key
        inMemoryDB.delete("d");
        // delete completely and count
        inMemoryDB.delete("a");
        inMemoryDB.delete("b");
        assertEquals(0, inMemoryDB.count("1"));
        assertEquals(0, inMemoryDB.count("2"));


    }

}