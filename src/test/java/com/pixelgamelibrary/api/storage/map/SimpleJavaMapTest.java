package com.pixelgamelibrary.api.storage.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleJavaMapTest {

    private SimpleJavaMap simpleMap;

    @BeforeEach
    void setUp() {
        simpleMap = new SimpleJavaMap();
    }

    @Test
    void testPutAndGetString() {
        simpleMap.putString("key1", "value1");
        assertEquals("value1", simpleMap.getString("key1"));
    }

    @Test
    void testPutAndGetStringWithDefault() {
        simpleMap.putString("key1", "value1");
        assertEquals("value1", simpleMap.getString("key1", "default"));
        assertEquals("default", simpleMap.getString("key2", "default"));
    }

    @Test
    void testPutMultiple() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        simpleMap.put(map);

        assertEquals("value1", simpleMap.getString("key1"));
        assertEquals("value2", simpleMap.getString("key2"));
    }

    @Test
    void testGetReadOnlyMap() {
        simpleMap.putString("key1", "value1");
        Map<String, String> readOnlyMap = simpleMap.getReadOnlyMap();
        assertEquals("value1", readOnlyMap.get("key1"));

        // Attempting to modify the read-only map should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> readOnlyMap.put("key2", "value2"));
    }

    @Test
    void testContains() {
        simpleMap.putString("key1", "value1");
        assertTrue(simpleMap.contains("key1"));
        assertFalse(simpleMap.contains("key2"));
    }

    @Test
    void testClear() {
        simpleMap.putString("key1", "value1");
        simpleMap.clear();
        assertFalse(simpleMap.contains("key1"));
        assertNull(simpleMap.getString("key1"));
    }

    @Test
    void testRemove() {
        simpleMap.putString("key1", "value1");
        simpleMap.remove("key1");
        assertFalse(simpleMap.contains("key1"));
        assertNull(simpleMap.getString("key1"));
    }

    @Test
    void testFlush() {
        // No-op test since flush does nothing
        simpleMap.flush();
        // No assertions needed, just checking no exception is thrown
    }

    @Test
    void testKeyList() {
        simpleMap.putString("key1", "value1");
        simpleMap.putString("key2", "value2");
        assertTrue(simpleMap.keyList().contains("key1"));
        assertTrue(simpleMap.keyList().contains("key2"));
        assertFalse(simpleMap.keyList().contains("key3"));
    }
}
