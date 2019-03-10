package Main;

import java.util.*;


@SuppressWarnings("unchecked")
public class SimpleHashTable<K> implements Map<K, Integer> {
    private Node[] nodes;
    private int INITIAL_CAPACITY = 64;
    private List<K> keySet = new ArrayList<K>();

    public SimpleHashTable() {
        nodes = new Node[INITIAL_CAPACITY];
    }


    /**
     * @return the size of the hashtable
     */
    @Override
    public int size() {
        return keySet.size();
    }

    /**
     *
     * @return true if the hash table is empty otherwise false
     */
    @Override
    public boolean isEmpty() {
        return keySet.isEmpty();
    }


    /**
     *
     * @param key - parameter supplied for comparison with the key
     * @return if there is such a key outputs the true otherwise false
     */
    @Override
        public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }

        return keySet.contains(key);
    }

    /**
     *
     * @param value - parameter supplied for comparison with the value
     * @return if there is such a value outputs the truth otherwise false
     */

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        for (Node node : nodes) {
            for (Node next = node; next != null; next = next.getNext()) {
                if (value.equals(next.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * @param key - the parameter supplied to the search key
     * @return  the value of the derived key
     */

    @Override
    public Integer get(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = getIndex(key);

        if (nodes.length < index) {
            return null;
        }

        if (nodes[index] == null) {
            return null;
        }

        for (Node next = nodes[index]; next != null; next = next.getNext()) {
            if (key.equals(next.getKey())) {
                return next.getValue();
            }
        }

        return null;
    }

    /**
     *
     * @param key - еру option to add as a key
     * @param value - the option to add as the value
     * @return puts in a hashtable key and value and outputs the value
     */

    @Override
    public Integer put(K key, Integer value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }

        if (containsValue(value)) {
            return null;
        }

        int index = getIndex(key);

        if (nodes[index] == null) {
            nodes[index] = new Node(key, value);
            keySet.add(key);
        } else {
            for (Node next = nodes[index]; next != null; next = next.getNext()) {
                if (key.equals(next.getKey())) {
                    next.setValue(value);

                    return value;
                } else if (next.getNext() == null) {
                    next.setNext(new Node(key, value));
                    keySet.add(key);
                    return value;
                }
            }
        }

        return value;
    }

    /**
     *
     * @param key - the parameter by which the desired cell is searched
     * @return the value of a remote key if it is not null
     */
    @Override
    public Integer remove(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = getIndex(key);

        if (index > nodes.length) {
            return null;
        }

        if (nodes[index] == null) {
            return null;
        }

        for (Node next = nodes[index];next != null; next = next.getNext()){
            if (key.equals(next.getKey())) {
                if (next.getNext() == null && next.getPrev() == null) {
                    nodes[index] = null;
                }

                if (next.getPrev() != null && next.getNext() != null) {
                    next.getPrev().setNext(next.getNext());

                    next.getNext().setPrev(next.getPrev());
                }

                if (next.getNext() != null && next.getPrev() == null) {
                    next.getNext().setPrev(null);

                    nodes[index] = next.getNext();

                    next.setNext(null);
                }

                if (next.getNext() == null && next.getPrev() != null) {
                    next.getPrev().setNext(null);

                    next.setPrev(null);

                }

                keySet.remove(key);

                return next.getValue();
            }
        }

        return null;
    }


    /**
     * puts all values from m into the display
     * @param m parameter from which all values are moved
     */
   @Override
   public void putAll(Map<? extends K, ? extends Integer> m) {
           for (Map.Entry entry: m.entrySet()) {
               if (entry == null){
                   continue;
               }
           put((K) entry.getKey(), (Integer) entry.getValue());
       }
   }


    /**
     * removes all items
     */
    @Override
    public void clear() {
        nodes = new Node[INITIAL_CAPACITY];

        keySet.clear();
    }

    /**
     *
     * @return list of keys
     */
    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keySet);
    }

    /**
     *
     * @return list of values
     */
    @Override
    public Collection<Integer> values() {
        List<Integer> result = new ArrayList<>();

        for (Node node: nodes) {
            if (node == null){
                continue;
            }
            result.add(node.getValue());

            for (Node next = node.getNext(); next != null; next = next.getNext()) {
                result.add(next.getValue());
            }
        }

        return result;
    }

    @Override
    public Set<Entry<K, Integer>> entrySet() {
        Set result = new HashSet();

        for (Node node: nodes) {
            if (node == null){
                continue;
            }
            result.add(node);

            for (Node next = node.getNext(); next != null; next = next.getNext()) {
                result.add(next);
            }
        }

        return result;
    }



    private static class Node<K> implements Map.Entry<K, Integer> {
        private K key;
        private Integer value;
        private Node<K> next;
        private Node<K> prev;

        private Node(K key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public Integer setValue(Integer value) {
            this.value = value;

            return value;
        }

        private Node<K> getNext() {
            return next;
        }

        private void setNext(Node<K> next) {
            this.next = next;
        }

        private Node<K> getPrev() {
            return prev;
        }

        private void setPrev(Node<K> prev) {
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next) &&
                    Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, next, prev);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleHashTable<?> hashTable = (SimpleHashTable<?>) o;
        return INITIAL_CAPACITY == hashTable.INITIAL_CAPACITY &&
                Arrays.equals(nodes, hashTable.nodes) &&
                Objects.equals(keySet, hashTable.keySet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(INITIAL_CAPACITY, keySet);
        result = 31 * result + Arrays.hashCode(nodes);
        return result;
    }

    private int hash(Object o) {
        return 31 ^ o.hashCode();
    }

    private int getIndex (Object key) {
        return hash(key) & (nodes.length - 1);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleHashTable{\n");

        for (Node node: nodes) {
            if (node == null) {
                continue;
            }

            for (Node next = node; next != null; next = next.getNext()) {
                sb.append("Key: ");
                sb.append(next.getKey());
                sb.append(" Value: ");
                sb.append(next.getValue());
                sb.append("\n");
            }
        }

        sb.append("}");

        return sb.toString();
    }
}
