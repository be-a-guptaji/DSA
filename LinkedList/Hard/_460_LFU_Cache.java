/*
LeetCode Problem: https://leetcode.com/problems/lfu-cache/

Question: 460. LFU Cache

Problem Statement: Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

    LFUCache(int capacity) Initializes the object with the capacity of the data structure.
    int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.

To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3

Constraints:
    1 <= capacity <= 10^4
    0 <= key <= 10^5
    0 <= value <= 10^9
    At most 2 * 10^5 calls will be made to get and put.
*/

/*
Approach: Hash Map + Frequency Lists (LFU Cache)

Goal:
- Design a cache supporting:
    get(key)
    put(key, value)
- When the cache reaches capacity, remove the
  Least Frequently Used (LFU) key.
- If multiple keys have the same minimum frequency,
  remove the Least Recently Used (LRU) among them.

Core Idea:
- Each cache entry stores:
    - key
    - value
    - frequency
- Nodes having the same frequency are grouped in a
  doubly linked list.
- A key map provides direct access to nodes.
- A frequency map stores the doubly linked list
  corresponding to each frequency.
- A minimumFrequency variable tracks the lowest
  frequency currently present in the cache.

Data Structures:
1. ListNode
   - key
   - value
   - frequency
   - previous
   - next

2. DoublyLinkedList
   - Stores all nodes having the same frequency.
   - New or recently accessed nodes are inserted
     at the head.
   - The least recently used node is located just
     before the tail.

3. keyMap
   - Maps a key directly to its corresponding node
     for O(1) lookup.

4. frequencyMap
   - Maps a frequency to its corresponding doubly
     linked list.

Algorithm for get(key):
1. Look up the node in keyMap.
2. If the key does not exist, return -1.
3. Remove the node from its current frequency list.
4. Increment its frequency.
5. Insert it into the new frequency list.
6. If the previous minimum frequency list becomes
   empty, increment minimumFrequency.
7. Return the node's value.

Algorithm for put(key, value):
1. If the key already exists:
   - Update its value.
   - Increase its frequency exactly as in get().
2. Otherwise:
   - If the cache is full:
     - Remove the least recently used node from
       the minimumFrequency list.
     - Remove its key from keyMap.
   - Create a new node with frequency 0.
   - Insert it into the frequency-0 list.
   - Add it to keyMap.
   - Reset minimumFrequency to 0.

Why It Works:
- keyMap provides O(1) access to any cache entry.
- frequencyMap groups nodes by access frequency.
- The doubly linked list maintains LRU order among
  nodes having the same frequency.
- minimumFrequency always identifies the frequency
  from which eviction must occur.
- Every insertion, deletion, and frequency update
  only modifies pointers and hash table entries,
  resulting in constant-time operations.

Time Complexity:
- get(): O(1)
- put(): O(1)

Space Complexity:
- O(capacity)

Result:
- Implements an LFU Cache supporting O(1) average
  time complexity for both get() and put()
  operations while correctly handling LFU and LRU
  eviction policies.
*/

package LinkedList.Hard;

import java.util.ArrayList;

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// LFUCache Class
class LFUCache {
  // ListNode Class
  private final class ListNode {
    // Initialize the values for the ListNode
    private final int key;
    private int value;
    private int frequency;
    private ListNode previous;
    private ListNode next;

    public ListNode(int key) {
      this.key = key;
      this.frequency = 0;
    }
  }

  // DoublyLinkedList Class
  private final class DoublyLinkedList {
    // Initialize the values for the DoublyLinkedList
    private final ListNode head;
    private final ListNode tail;

    public DoublyLinkedList() {
      this.head = new ListNode(-1);
      this.tail = new ListNode(-1);

      // Link the head and tail
      this.head.next = this.tail;
      this.tail.previous = this.head;
    }

    // Helper method to add the node from the head of the linked list
    private void addNode(ListNode node) {
      // Update the nodes
      this.head.next.previous = node;
      node.next = this.head.next;
      node.previous = this.head;
      this.head.next = node;
    }

    // Helper method to remove the node from the tail of the linked list
    private void removeNode() {
      // Update the nodes
      this.tail.previous.previous.next = this.tail;
      this.tail.previous = this.tail.previous.previous;
    }

    // Helper method to remove the node from the middle of the linked list
    private void removeNode(ListNode node) {
      // Update the nodes
      node.previous.next = node.next;
      node.next.previous = node.previous;
    }
  }

  // Initialize the capacity variable
  private int capacity;

  // Initialize the minimum frequency variable
  private int minimumFrequency;

  // Initialize the key map
  private final ListNode[] keyMap;

  // Initialize the frequency map
  private final DoublyLinkedList[] frequencyMap;

