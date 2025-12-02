/*
LeetCode Problem: https://leetcode.com/problems/all-oone-data-structure/

Question: 432. All O`one Data Structure

Problem Statement: Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

Example 1:
Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"

Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */

/*
Approach:

1. We need to design a data structure that supports the following operations in O(1) time:
   • inc(key)      → Increment the frequency of a key
   • dec(key)      → Decrement the frequency of a key
   • getMaxKey()   → Return any key with the maximum frequency
   • getMinKey()   → Return any key with the minimum frequency

2. To achieve O(1) time complexity, we use:
   • A HashMap<String, Node> to map each key to its corresponding frequency node.
   • A Doubly Linked List of Nodes where:
        - Each Node represents a frequency count.
        - Each Node stores a HashSet of keys with that frequency.
        - Nodes are sorted in ascending order of frequency.

3. Two dummy nodes are used:
   • head → Represents the lowest boundary (before minimum frequency).
   • tail → Represents the highest boundary (after maximum frequency).
   This avoids null checks while inserting and deleting nodes.

4. For inc(key):
   • If the key already exists:
        - Remove it from its current frequency node.
        - Move it to the next higher frequency node.
        - If that node doesn’t exist, create a new one.
        - If the old frequency node becomes empty, remove it.
   • If the key does not exist:
        - Insert it into frequency = 1 node.
        - If no frequency-1 node exists, create it right after head.

5. For dec(key):
   • Remove the key from its current frequency node.
   • If its frequency becomes 0:
        - Remove the key completely from the HashMap.
   • Otherwise:
        - Move it to the previous lower frequency node.
        - If that node doesn’t exist, create a new one.
   • If the old node becomes empty, remove it.

6. getMaxKey():
   • The maximum frequency key is always found in the node just before tail.
   • Return any one key from that node’s HashSet.

7. getMinKey():
   • The minimum frequency key is always found in the node just after head.
   • Return any one key from that node’s HashSet.

Time Complexity:
• inc():     O(1)
• dec():     O(1)
• getMaxKey(): O(1)
• getMinKey(): O(1)

Space Complexity:
• O(n), where n is the total number of unique keys stored.
*/

