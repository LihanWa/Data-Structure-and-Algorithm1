package cs445.rec11;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-hashing-based dictionary
   Search keys and associated values are not null.
   @author William C. Garrison III
   @version 5.1
*/
public class HashingDictionary<K, V> implements DictionaryInterface<K, V>, Iterable<K> {

    // Prime-sized hash table for storage
    private Pair[] hashContents;
    // Modulus for the second hash function
    private int secondMod;
    // Number of pairs stored
    private int size;
    // Number of non-null spaces (includes tombstones)
    private int loadSize;

    private final Pair TOMBSTONE = new Pair(null, null);

    private class Pair {
        K key;
        V value;
        Pair(K k, V v) {
            key = k;
            value = v;
        }
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }

    public void print() {
        System.out.println(java.util.Arrays.toString(hashContents));
    }

    public HashingDictionary() {
        // Reasonable default prime size
        hashContents = (Pair[])new HashingDictionary<?,?>.Pair[19];
        secondMod = 17;
        size = 0;
        loadSize = 0;
    }

    private int hash(K key) {
        int h = key.hashCode() % hashContents.length;
        // Ensure non-negative
        if (h < 0) h += hashContents.length;
        return h;
    }

    private int hash2(K key) {
        int offset = key.hashCode() % secondMod;
        // Ensure non-negative
        if (offset < 0) offset += secondMod;
        // Ensure not 0
        offset++;
        return offset;
    }

    private boolean isTooFull() {
        return (loadSize > 0.75 * hashContents.length);
    }

    private void increaseCapacity() {
        Pair[] old = hashContents;
        int newCap = Primes.primeNoLessThan(hashContents.length * 2);

        hashContents = (Pair[])new HashingDictionary<?,?>.Pair[newCap];
        secondMod = Primes.primeNoMoreThan(newCap-1);
        size = 0;
        loadSize = 0;

        for (int i = 0; i < old.length; i++) {
            if (old[i] != null && old[i] != TOMBSTONE) {
                add(old[i].key, old[i].value);
            }
        }
    }

    /**
     * Adds a new pair to this dictionary. If the given search key already
     * exists in the dictionary, replaces the corresponding value.
     * @param key  The key to insert
     * @param value  The value associated with the given key
     * @return  Either null if the new pair was added to the dictionary or the
     * value that was associated with key if that value was replaced.
     */
    public V add(K key, V value) {
        if (key == null || value == null) throw new NullPointerException();
        if (isTooFull()) increaseCapacity();

        V result;
        int i = hash(key);
        int offset = hash2(key);
        int tombLoc = -1;

        // Find a position to add; empty or already storing this key
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].key.equals(key))) {
            if (hashContents[i] == TOMBSTONE && tombLoc < 0) tombLoc = i;
            i = (i + offset) % hashContents.length;
        }

        if (hashContents[i] == null) {
            result = null;
            size++;
            if (tombLoc >= 0) {
                i = tombLoc;
            } else {
                loadSize++;
            }
        } else {
            result = hashContents[i].value;
        }

        hashContents[i] = new Pair(key, value);

        return result;
    }

    /**
     * Removes a specific pair from this dictionary.
     * @param key  The key to search for and remove
     * @return  Either the value that was associated with the key or null if no
     * such pair exists.
     */
    public V remove(K key) {
        V result = null;
        int i = hash(key);
        int offset = hash2(key);

        // Find the key or fully empty spot
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].key.equals(key))) {
            i = (i + offset) % hashContents.length;
        }

        if (hashContents[i] != null) {
            result = hashContents[i].value;
            hashContents[i] = TOMBSTONE;
            size--;
        }

        return result;
    }

    /**
     * Retrieves from this dictionary the value associated with a given key.
     * @param key  The key to search for and retrieve
     * @return  Either the value that is associated with the key or null if no
     * such pair exists
     */
    public V getValue(K key) {
        V result = null;
        int i = hash(key);
        int offset = hash2(key);

        // Find the key or a fully empty spot
        while (hashContents[i] != null &&
                (hashContents[i] == TOMBSTONE || !hashContents[i].key.equals(key))) {
            i = (i + offset) % hashContents.length;
        }

        if (hashContents[i] != null) {
            result = hashContents[i].value;
        }

        return result;
    }

    /**
     * Determines whether a pair with a specified key is in this dictionary.
     * @param key  The key to search for
     * @return  True if key is associated with a pair in the dictionary
     */
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * Creates an iterator that traverses all keys in this dictionary.
     * @return  An iterator that provides sequential access to the search keys
     * in the dictionary.
     */
    public Iterator<K> getKeyIterator() {
        return new UnifiedIterator<K>(false);
    }

    /**
     * Creates an iterator that traverses all keys in this dictionary.
     * @return  An iterator that provides sequential access to the search keys
     * in the dictionary.
     */
    public Iterator<K> iterator() {
        return getKeyIterator();
    }

    /**
     * Creates an iterator that traverses all values in this dictionary.
     * @return  An iterator that provides sequential access to the values in
     * this dictionary.
     */
    public Iterator<V> getValueIterator() {
        return new UnifiedIterator<V>(true);
    }

    private class UnifiedIterator<Q> implements Iterator<Q> {
        int next, numReturned;
        boolean values;

        UnifiedIterator(boolean vals) {
            next = 0;
            numReturned = 0;
            values = vals;
        }

        public boolean hasNext() {
            return numReturned < size;
        }

        public Q next() {
            if (hasNext()) {
                while (hashContents[next] == null || hashContents[next] == TOMBSTONE) {
                    next++;
                }
                numReturned++;
                if (values) {
                    return (Q)hashContents[next++].value;
                } else {
                    return (Q)hashContents[next++].key;
                }
            } else throw new NoSuchElementException();
        }

        public void delete() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Determines whether this dictionary is empty.
     * @return  True if the dictionary is empty.
     */
    public boolean isEmpty() {
        return size != 0;
    }

    /**
     * Returns the size of this dictionary.
     * @return  The number of entries (key-value pairs) currently in the
     * dictionary.
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all entries from this dictionary.
     */
    public void clear() {
        hashContents = (Pair[])new HashingDictionary<?,?>.Pair[19];
        secondMod = 17;
        size = 0;
        loadSize = 0;
    }

} // end HashingDictionary

