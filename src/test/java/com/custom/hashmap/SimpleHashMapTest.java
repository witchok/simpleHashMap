package com.custom.hashmap;

import com.custom.hashmap.exceptions.HashMapIsFullException;
import com.custom.hashmap.exceptions.NoSuchKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleHashMapTest {
    private static final int TEST_CAPACITY=10;

    private SimpleMap simpleHashMap;

    @BeforeEach
    void init(){
        simpleHashMap = new SimpleHashMap(TEST_CAPACITY);
    }

    @Test
    void shouldPutElementsIntoMap(){
        int key1 = 3;
        long value1 = 234;

        int key2 = 1324;
        long value2 = 145678;

        int key3 = 7890;
        long value3 = 213;
        simpleHashMap.put(key1,value1);
        assertEquals(1,simpleHashMap.size());

        simpleHashMap.put(key2,value2);
        assertEquals(2,simpleHashMap.size());

        simpleHashMap.put(key3,value3);
        assertEquals(3,simpleHashMap.size());

        assertEquals(value1,simpleHashMap.get(key1));
        assertEquals(value2,simpleHashMap.get(key2));
        assertEquals(value3,simpleHashMap.get(key3));
    }

    @Test
    void shouldRewriteElementsWithTheSameKey(){
        int key1 = 3;
        long value1 = 234;
        long value2 = 1234567;
        long value3 = 2;

        simpleHashMap.put(1,90);
        simpleHashMap.put(key1,value1);
        simpleHashMap.put(2,92);

        assertEquals(3,simpleHashMap.size());
        assertEquals(value1,simpleHashMap.get(key1));

        simpleHashMap.put(key1,value2);
        assertEquals(3,simpleHashMap.size());
        assertEquals(value2,simpleHashMap.get(key1));

        simpleHashMap.put(key1,value3);
        assertEquals(3,simpleHashMap.size());
        assertEquals(value3,simpleHashMap.get(key1));
    }

    @Test
    void shouldThrowNoSuchKeyException(){
        final int key = 1;

        simpleHashMap.put(3,1233);
        simpleHashMap.put(4,1234);
        simpleHashMap.put(5,1235);
        simpleHashMap.put(6,1236);
        simpleHashMap.put(7,1237);

        assertThrows(NoSuchKeyException.class, () -> simpleHashMap.get(key));
    }

    @Test
    void shouldThrowHashMapIsFullException(){
        int key = TEST_CAPACITY+1;
        for (int i = 1; i <= TEST_CAPACITY; i++) {
            simpleHashMap.put(i,i*1000);
        }
        assertEquals(TEST_CAPACITY, simpleHashMap.size());

        assertThrows(HashMapIsFullException.class, () -> simpleHashMap.put(key,12));
    }

    @Test
    void shouldRewriteElementInFullMap(){
        int keyToRewrite = TEST_CAPACITY-1;
        long value = 1001;
        for (int i = 1; i <= TEST_CAPACITY; i++) {
            simpleHashMap.put(i,i*1000);
        }
        assertEquals(TEST_CAPACITY, simpleHashMap.size());
        simpleHashMap.put(keyToRewrite,value);
        assertEquals(value, simpleHashMap.get(keyToRewrite));
    }


}
