/*
LeetCode Problem: https://leetcode.com/problems/insert-delete-getrandom-o1/

Question: 380. Insert Delete GetRandom O(1)

Problem Statement: Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.

Example 1:
Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

Constraints:

-2^31 <= val <= 2^31 - 1
At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
 */

/*
Approach:
1. The RandomizedSet must support three operations in average O(1) time:
      • insert(val)  → insert the value if it does not already exist
      • remove(val)  → remove the value if it exists
      • getRandom()  → return a random element from the set

2. To achieve constant-time operations, we use:
      • A HashMap to store each value and its index inside the ArrayList.
      • An ArrayList to store all values for O(1) random access.

3. For insert(val):
      - If the value already exists in the HashMap, return false.
      - Otherwise, add it to the end of the ArrayList.
      - Store its index in the HashMap.

4. For remove(val):
      - If the value does not exist, return false.
      - Otherwise:
            • Find its index from the HashMap.
            • Replace this index with the last element in the list.
            • Update the HashMap index for the last element.
            • Remove the last element from the list.
            • Remove val from the HashMap.
        This ensures O(1) removal without shifting elements.

5. For getRandom():
      - Generate a random index in the range [0, list.size()).
      - Return the value at that index.
      - Since elements are stored densely, random selection is uniform.

Time Complexity: O(1) for insert, remove, and getRandom.
Space Complexity: O(n) where n is the number of inserted elements.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class _380_Insert_Delete_GetRandom_O1 {
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    // Class to make the RandomizedSet
    private static class RandomizedSet {
        // Initialize the HashMap to store the value and its index in the list
        private final HashMap<Integer, Integer> hashMap;

        // Initialize the ArrayList for the getRandom function
        private final ArrayList<Integer> list;

        public RandomizedSet() {
            // Initialize the HashMap
            hashMap = new HashMap<>();

            // Initialize the ArrayList
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            // If val is already present then return false
            if (hashMap.containsKey(val)) {
                return false;
            }

            // Add the value to the end of the list
            list.add(val);

            // Store the index of the value in the HashMap
            hashMap.put(val, list.size() - 1);

            // Return true
            return true;
        }

        public boolean remove(int val) {
            // If val is not present then return false
            if (!hashMap.containsKey(val)) {
                return false;
            }

            // Get the index of the value to remove
            int index = hashMap.get(val);

            // Get the last value from the list
            int lastValue = list.getLast();

            // Move the last value to the position of the value to remove
            list.set(index, lastValue);

            // Update the index of the last value in the HashMap
            hashMap.put(lastValue, index);

            // Remove the last element from the list
            list.removeLast();

            // Remove the val from the HashMap
            hashMap.remove(val);

            // Return true
            return true;
        }

        public int getRandom() {
            // Generate a random index in the range from 0 to list size
            int index = ThreadLocalRandom.current().nextInt(0, list.size());

            // Return the value at the random index
            return list.get(index);
        }
    }

    // Main method to test RandomizedSet
    public static void main(String[] args) {

        String[] operations = {
                "RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"
        };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] {});
        values.add(new int[] { 1 });
        values.add(new int[] { 2 });
        values.add(new int[] { 2 });
        values.add(new int[] {});
        values.add(new int[] { 1 });
        values.add(new int[] { 2 });
        values.add(new int[] {});

        // Create an instance of RandomizedSet
        RandomizedSet randomizedSet = new RandomizedSet();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("RandomizedSet")) {
                randomizedSet = new RandomizedSet();
                System.out.println("null");
            }
            if (operation.equals("insert")) {
                System.out.println(randomizedSet.insert(values.get(i)[0]));
            }
            if (operation.equals("remove")) {
                System.out.println(randomizedSet.remove(values.get(i)[0]));
            }
            if (operation.equals("getRandom")) {
                System.out.println(randomizedSet.getRandom());
            }
        }
    }
}
