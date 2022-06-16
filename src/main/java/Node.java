import java.util.LinkedList;
import java.util.List;

public class Node<K, V> {
    final K key;
    V value;
    private List<Node<K,V>> nodes;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.nodes = new LinkedList<>();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public List<Node<K, V>> getNodes() {
        return nodes;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
