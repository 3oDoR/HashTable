package Test;

import Main.SimpleHashTable;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleHashTableTest {
    private SimpleHashTable<String> hashTable = new SimpleHashTable<>();


    @Test
    void size() {

        hashTable.put("A",1);
        hashTable.put("B",2);
        assertEquals(2,hashTable.size());
    }

    @Test
    void isEmpty() {

        assertTrue(hashTable.isEmpty());
    }

    @Test
    void containsKey() {
        hashTable.put("a",4);
        assertTrue(hashTable.containsKey("a"));
    }

    @Test
    void containsValue() {
        hashTable.put("b",8);
        assertTrue(hashTable.containsValue(8));
    }

    @Test
    void get() {
        hashTable.put("c",9);
        assertEquals(9,hashTable.get("c"));
    }

    @Test
    void put() {
        hashTable.put("d",10);
        assertEquals("SimpleHashTable{\n" + "Key: d Value: 10\n" + "}",hashTable.toString());

    }

    @Test
    void remove() {
        hashTable.put("s",6);
        hashTable.put("e",3);
        hashTable.remove("e");
        assertEquals(1,hashTable.size());
        assertEquals("SimpleHashTable{\n" + "Key: s Value: 6\n" + "}",hashTable.toString());
    }

    @Test
    void putAll() {
        SimpleHashTable hashTable2 = new SimpleHashTable();
        hashTable.put("fe",4);
        hashTable2.putAll(hashTable);
        assertEquals(hashTable.toString(),hashTable2.toString());
    }

    @Test
    void clear() {
        hashTable.put("eq",2);
        hashTable.clear();
        assertEquals(0,hashTable.size());
    }

    @Test
    void keySet() {
        hashTable.put("r",5);
        hashTable.put("qq",42);
        assertEquals("[qq, r]",hashTable.keySet().toString());
    }

    @Test
    void values() {
        hashTable.put("rs",5);
        hashTable.put("qqs",42);
        assertEquals("[5, 42]",hashTable.values().toString());
    }

    @Test
    void equals() {
        SimpleHashTable hashTable2 = new SimpleHashTable();
        hashTable.put("rs",5);
        hashTable2.put("rs",5);

        assertTrue(hashTable.equals(hashTable2));
    }


}