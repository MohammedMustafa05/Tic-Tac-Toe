import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {

    private LinkedList<Data>[] table;
    private int numRecords;



    public HashDictionary(int size) {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
        numRecords = 0;
    }

    private int hashFunction(String key) {
        int hashValue = 0;
        int prime = 17;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (prime * hashValue + key.charAt(i)) % table.length;
        }
        return Math.abs(hashValue);
    }

    @Override
    public int put(Data record) throws DictionaryException {
        int hashIndex = hashFunction(record.getConfiguration());
        LinkedList<Data> bucket = table[hashIndex];

        for (Data data : bucket) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
        }

        bucket.add(record);
        numRecords++;
        if(bucket.size() > 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public void remove(String config) throws DictionaryException {
        int hashIndex = hashFunction(config);
        LinkedList<Data> bucket = table[hashIndex];

        for (Data data : bucket) {
            if (data.getConfiguration().equals(config)) {
                bucket.remove(data);
                numRecords--;
                return;
            }
        }

        throw new DictionaryException();
    }

    @Override
    public int get(String config) {
        int hashIndex = hashFunction(config);
        LinkedList<Data> bucket = table[hashIndex];

        for (Data data : bucket) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore();
            }
        }

        return -1;
    }

    @Override
    public int numRecords() {
        return numRecords;
    }
}
