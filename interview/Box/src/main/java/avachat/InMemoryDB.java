package avachat;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {


    private final Map<String, String> keyMap;
    private final Map<String, Integer> valueCounts;

    public InMemoryDB () {

        this.keyMap = new HashMap<>();
        this.valueCounts = new HashMap<>();
    }

    public void store(String key, String value) {

        // store in keyMap
        keyMap.put(key, value);

        // increment count
        int prevCount = valueCounts.getOrDefault(value, 0);
        valueCounts.put(value, prevCount+1);
    }

    public String retrieve(String key) {
        return keyMap.getOrDefault(key, "NULL");
    }

    public int count(String val) {
        return valueCounts.getOrDefault(val, 0);
    }

    public void delete(String key) {

        // if key doesn't exist nothing to do
        if (! keyMap.containsKey(key)) {
            return;
        }

        // get value
        // make sure internal structure is consistent
        String val = keyMap.getOrDefault(key, null);
        if (null == val) {
            throw new RuntimeException("Internal Error : no value for key = " + key);
        }

        // get count
        // make sure internal structure is consistent
        int prevCount = valueCounts.getOrDefault(val, 0);
        if (prevCount <= 0) {
            throw new RuntimeException("Internal Error : count for value = " + val + " = " + prevCount);
        }


        keyMap.remove(key);
        // delete key

        // decrement count
        valueCounts.put(val, prevCount-1);

    }


    /*
    public int countKeys() {
        return keyMap.keySet().size();
    }
    */

}