package SystemDesign.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class _432_All_O_one_Data_Structure {
    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */

    // Class to make the AllOne Data Structure
    private static class AllOne {
        // Initialize the HashMap for storing the key and its corresponding node
        private final HashMap<String, Node> frequency;

        // Initialize the pointer for head and tail of the doubly linked list
        private final Node head, tail;

        public AllOne() {
            // Set the HashMap for the String frequency
            this.frequency = new HashMap<>();

            // Set the pointer for head and tail of the doubly linked list
            this.head = new Node(Integer.MIN_VALUE, null, null);
            this.tail = new Node(Integer.MAX_VALUE, null, null);

            // Attach the head and tail of the doubly linked list
            this.head.nextNode = this.tail;
            this.tail.previousNode = this.head;
        }

        public void inc(String key) {
            // If key is already present, increase its frequency
            if (this.frequency.containsKey(key)) {
                // Get the node of the doubly linked list from the HashMap
                Node node = this.frequency.get(key);

                // Calculate the new frequency index
                int newIndex = node.index + 1;

                // Get the next node in the linked list
                Node next = node.nextNode;

                // Remove the key from the current node
                node.strings.remove(key);

                // If next node index is equal to newIndex then just add key to that node
                // else create a new node between node and next
                if (next == this.tail || next.index > newIndex) {
                    // Create a new node with newIndex between node and next
                    Node newNode = new Node(newIndex, next, node);

                    // Link the new node correctly
                    node.nextNode = newNode;
                    next.previousNode = newNode;

                    // Add the key to the new node
                    newNode.strings.add(key);

                    // Update the frequency HashMap
                    this.frequency.put(key, newNode);
                } else {
                    // Add the key directly to the next node
                    next.strings.add(key);

                    // Update the frequency HashMap
                    this.frequency.put(key, next);
                }

                // If current node has no keys left then remove the node from the doubly linked
                // list
                if (node.strings.isEmpty()) {
                    removeNode(node);
                }
            } else {
                // Key is new, so its frequency becomes 1
                int newIndex = 1;

                // Get the node after head
                Node next = this.head.nextNode;

                // If next node index is 1 then just add key to that node
                // else create a new node between head and next
                if (next != this.tail && next.index == newIndex) {
                    // Add key to the next node
                    next.strings.add(key);

                    // Update the frequency HashMap
                    this.frequency.put(key, next);
                } else {
                    // Create a new node with index 1 between head and next
                    Node newNode = new Node(newIndex, next, this.head);

                    // Link the new node correctly
                    this.head.nextNode = newNode;
                    next.previousNode = newNode;

                    // Add the key to the new node
                    newNode.strings.add(key);

                    // Update the frequency HashMap
                    this.frequency.put(key, newNode);
                }
            }
        }

        public void dec(String key) {
            // Get the node from the String frequency HashMap
            Node node = this.frequency.get(key);

            // Remove the key from the current node
            node.strings.remove(key);

            // If the node has index equal to one then remove the key entirely
            if (node.index == 1) {
                // Remove the entry from the String frequency HashMap
                this.frequency.remove(key);
            } else {
                // Calculate the new frequency index
                int newIndex = node.index - 1;

                // Get the previous node in the linked list
                Node prev = node.previousNode;

                // If prev node index is equal to newIndex then add key to that node
                // else create a new node between prev and node
                if (prev != this.head && prev.index == newIndex) {
                    // Add the key to the previous node
                    prev.strings.add(key);

                    // Update the String frequency HashMap
                    this.frequency.put(key, prev);
                } else {
                    // Create a new node with newIndex between prev and node
                    Node newNode = new Node(newIndex, node, prev);

                    // Link the new node correctly
                    prev.nextNode = newNode;
                    node.previousNode = newNode;

                    // Add the key to the new node
                    newNode.strings.add(key);

                    // Update the String frequency HashMap
                    this.frequency.put(key, newNode);
                }
            }

            // If the current node has no keys left then remove the node from the doubly
            // linked list
            if (node.strings.isEmpty()) {
                removeNode(node);
            }
        }

        public String getMaxKey() {
            // If there is no valid node between head and tail then return empty String
            if (this.tail.previousNode == this.head) {
                return "";
            }

            // Get the node previous to the tail node (this has maximum frequency)
            Node node = this.tail.previousNode;

            // Return any one String from the node HashSet
            // (All keys in this node have the same maximum frequency)
            for (String key : node.strings) {
                return key;
            }

            // If somehow there is no key, return empty String
            return "";
        }

        public String getMinKey() {
            // If there is no valid node between head and tail then return empty String
            if (this.head.nextNode == this.tail) {
                return "";
            }

            // Get the node next to the head node (this has minimum frequency)
            Node node = this.head.nextNode;

            // Return any one String from the node HashSet
            for (String key : node.strings) {
                return key;
            }

            // If somehow there is no key, return empty String
            return "";
        }

        // Helper method to remove a node from the doubly linked list
        private void removeNode(Node node) {
            // Connect the previous and next nodes directly
            Node prev = node.previousNode;
            Node next = node.nextNode;

            prev.nextNode = next;
            next.previousNode = prev;
        }

        // Helper class for the node of doubly linked list
        private class Node {
            // Initialize the pointer for next and previous node
            private Node nextNode, previousNode;

            // Initialize the index variable for the position (this represents the count)
            private final int index;

            // Initialize the HashSet for storing the Strings with this count
            private final HashSet<String> strings;

            // Constructor for initializing the node
            public Node(int idx, Node next, Node previous) {
                // Set the next and previous node
                this.nextNode = next;
                this.previousNode = previous;

                // Set the index variable
                this.index = idx;

                // Initialize the HashSet for storing the String keys
                this.strings = new HashSet<>();
            }
        }
    }

    // Main method to test AllOne
    public static void main(String[] args) {

        String[] operations = {
                "AllOne", "inc", "inc", "getMaxKey", "getMinKey",
                "inc", "getMaxKey", "getMinKey"
        };

        List<String[]> values = new ArrayList<>();

        values.add(new String[] {}); // AllOne()
        values.add(new String[] { "hello" }); // inc("hello")
        values.add(new String[] { "hello" }); // inc("hello")
        values.add(new String[] {}); // getMaxKey()
        values.add(new String[] {}); // getMinKey()
        values.add(new String[] { "leet" }); // inc("leet")
        values.add(new String[] {}); // getMaxKey()
        values.add(new String[] {}); // getMinKey()

        // Create the AllOne object
        AllOne allOne = new AllOne();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            switch (operation) {

                case "AllOne" -> {
                    allOne = new AllOne();
                    System.out.println("null");
                }

                case "inc" -> {
                    allOne.inc(values.get(i)[0]);
                    System.out.println("null");
                }

                case "getMaxKey" -> {
                    System.out.println(allOne.getMaxKey());
                }

                case "getMinKey" -> {
                    System.out.println(allOne.getMinKey());
                }
            }
        }
    }
}