  public LFUCache(int capacity) {
    // Initialize the capacity variable
    this.capacity = capacity;

    // Initialize the minimum frequency variable
    this.minimumFrequency = 0;

    // Initialize the key map
    this.keyMap = new ListNode[100001];

    // Initialize the frequency map
    this.frequencyMap = new DoublyLinkedList[200001];
  }

  public int get(int key) {
    // Get the node from the key map
    ListNode node = this.keyMap[key];

    // If node is null then return the -1
    if (node == null) {
      return -1;
    }

    // Remove the value to from the frequency map
    this.frequencyMap[node.frequency].removeNode(node);

    // Increment the frequency of the node
    node.frequency++;

    // If frequency map does not that frequency then add one to the map
    if (this.frequencyMap[node.frequency] == null) {
      this.frequencyMap[node.frequency] = new DoublyLinkedList();
    }

    // Add the value to the DoublyLinkedList
    this.frequencyMap[node.frequency].addNode(node);

    // Get the doubly linked list for the of the minimum frequency
    DoublyLinkedList doublyLinkedList = this.frequencyMap[this.minimumFrequency];

    // If head if points to the tail then increment then minimum frequency
    if (doublyLinkedList.head.next == doublyLinkedList.tail) {
      this.minimumFrequency++;
    }

    // Return the node.value
    return node.value;
  }

  public void put(int key, int value) {
    // Get the node from the key map
    ListNode node = this.keyMap[key];

    // If node is null then add the linked list
    if (node == null) {
      // Initialize the node value
      node = new ListNode(key);

      // If capacity is greater than zero then add the value to the frequency map
      if (this.capacity > 0) {
        // If frequency map does not that frequency then add one to the map
        if (this.frequencyMap[node.frequency] == null) {
          this.frequencyMap[node.frequency] = new DoublyLinkedList();
        }

        // Add the value to the DoublyLinkedList
        this.frequencyMap[0].addNode(node);

        // Decrement the capacity variable
        this.capacity--;
      } else {
        // Get the doubly linked list for the of the minimum frequency
        DoublyLinkedList doublyLinkedList = this.frequencyMap[this.minimumFrequency];

        // Remove the value from the key map
        this.keyMap[doublyLinkedList.tail.previous.key] = null;

        // Remove the value from the doubly linked list
        doublyLinkedList.removeNode();

        // Add the value to the DoublyLinkedList
        this.frequencyMap[0].addNode(node);
      }

      // Add the value to the key map
      this.keyMap[key] = node;

      // Reset the value of the minimum frequency variable
      this.minimumFrequency = 0;
    } else {
      // Remove the node from the frequency map
      DoublyLinkedList doublyLinkedList = this.frequencyMap[node.frequency];

      // Remove the node from the linked list
      doublyLinkedList.removeNode(node);

      // Increment the frequency of the node
      node.frequency++;

      // If frequency map does not that frequency then add one to the map
      if (this.frequencyMap[node.frequency] == null) {
        this.frequencyMap[node.frequency] = new DoublyLinkedList();
      }

      // Add the value to the DoublyLinkedList
      this.frequencyMap[node.frequency].addNode(node);

      // Get the doubly linked list for the of the minimum frequency
      doublyLinkedList = this.frequencyMap[this.minimumFrequency];

      // If head if points to the tail then increment then minimum frequency
      if (doublyLinkedList.head.next == doublyLinkedList.tail) {
        this.minimumFrequency++;
      }
    }

    // Update the node value
    node.value = value;
  }
}

// Main Class
public class _460_LFU_Cache {
  // Main method to test LFUCache
  public static void main(String[] args) {
    String[] operations = new String[] { "LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
        "get" };

    ArrayList<int[]> values = new ArrayList<>();

    values.add(new int[] { 2 });
    values.add(new int[] { 1, 1 });
    values.add(new int[] { 2, 2 });
    values.add(new int[] { 1 });
    values.add(new int[] { 3, 3 });
    values.add(new int[] { 2 });
    values.add(new int[] { 3 });
    values.add(new int[] { 4, 4 });
    values.add(new int[] { 1 });
    values.add(new int[] { 3 });
    values.add(new int[] { 4 });

    LFUCache lfuCache = new LFUCache(0);

    for (int i = 0; i < operations.length; i++) {
      switch (operations[i]) {
        case "LFUCache" -> {
          lfuCache = new LFUCache(values.get(i)[0]);
          System.out.println("null");
        }
        case "put" -> {
          lfuCache.put(values.get(i)[0], values.get(i)[1]);
          System.out.println("null");
        }
        case "get" -> {
          System.out.println(lfuCache.get(values.get(i)[0]));
        }
      }
    }
  }
}
