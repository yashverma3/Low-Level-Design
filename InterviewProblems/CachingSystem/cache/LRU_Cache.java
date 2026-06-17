package InterviewProblems.CachingSystem.cache;

import java.util.HashMap;

import InterviewProblems.CachingSystem.nodes.LRU_Node;

public class LRU_Cache implements Cache {
    private int capacity;
    private LRU_Node left;
    private LRU_Node right;
    private HashMap<Integer, LRU_Node> cache;

    public LRU_Cache(int capacity) {
        this.capacity = capacity;
        left = new LRU_Node(-1, -1);
        right = new LRU_Node(-1, -1);
        left.next = right;
        right.prev = left;
        cache = new HashMap<>();
    }

    public void insert(int key, int value) {
        if(cache.containsKey(key)) {
            remove(cache.get(key));
        }

        LRU_Node node = new LRU_Node(key, value);
        cache.put(key, node);
        add(node);

        if(cache.size() > this.capacity) {
            LRU_Node lru = (LRU_Node) left.next;
            cache.remove(lru.key);
            remove(lru);
            System.out.println("Evicted least recently used key: " + lru.key);
        }

        System.out.println("Added a new node with key: " + key + " and value: " + value);
    }
    
    public int get(int key) {
        if(!cache.containsKey(key)) {
            System.out.println("Get(" + key + ") -> There is no such key present");
            return -1;
        }

        LRU_Node node = cache.get(key);
        int value = node.value;

        remove(node);
        add(node);

        System.out.println("Get(" + key + ") -> Returned " + value);
        return value;
    }

    private void add(LRU_Node node) {
        LRU_Node Prev = (LRU_Node) right.prev;
        Prev.next = node;
        node.next = right;
        right.prev = node;
        node.prev = Prev;
    }

    private void remove(LRU_Node node) {
        LRU_Node Next = (LRU_Node) node.next;
        LRU_Node Prev = (LRU_Node) node.prev;
        Prev.next = Next;
        Next.prev = Prev;
    }

    public void print() {
        LRU_Node node = (LRU_Node) left.next;
        while(node!=right) {
            System.out.print("(" + node.key + "," + node.value + ") ");
            node = (LRU_Node) node.next;
        }
        System.out.println();
    }
}
