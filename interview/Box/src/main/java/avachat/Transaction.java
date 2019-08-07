package avachat;

public interface Transaction {

    //private StorageStructure

    void store(String key, String value);
    boolean contains(String key);
    String retrieve(String key);
    int count(String val);
    void delete(String key);
    void commit();
    void rollback();
}
