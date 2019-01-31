package com.custom.hashmap;

import com.custom.hashmap.exceptions.HashMapIsFullException;
import com.custom.hashmap.exceptions.NoSuchKeyException;


/**
 * Simple Open Addressing Hash Map with put, get, size operations
 */
public class SimpleHashMap implements SimpleMap {
    public static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private int capacity;
    public Entry[] table;

    /**
     * Constructor which creates HashMap with given capacity
     * @param capacity capacity of HashMap
     */
    public SimpleHashMap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }
    /**
     * Constructor which creates HashMap with DEFAULT_INITIAL_CAPACITY
     */
    public SimpleHashMap(){
        this(DEFAULT_INITIAL_CAPACITY);
    }


    /**
     * Method puts element with given value under given key in HashMap
     * @param key given key under which value will be stored
     * @param value given value to be stored
     */
    public void put(int key, long value) {
//        if ((double)size/capacity == 1){
//            throw new HashMapIsFullException();
//        }
        int hashKey = hash(key);
        int counter = 0;
        while (table[hashKey]!=null && table[hashKey].getKey()!=key){
            if(counter++ > capacity){
                throw new HashMapIsFullException();
            }
            hashKey = (hashKey+1)%capacity;
        }
        if(table[hashKey]==null) {
            size++;
        }
        table[hashKey] = new Entry(key, value);
    }

    /**
     * Method gets value by given key
     * @param key
     * @return value for given key
     */
    public long get(int key) {
        int hashKey = hash(key);
        int counter = 0;
        while (table[hashKey] != null){
            if(counter++ > capacity){
                throw new NoSuchKeyException();
            }
            if(table[hashKey].getKey() == key){
                return table[hashKey].getValue();
            }
            hashKey = (hashKey+1)%capacity;
        }
        throw new NoSuchKeyException();
    }

    /**
     * @return amount of elements in HashMap
     */
    public int size() {
        return this.size;
    }

    /**
     * Method for calculating hash for given key
     * @param key
     * @return hash for given key
     */
    private int hash(int key){
        return key % capacity;
    }

    /**
     * Simple entry for hash map, which has int key and long value
     */
    static class Entry{
        private final int key;
        private long value;
        public Entry(int key, long value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value){
            this.value = value;
        }


        /**
         * Compare this object for equality with given
         * @param obj object to compare
         * @return boolean true if objects are equal otherwise false
         */
        @Override
        public boolean equals(Object obj) {
            if(obj == this) {
                return true;
            } else if(obj instanceof Entry) {
                if(((Entry) obj).getKey() == this.key
                        && ((Entry) obj).getValue() == this.value)
                    return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("%-10s%s",getKey(),getValue());
        }
    }

//    public static void main(String[] args) {
//        SimpleHashMap.Entry entry = new Entry(3, 132);
//        System.out.println(entry);
//        SimpleMap map = new SimpleHashMap();
//        System.out.println(map.size());
//        map.put(1,1230);
//        System.out.println(map.size());
//        map.put(2,1231);
//        System.out.println(map.size());
//        map.put(3,12230);
//        System.out.println(map.size());
//        map.put(4,1230);
//        System.out.println(map.size());
//
//        map.put(5,12);
//        System.out.println(map.size());
//        try {
//            System.out.println(map.get(231));
//        }catch(NoSuchKeyException e){
//            System.out.println("There is no such key in the map");
//        }
//
//        map.put(5,21);
//    }
}
