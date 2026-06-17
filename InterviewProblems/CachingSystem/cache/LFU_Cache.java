package InterviewProblems.CachingSystem.cache;

import java.util.HashMap;

import InterviewProblems.CachingSystem.nodes.LFU_Node;

public class LFU_Cache implements Cache {
    private int capacity;
    private HashMap<Integer, LFU_Node> cache;
    private HashMap<Integer, DLL> frequencyMap;
    private int minFrequency;

    public LFU_Cache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public void insert(int key, int value) {
        // check if it already exists
        if(cache.containsKey(key)) {
            LFU_Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
            System.out.println("Updated node with key: " + key + " and value: " + value);
            return;
        }

        // evict the node if capacity is full
        if(cache.size() >= this.capacity) {
            DLL dll = frequencyMap.get(minFrequency);
            LFU_Node lfu = (LFU_Node) dll.left.next;
            dll.remove(lfu);
            cache.remove(lfu.key);
            System.out.println("Evicted LFU key: " + lfu.key);
        }

        // Add a anew node in both hashmap and update minFreq
        LFU_Node node = new LFU_Node(key, value, 1);
        cache.put(key,node);
        minFrequency = 1;
        frequencyMap.putIfAbsent(1, new DLL());
        frequencyMap.get(1).add(node);
        System.out.println("Added a new node with key: " + key + " and value: " + value);
    }

    public int get(int key) {
        // if not present
        if(!cache.containsKey(key)) {
            System.out.println("Get(" + key + ") -> There is no such key present");
            return -1;
        }

        // if present
        LFU_Node node = cache.get(key);
        updateFrequency(node);
        System.out.println("Get(" + key + ") -> Returned " + node.value);
        return node.value;
    }

    public void print() {
        for(int freq: frequencyMap.keySet()) {
            DLL dll = frequencyMap.get(freq);
            if(dll.listSize > 0) {
                System.out.print("Frequency: " + freq + " -> ");
                LFU_Node curr = (LFU_Node) dll.left.next;
                while (curr != dll.right) {
                    System.out.print("(" + curr.key + "," + curr.value + ") ");
                    curr = (LFU_Node) curr.next;
                }
                System.out.println();
            }
        }
    }

    private void updateFrequency(LFU_Node node) {
        int currentFrequency = node.frequency;
        DLL dll = frequencyMap.get(currentFrequency);
        dll.remove(node);

        if(dll.listSize == 0 && currentFrequency == minFrequency) {
            minFrequency++;
        }

        node.frequency++;
        frequencyMap.putIfAbsent(node.frequency, new DLL());
        frequencyMap.get(node.frequency).add(node);
    }

    private class DLL {
        public LFU_Node left;
        public LFU_Node right;
        public int listSize;

        public DLL() {
            left = new LFU_Node(-1, -1, -1);
            right = new LFU_Node(-1, -1, -1);
            left.next = right;
            right.prev = left;
            listSize = 0;
        }

        public void add(LFU_Node node) {
            LFU_Node Prev = (LFU_Node) right.prev;
            Prev.next = node;
            node.next = right;
            right.prev = node;
            node.prev = Prev;
            listSize++;
        }

        public void remove(LFU_Node node) {
            LFU_Node Prev = (LFU_Node) node.prev;
            LFU_Node Next = (LFU_Node) node.next;
            Prev.next = Next;
            Next.prev = Prev;
            listSize--;
        }
    }
}
