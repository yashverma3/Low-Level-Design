package InterviewProblems.CachingSystem;

import InterviewProblems.CachingSystem.cache.LFU_Cache;
import InterviewProblems.CachingSystem.cache.LRU_Cache;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INITIALIZING LRU CACHE (Capacity: 2) ---");
        LRU_Cache cache = new LRU_Cache(2);

        System.out.println("\n--- TEST 1: Basic Insertions ---");
        cache.insert(1, 10);
        cache.insert(2, 20);
        cache.print();
        
        System.out.println("\n--- TEST 2: Basic Get ---");
        System.out.println(cache.get(1));
        cache.print();

        System.out.println("\n--- TEST 3: Eviction Trigger ---");
        cache.insert(3, 30);
        cache.print();
        
        System.out.println("\n--- TEST 4: Verify Eviction ---");
        System.out.println(cache.get(2));
        cache.print();

        System.out.println("\n--- TEST 5: Update Existing Key ---");
        cache.insert(1, 100);
        System.out.println(cache.get(1));
        cache.print();

        System.out.println("\n--- TEST 6: Another Eviction ---");
        cache.insert(4, 40);
        System.out.println(cache.get(3));
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
        cache.print();
        
        System.out.println("\n--- ALL TESTS COMPLETE ---");



        System.out.println("--- INITIALIZING LFU CACHE (Capacity: 2) ---");
        LFU_Cache cache2 = new LFU_Cache(2);

        System.out.println("\n--- TEST 1: Basic Insertions ---");
        // Both keys start with frequency 1
        cache2.insert(1, 10); 
        cache2.insert(2, 20); 
        cache2.print();
        
        System.out.println("\n--- TEST 2: Basic Get (Increases Frequency) ---");
        // Getting key 1 pushes its frequency to 2
        System.out.println(cache2.get(1)); 
        cache2.print();

        System.out.println("\n--- TEST 3: LFU Eviction Trigger ---");
        // Capacity is 2. Key 1 has Freq=2, Key 2 has Freq=1. 
        // Inserting Key 3 MUST evict Key 2.
        cache2.insert(3, 30); 
        cache2.print();
        
        System.out.println("\n--- TEST 4: Verify Eviction ---");
        // Key 2 should be completely gone
        System.out.println(cache2.get(2)); // Expect: -1
        cache2.print();

        System.out.println("\n--- TEST 5: LRU Tie-Breaker Trigger ---");
        // Let's get key 3 so both key 1 and key 3 have Freq=2.
        System.out.println(cache2.get(3)); 
        cache2.print(); 
        
        // State: Key 1 (Freq=2, older), Key 3 (Freq=2, newer).
        // Inserting Key 4 should evict Key 1 because it is the Least Recently Used among Freq=2.
        cache2.insert(4, 40); 
        System.out.println(cache2.get(1)); // Expect: -1 (Evicted)
        System.out.println(cache2.get(3)); // Expect: 30 (Still here)
        System.out.println(cache2.get(4)); // Expect: 40 (New, Freq=1)
        cache2.print();

        System.out.println("\n--- TEST 6: Update Existing Key ---");
        // Updating key 4 should change its value from 40 to 400 AND increase its frequency to 2
        cache2.insert(4, 400); 
        System.out.println(cache2.get(4)); // Expect: 400
        cache2.print();
        
        System.out.println("\n--- ALL TESTS COMPLETE ---");
    }
}