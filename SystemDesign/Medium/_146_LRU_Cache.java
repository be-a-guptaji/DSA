/*
LeetCode Problem: https://leetcode.com/problems/lru-cache/

Question: 146. LRU Cache

Problem Statement: Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:

1 <= capacity <= 3000
0 <= key <= 10^4
0 <= value <= 10^5
At most 2 * 10^5 calls will be made to get and put.
 */

/*
Approach:
1. The LRUCache needs to support two operations in O(1) time:
      • get(key)  → return the value and mark the key as recently used
      • put(key, value) → insert/update a key and maintain the LRU order

2. To achieve this, we combine:
      • A HashMap → for O(1) access to nodes using the key
      • A Doubly Linked List → to track the usage order of keys
        - Most recently used (MRU) key is placed at the tail
        - Least recently used (LRU) key stays at head.next

3. For every get(key):
      - If key is not present, return -1
      - If key exists:
           → remove the node from its current position
           → move it to the tail (mark as most recently used)
           → return its value

4. For every put(key, value):
      - If key already exists:
           → update its value
           → remove it from the list and move it to the tail
      - If key does NOT exist:
           → If cache is full:
                * remove the least recently used node (head.next)
                * remove it from the HashMap
           → create a new node
           → insert it at the tail
           → add it to the HashMap

5. The doubly linked list uses two dummy nodes:
      • head  → marks the start (LRU side)
      • tail  → marks the end (MRU side)
   These dummy nodes simplify insertions and deletions.

6. Removing a node or adding it to the tail is O(1)
   because we adjust only two pointers:
       prev.next = next
       next.prev = prev
   and similarly when inserting at the tail.

Time Complexity:
   • get()  → O(1)
   • put()  → O(1)
   Both operations use direct HashMap lookup and constant-time linked list updates.
Space Complexity:
   • O(capacity) for storing key-node pairs in the HashMap and linked list.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _146_LRU_Cache {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    // Class to make the LRUCache
    private static class LRUCache {

        // Initialize the storage capacity
        private final int storage;

        // HashMap to store key -> Node mappings
        private final HashMap<Integer, Node> hashMap;

        // Head and tail pointer for the doubly linked list
        private final Node head, tail;

        public LRUCache(int capacity) {
            // Set the storage capacity
            storage = capacity;

            // Initialize the hashMap
            hashMap = new HashMap<>();

            // Initialize the dummy head and tail node
            head = new Node(0, 0);
            tail = new Node(0, 0);

            // Connect head and tail
            head.nextNode = tail;
            tail.previousNode = head;
        }

        public int get(int key) {
            // If key is not found return -1
            if (!hashMap.containsKey(key)) {
                return -1;
            }

            // Get the node
            Node node = hashMap.get(key);

            // Move the node to the tail (most recently used)
            removeNode(node);
            addToTail(node);

            // Return the value
            return node.value;
        }

        public void put(int key, int value) {
            // If key already exists update it and move to tail
            if (hashMap.containsKey(key)) {
                Node node = hashMap.get(key);

                // Update value
                node.value = value;

                // Move to tail
                removeNode(node);
                addToTail(node);
                return;
            }

            // If capacity is full remove least recently used node (head.next)
            if (hashMap.size() == storage) {
                Node lru = head.nextNode;

                // Remove from linked list
                removeNode(lru);

                // Remove from hashmap
                hashMap.remove(lru.key);
            }

            // Create a new node and add it to tail
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode);
            addToTail(newNode);
        }

        // Helper method to remove a node from the doubly linked list
        private void removeNode(Node node) {
            Node prev = node.previousNode;
            Node next = node.nextNode;

            prev.nextNode = next;
            next.previousNode = prev;
        }

        // Helper method to add a node to the tail of the doubly linked list
        private void addToTail(Node node) {
            Node prev = tail.previousNode;

            prev.nextNode = node;
            node.previousNode = prev;

            node.nextNode = tail;
            tail.previousNode = node;
        }

        // Node class for the doubly linked list
        private class Node {
            private int key, value;
            private Node nextNode, previousNode;

            public Node(int k, int v) {
                key = k;
                value = v;
            }
        }
    }

    // Main method to test LRUCache
    public static void main(String[] args) {

        String[] operations = {
                "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"
        };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] { 2 }); // LRUCache(2)
        values.add(new int[] { 1, 1 }); // put(1,1)
        values.add(new int[] { 2, 2 }); // put(2,2)
        values.add(new int[] { 1 }); // get(1)
        values.add(new int[] { 3, 3 }); // put(3,3)
        values.add(new int[] { 2 }); // get(2)
        values.add(new int[] { 4, 4 }); // put(4,4)
        values.add(new int[] { 1 }); // get(1)
        values.add(new int[] { 3 }); // get(3)
        values.add(new int[] { 4 }); // get(4)

        // Create an instance of LRUCache
        LRUCache lru = new LRUCache(0);

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("LRUCache")) {
                // Create the new LRUCache instance
                lru = new LRUCache(values.get(i)[0]);
                System.out.println("null");
            }
            if (operation.equals("put")) {
                // Call the put method with key and value
                lru.put(values.get(i)[0], values.get(i)[1]);
                System.out.println("null");
            }
            if (operation.equals("get")) {
                // Call the get method with key and print the result
                System.out.println(lru.get(values.get(i)[0]));
            }
        }
    }
}
