/*
LeetCode Problem: https://leetcode.com/problems/design-hashmap/

Question: 706. Design HashMap

Problem Statement: Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.

Example 1:
Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]

Constraints:

0 <= key, value <= 10^6
At most 10^4 calls will be made to put, get, and remove.
 */

/*
Approach:
1. We implement a simple HashMap using a direct-address table because the key range
   is fixed between 0 and 1,000,000. This allows us to store values directly at
   their corresponding index.

2. We maintain an Integer array where:
      • memory[key] holds the value for that key
      • null indicates that the key does not have a stored value

3. For each operation:
   - put(key, value): store the value directly at memory[key].
   - get(key): return the stored value if it exists, otherwise return -1.
   - remove(key): remove the mapping by setting memory[key] to null.

4. This avoids hashing, collision handling, and bucket management since the key
   is directly used as the index.

Time Complexity: O(1) for put, get, and remove, because all operations use direct indexing.
Space Complexity: O(n) where n = 1,000,001 (fixed size array used to store values).
*/

package SystemDesign.Easy;

import java.util.ArrayList;
import java.util.List;

public class _706_Design_HashMap {
    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

    // Class to make the MyHashMap
    private static class MyHashMap {
        // Initialize the for storing the values
        private final Integer[] memory;

        public MyHashMap() {
            // Initialize the memory array of 1000001 size
            this.memory = new Integer[1000001];
        }

        public void put(int key, int value) {
            // Place the value at the key index
            memory[key] = value;
        }

        public int get(int key) {
            // Return the value at the key index
            return this.memory[key] == null ? -1 : this.memory[key];
        }

        public void remove(int key) {
            // Set the key value to -1
            this.memory[key] = null;
        }
    }

    // Main method to test MyHashMap
    public static void main(String[] args) {
        String[] operations = { "MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get" };
        List<int[]> values = new ArrayList<>();

        values.add(new int[] {});
        values.add(new int[] { 1, 1 });
        values.add(new int[] { 2, 2 });
        values.add(new int[] { 1 });
        values.add(new int[] { 3 });
        values.add(new int[] { 2, 1 });
        values.add(new int[] { 2 });
        values.add(new int[] { 2 });
        values.add(new int[] { 2 });

        // Create an instance of MyHashMap
        MyHashMap myHashMap = new MyHashMap();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MyHashMap")) {
                // Create the new MyHashMap instance
                myHashMap = new MyHashMap();
                System.out.println("null");
            }
            if (operation.equals("put")) {
                // Call the put method with values[i][0] as key and values[i][1] as value
                myHashMap.put(values.get(i)[0], values.get(i)[1]);
                System.out.println("null");
            }
            if (operation.equals("get")) {
                // Call the get method with values[i][0] as key and print the result
                System.out.println(myHashMap.get(values.get(i)[0]));
            }
            if (operation.equals("remove")) {
                // Call the remove method with values[i][0] as key
                myHashMap.remove(values.get(i)[0]);
                System.out.println("null");
            }
        }
    }
}
