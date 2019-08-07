package avachat;

import java.util.HashMap;
import java.util.Map;

public class StorageStructure implements Transaction {


    private final Map<String, String> keyMap;
    private final Map<String, Integer> valueCounts;

    public StorageStructure() {

        this.keyMap = new HashMap<>();
        this.valueCounts = new HashMap<>();
    }

    public void store(String key, String value) {

        // TODO : decrement old value count

        // store in keyMap
        keyMap.put(key, value);

        // increment count
        int prevCount = valueCounts.getOrDefault(value, 0);
        valueCounts.put(value, prevCount+1);
    }

    public boolean contains(String key) {
        return keyMap.containsKey(key);
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

        // delete key
        keyMap.remove(key);

        // decrement count
        valueCounts.put(val, prevCount-1);

    }

    @Override
    public void commit() {
        // no op
    }

    @Override
    public void rollback() {
        // no op
    }


    /*
    public int countKeys() {
        return keyMap.keySet().size();
    }
    */

}
