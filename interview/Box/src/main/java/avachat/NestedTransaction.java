package avachat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NestedTransaction implements Transaction {

    private final Transaction parentTransaction;
    private final StorageStructure deltaKeys;
    private final Set<String> removedKeys;
    private final Map<String, Integer> decrementParentCounts;

    public NestedTransaction(Transaction parent) {
        this.deltaKeys = new StorageStructure();
        this.removedKeys = new HashSet<>();
        this.decrementParentCounts = new HashMap<>();
        this.parentTransaction = parent;
    }


    @Override
    public void store(String key, String value) {

        // add to self
        deltaKeys.store(key, value);

        // if previously removed, then no longer consider it removed
        removedKeys.remove(key);

        // how does the parent get effectively modified
        if (! parentTransaction.contains(key)) {
            // no conflict
            return;
        }

        // parent contains the key
        // so effective values will change
        String valueAtParent = parentTransaction.retrieve(key);
        // if valueAtParent is same, nothing to do
        if (value.equals(valueAtParent)) {
            return;
        }

        // a new value got added for parent
        // so effective count of values will change now
    }

    @Override
    public boolean contains(String key) {
        if (deltaKeys.contains(key)) {
            return true;
        }
        return parentTransaction.contains(key);
    }

    @Override
    public String retrieve(String key) {

        // if it's deleted at this level, return null
        if (removedKeys.contains(key)) {
            return "NULL";
        }

        // if exists at this level, return it
        if (deltaKeys.contains(key)) {
            return deltaKeys.retrieve(key);
        }

        return parentTransaction.retrieve(key);
    }

    @Override
    public int count(String val) {

        // if it's removed at this level, then count is zero
        return 0;
    }

    @Override
    public void delete(String key) {

        // if it's already removed, nothing to do
        if (removedKeys.contains(key)) {
            return;
        }

        // remove from self
        deltaKeys.delete(key);

        // mark as removed
        removedKeys.remove(key);


        // TODO change effective count
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
