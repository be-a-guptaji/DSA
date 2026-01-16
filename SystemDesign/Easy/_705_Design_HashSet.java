/*
LeetCode Problem: https://leetcode.com/problems/design-hashset/

Question: 705. Design HashSet

Problem Statement: Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

Example 1:
Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)

Constraints:

0 <= key <= 10^6
At most 10^4 calls will be made to add, remove, and contains.
 */

/*
Approach: Bitset-Based HashSet (Direct Addressing)

Goal:
Implement a HashSet for integers using constant-time operations
without using built-in hash structures.

Key Idea:
Use a bitset where each bit represents whether a number is present.
Each integer key maps directly to:
- an index in the array
- a bit position inside an integer

Data Structure:
- int[] set
  • Each int stores 32 bits.
  • set[i] represents numbers from (i*32) to (i*32 + 31).

Indexing Logic:
- Array index  = key / 32
- Bit position = key % 32

Operations:
1. add(key):
   - Set the corresponding bit using OR (|).

2. remove(key):
   - Clear the corresponding bit using XOR (^) if it exists.

3. contains(key):
   - Check if the corresponding bit is set using AND (&).

Why It Works:
- Bit manipulation allows O(1) add, remove, and lookup.
- Direct indexing avoids collisions entirely.

Time Complexity:
- add / remove / contains: O(1)

Space Complexity:
- O(n) where n is the maximum key range supported
*/

package SystemDesign.Easy;

public class _705_Design_HashSet {
    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

    // Class to make the MyHashSet
    private static class MyHashSet {
        // Initialize the integer array of set of size 31251
        private final int[] set;

        public MyHashSet() {
            // Initialize the set
            this.set = new int[31251];
        }

        public void add(int key) {
            this.set[key / 32] |= this.getMask(key);
        }

        public void remove(int key) {
            if (this.contains(key)) {
                this.set[key / 32] ^= this.getMask(key);
            }
        }

        public boolean contains(int key) {
            return (this.set[key / 32] & this.getMask(key)) != 0;
        }

        // Helper method to get mask of the key
        private int getMask(int key) {
            return 1 << key % 32;
        }
    }

    // Main method to test MyHashSet
    public static void main(String[] args) {

        String[] operations = {
                "MyHashSet", "add", "add", "contains",
                "contains", "add", "contains", "remove", "contains"
        };

        int[][] values = {
                {}, { 1 }, { 2 }, { 1 }, { 3 }, { 2 }, { 2 }, { 2 }, { 2 }
        };

        MyHashSet myHashSet = new MyHashSet();

        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {

                case "MyHashSet" -> {
                    myHashSet = new MyHashSet();
                    System.out.println("null");
                }

                case "add" -> {
                    myHashSet.add(values[i][0]);
                    System.out.println("null");
                }

                case "contains" -> {
                    System.out.println(myHashSet.contains(values[i][0]));
                }

                case "remove" -> {
                    myHashSet.remove(values[i][0]);
                    System.out.println("null");
                }
            }
        }
    }

}
