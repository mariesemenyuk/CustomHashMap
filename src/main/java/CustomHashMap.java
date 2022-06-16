import java.util.*;

/**
 * Custom implementation for HashMap.
 * Methods implemented: put, get, containsKey, remove
 * @param <K> - key type
 * @param <V> - value type
 */
public class CustomHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;

    private Node<K, V>[] table;
    private int size = 0;
    private float threshold;

    CustomHashMap() {
        this.table =  new Node[INITIAL_CAPACITY];
        this.threshold = this.table.length * 0.75f;
    }

    /**
     * Checks if map contains key
     * @param key
     * @return boolean type
     */
    public boolean containsKey(Object key) {
        int index = key.hashCode() % this.table.length;
        if(table[index] == null) return false;
        else {
            List<Node<K, V>> nodes = table[index].getNodes();
            for (Node<K, V> node: nodes) {
                if(key.equals(node.getKey())) return true;
            }
        }

        return false;
    }

    /**
     * Returns value based on key value
     * @param key
     * @return
     */
    public V get(Object key) {
        int index = key.hashCode() % this.table.length;
        if(table[index] == null) return null;
        else {
            List<Node<K, V>> nodes = table[index].getNodes();
            for (Node<K, V> node: nodes) {
                if(key.equals(node.getKey())) return node.getValue();
            }
        }
        return null;
    }

    /**
     * Adds pair key-value to map
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        if(size + 1 >= threshold) {
            doubleSizeTable();
        }
        Node<K, V> newNode = new Node<>(key, value);
        if(key == null) {
            if(this.table[0] == null) {
                addNode(newNode, 0);
            } else {
                List<Node<K, V>> nodes = this.table[0].getNodes();
                for (Node<K, V> node: nodes) {
                    if (node.getKey() == null &&
                            !node.getValue().equals(newNode.getValue())) {
                        node.setValue(value);
                    } else {
                        addNode(newNode, 0);
                    }
                }
            }
            return value;
        }

        int index = key.hashCode() % this.table.length;

        if(this.table[index] == null) {
            addNode(newNode, index);
        } else {
            List<Node<K, V>> nodesCopy = new LinkedList<>(this.table[index].getNodes());

            for (Node<K, V> node: nodesCopy) {
                if(node.getKey().equals(newNode.getKey()) &&
                !node.getValue().equals(newNode.getValue())) {
                    node.setValue(value);
                    return value;
                }
                if(checkForCollision(node,newNode)) return value;
            }

            this.table[index].getNodes().add(newNode);
            this.size++;
        }
        return value;
    }

    private void doubleSizeTable() {
        threshold *= 2;

        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;
        for (Node<K, V> node: oldTable) {
            if(node != null) {
                for(Node<K, V> n : node.getNodes()) {
                    put(n.key, n.value);
                }
            }
        }
    }

    private boolean checkForCollision(Node<K, V> node, Node<K, V> newNode) {
       if (node.getKey().equals(newNode.getKey()) &&
                node.getValue().equals(newNode.getValue())) {
            return true;
        }
        return false;
    }

    private void addNode (Node<K, V> newNode, int index) {
        this.table[index] = newNode;
        this.table[index].getNodes().add(newNode);
        this.size++;
    }

    /**
     * Removes pair kwy-value from map
     * @param key
     * @param value
     * @return
     */
    public boolean remove(Object key, Object value) {
        int index = key.hashCode() % this.table.length;
        if(table[index] == null) return false;
        else {
            List<Node<K, V>> nodes = table[index].getNodes();
            if(nodes.size() == 1 &&
                    nodes.get(0).getKey().equals(key) &&
                    nodes.get(0).getValue().equals(value)
            ) {
                table[index] = null;
                size--;
            } else {
                for (Node<K, V> node: nodes) {
                    if(key.equals(node.getKey()) &&
                            value.equals(node.getValue())) {
                        size -= nodes.size();
                        table[index] = null;
                        nodes.remove(node);
                        for (Node<K,V> nodeInner: nodes) {
                            put(nodeInner.key, nodeInner.value);
                        }
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
