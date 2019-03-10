package Test;

import Main.SimpleHashTable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SimpleHashTableTest {

    @Test
    void size() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("A",1);
        hashTable.put("B",2);
        assertEquals(2,hashTable.size());
    }

    @Test
    void isEmpty() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void containsKey() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("a",4);
        assertTrue(hashTable.containsKey("a"));
    }

    @Test
    void containsValue() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("b",8);
        assertTrue(hashTable.containsValue(8));
    }

    @Test
    void get() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("c",9);
        assertEquals(9,hashTable.get("c"));
    }

    @Test
    void put() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("d",10);
        assertTrue(hashTable.containsValue(10));
        assertTrue(hashTable.containsKey("d"));

    }

    @Test
    void remove() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("s",6);
        hashTable.put("e",3);
        hashTable.remove("e");
        assertEquals(1,hashTable.size());
        assertFalse(hashTable.containsKey("e"));
    }

    @Test
    void putAll() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        SimpleHashTable hashTable2 = new SimpleHashTable();
        hashTable.put("fe",4);
        hashTable2.putAll(hashTable);
        assertEquals(hashTable,hashTable2);
    }

    @Test
    void clear() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("eq",2);
        hashTable.clear();
        assertEquals(0,hashTable.size());
    }

    @Test
    void keySet() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("r",5);
        HashSet hashSet = new HashSet();
        hashSet.add("r");
       assertEquals(hashSet,hashTable.keySet());

    }

    @Test
    void values() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        hashTable.put("rs",5);
        ArrayList arrayList = new ArrayList();
        arrayList.add(5);
        assertEquals(arrayList,hashTable.values());
    }

    @Test
    void equals() {
        SimpleHashTable<String> hashTable = new SimpleHashTable<>();
        SimpleHashTable hashTable2 = new SimpleHashTable();
        hashTable.put("rs",5);
        hashTable.put("e",8);
        hashTable2.put("rs",5);
        hashTable2.put("e",8);

        assertTrue(hashTable.equals(hashTable2));
    }


}