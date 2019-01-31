package com.custom.hashmap;

/**
 * Interface for map with put, size, get methods
 */
public interface SimpleMap {
    /**
     * Method puts element with given value under given key in HashMap
     * @param key given key under which value will be stored
     * @param value given value to be stored
     */
    void put(int key, long value);

    /**
     * Method gets value by given key
     * @param key
     * @return value for given key
     */
    long get(int key);

    /**
     * @return amount of elements in HashMap
     */
    int size();
}
