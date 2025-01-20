package searching;

import java.util.HashMap;

public class LRUCache<K, V> {
    private final int capacity;
    private final HashMap<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null; // Clé absente
        }

        // Déplacer le nœud correspondant à la fin de la liste
        Node<K, V> node = map.get(key);
        list.moveToEnd(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            // Met à jour la valeur et déplace le nœud à la fin de la liste
            Node<K, V> node = map.get(key);
            node.value = value;
            list.moveToEnd(node);
        } else {
            // Si la capacité est atteinte, supprime le LRU
            if (map.size() >= capacity) {
                Node<K, V> lru = list.removeFirst();
                if (lru != null) {
                    map.remove(lru.key);
                }
            }

            // Ajoute un nouveau nœud
            Node<K, V> newNode = new Node<>(key, value);
            list.addLast(newNode);
            map.put(key, newNode);
        }
    }

    // Classe représentant un nœud dans la liste
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Liste doublement chaînée pour gérer l'ordre des éléments
    private static class DoublyLinkedList<K, V> {
        private Node<K, V> head, tail;

        DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        void addLast(Node<K, V> node) {
            if (tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        void moveToEnd(Node<K, V> node) {
            if (node == tail) return;

            if (node == head) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = tail;
            node.next = null;
            if (tail != null) {
                tail.next = node;
            }
            tail = node;

            if (head == null) {
                head = tail;
            }
        }

        Node<K, V> removeFirst() {
            if (head == null) return null;

            Node<K, V> node = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return node;
        }
    }
}
