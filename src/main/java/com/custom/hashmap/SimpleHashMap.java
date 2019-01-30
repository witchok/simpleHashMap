package com.custom.hashmap;

import com.custom.hashmap.exceptions.HashMapIsFullException;
import com.custom.hashmap.exceptions.NoSuchKeyException;


public class SimpleHashMap implements SimpleMap {
    public static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private int capacity;
    public Entry[] table;

    public SimpleHashMap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }
    public SimpleHashMap(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

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

    public int size() {
        return this.size;
    }

    private int hash(int key){
        return key % capacity;
    }

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
