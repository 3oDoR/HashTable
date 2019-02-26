package Main;

import java.util.*;


@SuppressWarnings("unchecked")
public class SimpleHashTable<K> implements Map<K, Integer> {
    private List<Node> nodes = new ArrayList<>();
    private List<K> keySet = new ArrayList<K>();
    private int size = 0;


    /**
     * @return the size of the hashtable
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @return true if the hash table is empty otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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

        for (Node node : nodes) {
            if (hash(node.getKey()) != hash(key)) {
                continue;
            }

            for (Node next = node; next != null; next = next.getNext()) {
                if (key.equals(next.getKey())) {
                    return next.getValue();
                }
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

        for (Node node : nodes) {
            if (hash(node.getKey()) != hash(key)) {
                continue;
            }

            for (Node next = node; next != null; next = next.getNext()) {
                if (key.equals(next.getKey())) {
                    next.setValue(value);

                    return value;
                } else if (next.getNext() == null) {
                    next.setNext(new Node(key, value));

                    keySet.add(key);

                    size++;

                    return value;
                }
            }
        }

        nodes.add(new Node(key, value));

        keySet.add(key);

        size++;

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

        for (Node node : nodes) {
            if (hash(node.getKey()) != hash(key)) {
                continue;
            }

            for (Node next = node; next != null; next = next.getNext()) {
                if (key.equals(next.getKey())) {
                    if (next.getNext() != null) {
                        if (next.getPrev() != null) {
                            Node swap = next.getPrev();

                            swap.setNext(next.getNext());

                            keySet.remove((K) key);

                            size--;

                            return next.getValue();
                        }


                        node = next.getNext();

                        node.setPrev(null);

                        keySet.remove((K) key);

                        size --;

                        return node.getValue();

                    }

                    nodes.remove(next);

                    keySet.remove((K) key);

                    size--;

                    return next.getValue();
                }
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
            put((K) entry.getKey(), (Integer) entry.getValue());
        }
    }


    /**
     * removes all items
     */
    @Override
    public void clear() {
        nodes = new ArrayList<>();

        size = 0;
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
        List<Integer> result = new ArrayList<>(size);

        for (Node node: nodes) {
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
            return key.equals(node.key) && value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, next, prev);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleHashTable<?> hashTable = (SimpleHashTable<?>) o;
        return size == hashTable.size && Objects.equals(nodes, hashTable.nodes) && Objects.equals(keySet, hashTable.keySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, keySet, size);
    }

    private int hash(Object o) {
        return 31 ^ o.hashCode();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleHashTable{\n");

        for (Node node: nodes) {
            sb.append("Key: ");
            sb.append(node.getKey());
            sb.append(" Value: ");
            sb.append(node.getValue());
            sb.append("\n");
        }

        sb.append("}");

        return sb.toString();
    }
}
